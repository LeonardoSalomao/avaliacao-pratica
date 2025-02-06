package com.desafio.transferencias.service;

import com.desafio.transferencias.model.Transferencia;
import com.desafio.transferencias.repository.TransferenciaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransferenciaService {

    private final TransferenciaRepository repository;

    public TransferenciaService(TransferenciaRepository repository) {
        this.repository = repository;
    }

    public Transferencia agendarTransferencia(Transferencia transferencia) {
        long dias = ChronoUnit.DAYS.between(LocalDate.now(), transferencia.getDataTransferencia());
        double taxa = calcularTaxa(dias, transferencia.getValor());
        if (taxa == -1) {
            throw new IllegalArgumentException("Não há taxa aplicável para esta transferência.");
        }
        transferencia.setTaxa(taxa);
        transferencia.setDataAgendamento(LocalDate.now());
        return repository.save(transferencia);
    }

    private double calcularTaxa(long dias, double valor) {
        if (dias == 0) {
            return valor * 0.025 + 3;
        } else if (dias >= 1 && dias <= 10) {
            return 12;
        } else if (dias >= 11 && dias <= 20) {
            return valor * 0.082;
        } else if (dias >= 21 && dias <= 30) {
            return valor * 0.069;
        } else if (dias >= 31 && dias <= 40) {
            return valor * 0.047;
        } else if (dias >= 41 && dias <= 50) {
            return valor * 0.017;
        } else {
            return -1; // Nenhuma taxa aplicável
        }
    }

    public List<Transferencia> listarTransferencias() {
        return repository.findAll();
    }
}
