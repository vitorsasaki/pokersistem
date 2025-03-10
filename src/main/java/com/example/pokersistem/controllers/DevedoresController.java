package com.example.pokersistem.controllers;

import com.example.pokersistem.domain.devedores.Devedores;
import com.example.pokersistem.domain.devedores.DevedoresRequestDTO;
import com.example.pokersistem.domain.devedores.DevedoresResponseDTO;
import com.example.pokersistem.domain.jogador.Jogador;
import com.example.pokersistem.domain.jogador.JogadorRequestDTO;
import com.example.pokersistem.domain.jogador.JogadorResponseDTO;
import com.example.pokersistem.service.DevedoresService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/devedores")
public class DevedoresController {

    @Autowired
    private DevedoresService devedoresService;

    @PostMapping
    public ResponseEntity salvar(@RequestBody @Valid DevedoresRequestDTO devedoresRequestDTO){
        Devedores devedores = devedoresService.salvar(new Devedores(devedoresRequestDTO)) ;
        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity buscar(){
        List<DevedoresResponseDTO> devedoresResponseDTOList = this.devedoresService.buscar().stream().map(DevedoresResponseDTO::new).toList();
        return ResponseEntity.ok(devedoresResponseDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateDevedores(@PathVariable(value = "id") Long id, @RequestBody @Valid DevedoresRequestDTO devedoresRequestDTO){
        Optional<Devedores> devedoresOptional = devedoresService.findById(id);
        if(!devedoresOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Devedores não existente!");
        }
        Devedores devedoresNew = new Devedores();
        BeanUtils.copyProperties(devedoresRequestDTO, devedoresNew);
        devedoresNew.setId(devedoresOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(devedoresService.salvar(devedoresNew));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDevedores(@PathVariable(value = "id") Long id){
        Optional<Devedores> devedoresOptional = devedoresService.findById(id);
        if(!devedoresOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Devedores não existente!");
        }
        devedoresService.delete(devedoresOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Devedores deletado com sucesso!");

    }
}
