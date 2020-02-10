/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senior.hotel.projeto.controler;

import com.senior.hotel.projeto.model.Hospede;
import com.senior.hotel.projeto.repository.HospedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import javax.validation.Valid;

@RestController
public class HospedeController {

    @Autowired
    private HospedeRepository HospedeRepository;

    @GetMapping("/hospede")
    public Page<Hospede> getHospede(Pageable pageable) {
        return HospedeRepository.findAll(pageable);
    }

    @PostMapping("/hospede")
    public Hospede createHospede(@Valid @RequestBody Hospede hospede) {
        System.out.println(hospede);
        return HospedeRepository.save(hospede);
    }

    @GetMapping("/hospede/{id}")
    public Hospede getHospede(@PathVariable("id") Long id) {
        Optional<Hospede> hospede = HospedeRepository.findById(id);
        if (!hospede.isPresent()) {
        }
        return hospede.get();
    }

    @DeleteMapping("/hospede/{id}")
    public void deletarHospede(@PathVariable("id") Long id) {
        HospedeRepository.deleteById(id);
    }

    @PutMapping("/hospede/{id}")
    public Hospede updateHospede(@RequestBody Hospede hospede, @PathVariable Long id) {
        Optional<Hospede> hospedeOptional = HospedeRepository.findById(id);
        if (!hospedeOptional.isPresent()) {
        }

        hospede.setId(id);
        HospedeRepository.save(hospede);
        return HospedeRepository.getOne(id);
    }

    @GetMapping("/hospede/busca")
    @ResponseBody
    public Page<Hospede> buscar(@RequestParam(name = "valorBusca", required = true) String valorBusca, Pageable pageable) {
        if (valorBusca != null && !valorBusca.isEmpty()) {
            String nome = valorBusca;
            String documento = valorBusca;
            String telefone = valorBusca;
            return HospedeRepository.findByNomeContainingIgnoreCaseOrDocumentoContainingIgnoreCaseOrTelefoneContainingIgnoreCase(nome, documento, telefone, pageable);
        }
        return null;
    }

}
