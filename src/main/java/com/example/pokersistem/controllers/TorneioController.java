package com.example.pokersistem.controllers;

import com.example.pokersistem.domain.Torneio.Torneio;
import com.example.pokersistem.domain.Torneio.TorneioRequestDTO;
import com.example.pokersistem.domain.Torneio.TorneioResponseDTO;
import com.example.pokersistem.service.TorneioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/torneio")
public class TorneioController {

    @Autowired
    TorneioService torneioService;

    @PostMapping
    public ResponseEntity salvarTorneio(@RequestBody @Valid TorneioRequestDTO torneioRequestDTO){
        Torneio torneio = torneioService.salvar(new Torneio(torneioRequestDTO)) ;
        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity buscarTorneio(){
        List<TorneioResponseDTO> torneioResponseDTOList = this.torneioService.buscar().stream().map(TorneioResponseDTO::new).toList();
        return ResponseEntity.ok(torneioResponseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarTorneioId(@PathVariable(value = "id")Long id){
        return ResponseEntity.status(HttpStatus.OK).body(torneioService.buscarTorneioPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTorneio(@PathVariable(value = "id") Long id, @RequestBody @Valid TorneioRequestDTO torneioRequestDTO){
        Optional<Torneio> torneioOptional = torneioService.findById(id);
        if(!torneioOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Torneio não existente!");
        }
        Torneio torneioNew = new Torneio();
        BeanUtils.copyProperties(torneioRequestDTO, torneioNew);
        torneioNew.setId(torneioOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(torneioService.salvar(torneioNew));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTorneio(@PathVariable(value = "id") Long id){
        Optional<Torneio> torneioOptional = torneioService.findById(id);
        if(!torneioOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Torneio não existente!");
        }
        torneioService.delete(torneioOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Torneio deletado com sucesso!");

    }

}
