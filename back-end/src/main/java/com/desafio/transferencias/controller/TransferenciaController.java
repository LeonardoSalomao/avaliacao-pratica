package com.desafio.transferencias.controller;

import com.desafio.transferencias.model.Transferencia;
import com.desafio.transferencias.service.TransferenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
@CrossOrigin(origins = "http://localhost:8081")
@Tag(name = "Transferências", description = "Operações relacionadas ao agendamento de transferências financeiras")
public class TransferenciaController {

    private final TransferenciaService service;

    public TransferenciaController(TransferenciaService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Agendar uma transferência", description = "Agenda uma nova transferência financeira.")
    @ApiResponse(responseCode = "200", description = "Transferência agendada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    public Transferencia agendarTransferencia(@RequestBody Transferencia transferencia) {
        return service.agendarTransferencia(transferencia);
    }

    @GetMapping
    @Operation(summary = "Listar transferências", description = "Retorna a lista de todas as transferências agendadas.")
    @ApiResponse(responseCode = "200", description = "Lista de transferências retornada com sucesso")
    public List<Transferencia> listarTransferencias() {
        return service.listarTransferencias();
    }

}
