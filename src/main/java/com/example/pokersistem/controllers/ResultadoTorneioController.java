package com.example.pokersistem.controllers;

import com.example.pokersistem.Enum.EnumSimNao;
import com.example.pokersistem.domain.ranking.Ranking;
import com.example.pokersistem.domain.resultadoTorneio.ResultadoTorneio;
import com.example.pokersistem.domain.resultadoTorneio.ResultadoTorneioRequestDTO;
import com.example.pokersistem.domain.resultadoTorneio.ResultadoTorneioResponseDTO;
import com.example.pokersistem.service.RankingService;
import com.example.pokersistem.service.ResultadoTorneioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/resultadoTorneio")
public class ResultadoTorneioController {

    @Autowired
    private ResultadoTorneioService resultadoTorneioService;

    @Autowired
    private RankingService rankingService;

    @PostMapping
    public ResponseEntity salvar(@RequestBody @Valid ResultadoTorneioRequestDTO resultadoTorneioRequestDTO){
        try {
            ResultadoTorneio resultadoTorneio = resultadoTorneioService.salvar(new ResultadoTorneio(resultadoTorneioRequestDTO)) ;
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/lista")
    public ResponseEntity salvaLista(@RequestBody @Valid List<ResultadoTorneioRequestDTO> resultadoTorneioRequestDTOList){
        try {
            for (ResultadoTorneioRequestDTO resultadoTorneioRequestDTO : resultadoTorneioRequestDTOList){
                ResultadoTorneio resultadoTorneio = resultadoTorneioService.salvar(new ResultadoTorneio(resultadoTorneioRequestDTO)) ;
            }
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity buscar(){
        List<ResultadoTorneioResponseDTO> resultadoTorneioResponseDTOList = this.resultadoTorneioService.buscar().stream().map(ResultadoTorneioResponseDTO::new).toList();
        return ResponseEntity.ok(resultadoTorneioResponseDTOList);
    }

    @GetMapping("/naoAtualizados")
    public ResponseEntity buscarNaoAtualizados(){
        List<ResultadoTorneioResponseDTO> resultadoTorneioResponseDTOList = this.resultadoTorneioService.buscarResultadosNaoAtualizados().stream().map(ResultadoTorneioResponseDTO::new).toList();
        return ResponseEntity.ok(resultadoTorneioResponseDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateResultadoTorneio(@PathVariable(value = "id") Long id, @RequestBody @Valid ResultadoTorneioRequestDTO resultadoTorneioRequestDTO){
        Optional<ResultadoTorneio> resultadoTorneioOptional = resultadoTorneioService.findById(id);
        if(!resultadoTorneioOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ResultadoTorneio não existente!");
        }
        ResultadoTorneio resultadoTorneioNew = new ResultadoTorneio();
        BeanUtils.copyProperties(resultadoTorneioRequestDTO, resultadoTorneioNew);
        resultadoTorneioNew.setId(resultadoTorneioOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(resultadoTorneioService.update(resultadoTorneioNew));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteResultadoTorneio(@PathVariable(value = "id") Long id){
        Optional<ResultadoTorneio> resultadoTorneioOptional = resultadoTorneioService.findById(id);
        if(!resultadoTorneioOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ResultadoTorneio não existente!");
        }
        resultadoTorneioService.delete(resultadoTorneioOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("ResultadoTorneio deletado com sucesso!");

    }
}
