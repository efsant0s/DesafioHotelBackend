/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senior.hotel.projeto.repository;

 
import com.senior.hotel.projeto.model.Checkin;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 

@Repository
public interface CheckInRepository extends JpaRepository<Checkin, Long> {
 	List<Checkin> findByDataEntrada(Date dataEntrada);
	List<Checkin> findByDataSaida(Date dataSaida);
	List<Checkin> findByHospedeId(Long hospedeId);
}
