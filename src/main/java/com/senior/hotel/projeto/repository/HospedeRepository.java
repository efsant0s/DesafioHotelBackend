/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senior.hotel.projeto.repository;

import com.senior.hotel.projeto.model.Checkin;
import com.senior.hotel.projeto.model.Hospede;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long> {

    Page<Hospede> findByNomeContainingIgnoreCaseOrDocumentoContainingIgnoreCaseOrTelefoneContainingIgnoreCase(String nome, String documento, String telefone, Pageable pageable);

    Hospede findOneByNomeContainingIgnoreCaseOrDocumentoContainingIgnoreCaseOrTelefoneContainingIgnoreCase(String nome, String documento, String telefone); 
}
