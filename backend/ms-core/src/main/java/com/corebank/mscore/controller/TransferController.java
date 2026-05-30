package com.corebank.mscore.controller;

import com.corebank.mscore.domain.Transfer;
import com.corebank.mscore.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
public class TransferController {

    // Spring inyecta el servicio automaticamente
    private final TransferService transferService;

    // Realiza una transferencia entre 2 cuentas
    @PostMapping
    public ResponseEntity<Transfer> transferir(@RequestBody Transfer transfer){
        return ResponseEntity.status(201).body(transferService.transfer(transfer));
    }

    // Devuelve todas las transferencias
    @GetMapping
    public ResponseEntity<List<Transfer>> listarTodas(){
        return ResponseEntity.ok(transferService.findAll());
    }

}
