package com.example.pokersistem.service;

import com.example.pokersistem.Enum.EnumTipoLancamento;
import com.example.pokersistem.domain.jogador.Jogador;
import com.example.pokersistem.domain.lancamentoDevedor.ImportacaoLancamentoResultadoDTO;
import com.example.pokersistem.domain.lancamentoDevedor.LancamentoDevedor;
import com.example.pokersistem.repository.JogadorRepository;
import com.example.pokersistem.repository.LancamentoDevedorRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ImportacaoLancamentoService {

    private static final Pattern OPERADOR_PATTERN = Pattern.compile("\\((\\d+)\\)");
    private static final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private LancamentoDevedorRepository lancamentoDevedorRepository;

    public ImportacaoLancamentoResultadoDTO importar(MultipartFile file) {
        ImportacaoLancamentoResultadoDTO resultado = new ImportacaoLancamentoResultadoDTO();

        if (file == null || file.isEmpty()) {
            resultado.adicionarErro(0, "Arquivo vazio ou não enviado");
            return resultado;
        }

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                resultado.adicionarErro(0, "Planilha sem abas");
                return resultado;
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                resultado.adicionarErro(0, "Planilha sem cabeçalho");
                return resultado;
            }

            Map<String, Integer> colunas = mapearColunas(headerRow);
            validarColunasObrigatorias(colunas);

            Map<Long, Jogador> cacheJogadores = new HashMap<>();
            List<LancamentoDevedor> pendentes = new ArrayList<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null || linhaVazia(row)) {
                    continue;
                }

                int numeroLinha = i + 1;
                try {
                    String nomeJogador = getCelulaComoString(row, colunas.get("Jogador"));
                    Long jogadorIdPp = getCelulaComoLong(row, colunas.get("JogadorID"));
                    BigDecimal pontos = getCelulaComoBigDecimal(row, colunas.get("Pontos"));
                    String tipoOperacao = getCelulaComoString(row, colunas.get("Tipo de operação"));
                    String idOperadorRaw = getCelulaComoString(row, colunas.get("ID do operador"));
                    Date hora = getCelulaComoData(row, colunas.get("Hora"));

                    if (jogadorIdPp == null) {
                        throw new IllegalArgumentException("JogadorID inválido");
                    }
                    if (nomeJogador == null || nomeJogador.isBlank()) {
                        throw new IllegalArgumentException("Jogador vazio");
                    }
                    if (pontos == null) {
                        throw new IllegalArgumentException("Pontos inválidos");
                    }
                    if (hora == null) {
                        throw new IllegalArgumentException("Hora inválida");
                    }

                    EnumTipoLancamento tipoLancamento = EnumTipoLancamento.fromPlanilha(tipoOperacao);
                    Integer idOperador = extrairIdOperador(idOperadorRaw);

                    Jogador jogador = obterJogadorPorIdPp(jogadorIdPp, nomeJogador.trim(), cacheJogadores);

                    LancamentoDevedor lancamento = new LancamentoDevedor();
                    lancamento.setJogador(jogador);
                    lancamento.setTipoLancamento(tipoLancamento);
                    lancamento.setValor(pontos);
                    lancamento.setData(hora);
                    lancamento.setIdOperador(idOperador);

                    pendentes.add(lancamento);
                } catch (JogadorNaoEncontradoException e) {
                    resultado.adicionarErro(numeroLinha, e.getMessage());
                    return resultado;
                } catch (Exception e) {
                    resultado.adicionarErro(numeroLinha, e.getMessage());
                }
            }

            for (LancamentoDevedor lancamento : pendentes) {
                lancamentoDevedorRepository.save(lancamento);
                resultado.incrementarImportados();
            }
        } catch (IllegalArgumentException e) {
            resultado.adicionarErro(0, e.getMessage());
        } catch (Exception e) {
            resultado.adicionarErro(0, "Erro ao processar planilha: " + e.getMessage());
        }

        return resultado;
    }

    private Jogador obterJogadorPorIdPp(Long idPp, String nomeJogador, Map<Long, Jogador> cacheJogadores) {
        Jogador emCache = cacheJogadores.get(idPp);
        if (emCache != null) {
            return emCache;
        }

        Jogador jogador = jogadorRepository.findByIdPP(idPp)
                .orElseThrow(() -> new JogadorNaoEncontradoException(
                        "Jogador não encontrado: " + nomeJogador + " (ID " + idPp + ")"));

        cacheJogadores.put(idPp, jogador);
        return jogador;
    }

    private static class JogadorNaoEncontradoException extends RuntimeException {
        JogadorNaoEncontradoException(String message) {
            super(message);
        }
    }

    private Map<String, Integer> mapearColunas(Row headerRow) {
        Map<String, Integer> colunas = new HashMap<>();
        for (Cell cell : headerRow) {
            String nome = getCelulaComoString(cell);
            if (nome != null && !nome.isBlank()) {
                colunas.put(nome.trim(), cell.getColumnIndex());
            }
        }
        return colunas;
    }

    private void validarColunasObrigatorias(Map<String, Integer> colunas) {
        List<String> obrigatorias = List.of(
                "Jogador", "JogadorID", "Pontos", "Tipo de operação", "ID do operador", "Hora"
        );
        List<String> faltando = new ArrayList<>();
        for (String coluna : obrigatorias) {
            if (!colunas.containsKey(coluna)) {
                faltando.add(coluna);
            }
        }
        if (!faltando.isEmpty()) {
            throw new IllegalArgumentException("Colunas obrigatórias ausentes: " + String.join(", ", faltando));
        }
    }

    private Integer extrairIdOperador(String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }
        Matcher matcher = OPERADOR_PATTERN.matcher(valor);
        if (matcher.find()) {
            return Integer.valueOf(matcher.group(1));
        }
        try {
            return Integer.valueOf(valor.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID do operador inválido: " + valor);
        }
    }

    private boolean linhaVazia(Row row) {
        for (Cell cell : row) {
            String valor = getCelulaComoString(cell);
            if (valor != null && !valor.isBlank()) {
                return false;
            }
        }
        return true;
    }

    private String getCelulaComoString(Row row, Integer coluna) {
        if (coluna == null) {
            return null;
        }
        return getCelulaComoString(row.getCell(coluna));
    }

    private String getCelulaComoString(Cell cell) {
        if (cell == null) {
            return null;
        }
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    yield DATA_FORMAT.format(cell.getDateCellValue());
                }
                double valor = cell.getNumericCellValue();
                if (valor == Math.floor(valor)) {
                    yield String.valueOf((long) valor);
                }
                yield String.valueOf(valor);
            }
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> null;
        };
    }

    private Long getCelulaComoLong(Row row, Integer coluna) {
        String valor = getCelulaComoString(row, coluna);
        if (valor == null || valor.isBlank()) {
            return null;
        }
        try {
            return Long.valueOf(valor.trim().replaceAll("\\.0$", ""));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valor inteiro inválido: " + valor);
        }
    }

    private BigDecimal getCelulaComoBigDecimal(Row row, Integer coluna) {
        if (coluna == null) {
            return null;
        }
        Cell cell = row.getCell(coluna);
        if (cell == null) {
            return null;
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            return BigDecimal.valueOf(cell.getNumericCellValue());
        }
        String valor = getCelulaComoString(cell);
        if (valor == null || valor.isBlank()) {
            return null;
        }
        try {
            return new BigDecimal(valor.trim().replace(",", "."));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valor decimal inválido: " + valor);
        }
    }

    private Date getCelulaComoData(Row row, Integer coluna) {
        if (coluna == null) {
            return null;
        }
        Cell cell = row.getCell(coluna);
        if (cell == null) {
            return null;
        }
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue();
        }
        String valor = getCelulaComoString(cell);
        if (valor == null || valor.isBlank()) {
            return null;
        }
        try {
            synchronized (DATA_FORMAT) {
                return DATA_FORMAT.parse(valor.trim());
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("Data inválida: " + valor);
        }
    }
}
