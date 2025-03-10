package com.example.pokersistem.controllers;

import com.example.pokersistem.domain.lancamentoDevedor.LancamentoDevedor;
import com.example.pokersistem.domain.lancamentoDevedor.LancamentoDevedorRequestDTO;
import com.example.pokersistem.domain.lancamentoDevedor.LancamentoDevedorResponseDTO;
import com.example.pokersistem.service.LancamentoDevedorService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentoDevedor")
public class LancamentoDevedorController {

    @Autowired
    private LancamentoDevedorService lancamentoDevedorService;

    @PostMapping
    public ResponseEntity salvar(@RequestBody @Valid LancamentoDevedorRequestDTO lancamentoDevedorRequestDTO){
        LancamentoDevedor lancamentoDevedor = lancamentoDevedorService.salvar(new LancamentoDevedor(lancamentoDevedorRequestDTO)) ;
        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity buscar(){
        List<LancamentoDevedorResponseDTO> lancamentoDevedorRequestDTOList = this.lancamentoDevedorService.buscar().stream().map(LancamentoDevedorResponseDTO::new).toList();
        return ResponseEntity.ok(lancamentoDevedorRequestDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateLancamentoDevedor(@PathVariable(value = "id") Long id, @RequestBody @Valid LancamentoDevedorRequestDTO lancamentoDevedorRequestDTO){
        Optional<LancamentoDevedor> lancamentoDevedorOptional = lancamentoDevedorService.findById(id);
        if(!lancamentoDevedorOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("LancamentoDevedor não existente!");
        }
        LancamentoDevedor lancamentoDevedorNew = new LancamentoDevedor();
        BeanUtils.copyProperties(lancamentoDevedorRequestDTO, lancamentoDevedorNew);
        lancamentoDevedorNew.setId(lancamentoDevedorOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(lancamentoDevedorService.salvar(lancamentoDevedorNew));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLancamentoDevedor(@PathVariable(value = "id") Long id){
        Optional<LancamentoDevedor> lancamentoDevedorOptional = lancamentoDevedorService.findById(id);
        if(!lancamentoDevedorOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("LancamentoDevedor não existente!");
        }
        lancamentoDevedorService.delete(lancamentoDevedorOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("LancamentoDevedor deletado com sucesso!");

    }

}
