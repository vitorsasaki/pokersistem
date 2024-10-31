package com.example.pokersistem.controllers;

import com.example.pokersistem.domain.jogador.Jogador;
import com.example.pokersistem.domain.jogador.JogadorRequestDTO;
import com.example.pokersistem.domain.jogador.JogadorResponseDTO;
import com.example.pokersistem.domain.tipoTorneio.TipoTorneio;
import com.example.pokersistem.domain.tipoTorneio.TipoTorneioRequestDTO;
import com.example.pokersistem.domain.tipoTorneio.TipoTorneioResponseDTO;
import com.example.pokersistem.service.TipoTorneioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipoTorneio")
public class TipoTorneioController {

    @Autowired
    TipoTorneioService tipoTorneioService;

    @PostMapping
    public ResponseEntity salvarTipoTorneio(@RequestBody @Valid TipoTorneioRequestDTO tipoTorneioRequestDTO){
        TipoTorneio tipoTorneio = tipoTorneioService.salvar(new TipoTorneio(tipoTorneioRequestDTO));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity buscarTipoTorneio(){
        List<TipoTorneioResponseDTO> tipoTorneioResponseDTOList = this.tipoTorneioService.buscar().stream().map(TipoTorneioResponseDTO::new).toList();
        return ResponseEntity.ok(tipoTorneioResponseDTOList);
    }

    @PostMapping("/{id}")
    public ResponseEntity updateTipoTorneio(@PathVariable(value = "id") Long id, @RequestBody @Valid TipoTorneioRequestDTO tipoTorneioRequestDTO){
        Optional<TipoTorneio> tipoTorneioOptional = tipoTorneioService.findById(id);
        if(!tipoTorneioOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo torneio Não existe");
        }
        TipoTorneio tipoTorneio = new TipoTorneio();
        BeanUtils.copyProperties(tipoTorneioRequestDTO, tipoTorneio);
        tipoTorneio.setId(tipoTorneioOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tipoTorneioService.salvar(tipoTorneio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTipoTorneio(@PathVariable(value = "id") Long id){
        Optional<TipoTorneio> tipoTorneioOptional = tipoTorneioService.findById(id);
        if(!tipoTorneioOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo Torneio não encontrado");
        }
        tipoTorneioService.delete(tipoTorneioOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Tipo Torneio deletado com sucesso!");
    }





}
