/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senior.hotel.projeto.controler;

import com.senior.hotel.projeto.model.Checkin;
import com.senior.hotel.projeto.model.Hospede;
import com.senior.hotel.projeto.repository.CheckInRepository;
import com.senior.hotel.projeto.repository.HospedeRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Eduardo
 */
@RestController
public class CheckInController {

    @Autowired
    private CheckInRepository checkInRepository;
    @Autowired
    private HospedeRepository hospedeRepository;

    @GetMapping("/checkin")
    public Page<Checkin> getChecking(Pageable pageable) {
        return checkInRepository.findAll(pageable);
    }

    @PostMapping("/checkin")
    public Checkin createCheckin(@Valid @RequestBody Checkin checkIn) {
        if (checkIn.getHospede() != null && checkIn.getHospede().getId() == null) {
            Hospede hosp = checkIn.getHospede();
            if (hosp.getNome() != null && !hosp.getNome().isEmpty()) {
                Hospede hospede = hospedeRepository.findOneByNomeContainingIgnoreCase(hosp.getNome());
                if (hospede == null) {
                    hospede = hospedeRepository.save(checkIn.getHospede());

                }
                checkIn.setHospede(hospede);
            }

        }
        setValorDiaria(checkIn);
        return checkInRepository.save(checkIn);
    }

    private void setValorDiaria(Checkin checkIn) {
        Date dataEntrada = checkIn.getDataEntrada();
        Date dataSaida = checkIn.getDataSaida();
        if (dataEntrada != null && dataSaida != null) {
            try {
                checkIn.setValor(calcularValor(dataEntrada, dataSaida, checkIn.getAdicionalVeiculo()));
            } catch (ParseException ex) {
                Logger.getLogger(CheckInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private double calcularValor(Date dataEntrada, Date dataSaida, boolean adicionalVeiculo) throws ParseException {
        double retorno = 0.0;
        Date dataCalc = dataEntrada;
        Date dataSaidaCalc = dataSaida;

        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
        Date horaSaida = sdfHora.parse(sdfHora.format(dataSaidaCalc));
        Date horaLimite = sdfHora.parse("16:30");
        if (horaSaida.after(horaLimite)) {
            Calendar c = Calendar.getInstance();
            c.setTime(dataSaidaCalc);
            c.add(Calendar.DATE, 1);
            dataSaidaCalc = c.getTime();
        }

        while (dataCalc.before(dataSaidaCalc)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataCalc);
            if (cal.get(Calendar.DAY_OF_WEEK) == 1 || cal.get(Calendar.DAY_OF_WEEK) == 7) {
                retorno += (adicionalVeiculo) ? 170 : 150;
            } else {
                retorno += (adicionalVeiculo) ? 135 : 120;
            }
            cal.add(Calendar.DATE, 1);
            dataCalc = cal.getTime();
        }
        return retorno;
    }

}
