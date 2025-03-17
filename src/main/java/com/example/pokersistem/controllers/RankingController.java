package com.example.pokersistem.controllers;

import com.example.pokersistem.domain.ranking.Ranking;
import com.example.pokersistem.domain.ranking.RankingRequestDTO;
import com.example.pokersistem.domain.ranking.RankingResponseDTO;
import com.example.pokersistem.domain.resultadoTorneio.ResultadoTorneio;
import com.example.pokersistem.domain.resultadoTorneio.ResultadoTorneioResponseDTO;
import com.example.pokersistem.service.RankingService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ranking")
public class RankingController {


    @Autowired
    RankingService rankingService;

    @PostMapping
    public void atualizarRanking(@RequestBody @Valid List<ResultadoTorneio> resultadoTorneio){
        rankingService.atualizarRanking(resultadoTorneio);
    }

    @GetMapping
    public ResponseEntity buscarRanking(){
        List<RankingResponseDTO> rankingResponseDTOList = this.rankingService.buscar().stream().map(RankingResponseDTO::new).toList();
        return ResponseEntity.ok(rankingResponseDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateRanking(@PathVariable(value = "id") Long id, @RequestBody @Valid RankingRequestDTO rankingRequestDTO){
        Optional<Ranking> rankingOptional = rankingService.findById(id);
        if(!rankingOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ranking não existente!");
        }
        Ranking rankingNew = new Ranking();
        BeanUtils.copyProperties(rankingRequestDTO, rankingNew);
        rankingNew.setId(rankingOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(rankingService.salvar(rankingNew));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRanking(@PathVariable(value = "id") Long id){
        Optional<Ranking> rankingOptional = rankingService.findById(id);
        if(!rankingOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ranking não existente!");
        }
        rankingService.delete(rankingOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Ranking deletado com sucesso!");

    }

}
