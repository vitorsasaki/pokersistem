package com.example.pokersistem.controllers;

import com.example.pokersistem.domain.jogador.Jogador;
import com.example.pokersistem.domain.jogador.JogadorRequestDTO;
import com.example.pokersistem.domain.jogador.JogadorResponseDTO;
import com.example.pokersistem.service.JogadorService;
import jakarta.validation.Valid;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CommonsLog
@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    @Autowired
    JogadorService jogadorService;

    @PostMapping
    public ResponseEntity salvarJogador(@RequestBody @Valid JogadorRequestDTO jogadorRequestDTO){
        log.info("teste");
        Jogador jogador = jogadorService.salvar(new Jogador(jogadorRequestDTO)) ;
        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity buscarJogador(){
        List<JogadorResponseDTO> jogadorResponseDTOList = this.jogadorService.buscar().stream().map(JogadorResponseDTO::new).toList();
        return ResponseEntity.ok(jogadorResponseDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateJogador(@PathVariable(value = "id") Long id, @RequestBody @Valid JogadorRequestDTO jogadorRequestDTO){
        Optional<Jogador> jogadorOptional = jogadorService.findById(id);
        if(!jogadorOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador não existente!");
        }
        Jogador jogadorNew = new Jogador();
        BeanUtils.copyProperties(jogadorRequestDTO, jogadorNew);
        jogadorNew.setId(jogadorOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(jogadorService.salvar(jogadorNew));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteJogador(@PathVariable(value = "id") Long id){
        Optional<Jogador> jogadorOptional = jogadorService.findById(id);
        if(!jogadorOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador não existente!");
        }
        jogadorService.delete(jogadorOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Jogador deletado com sucesso!");

    }


}
