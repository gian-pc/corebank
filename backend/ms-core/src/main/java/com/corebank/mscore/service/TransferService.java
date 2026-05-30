package com.corebank.mscore.service;

import com.corebank.mscore.domain.Account;
import com.corebank.mscore.domain.Transfer;
import com.corebank.mscore.repository.AccountRepository;
import com.corebank.mscore.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferService {
    // Spring inyect estos 2 repositorios de forma automatica
    private final TransferRepository transferRepository;
    private final AccountRepository accountRepository;

    public Transfer transfer(Transfer transfer){
        // Busca cuenta origen por id y si no existe lanza excepcion
        Account source = accountRepository.findById(transfer.getCuentaOrigenId()).orElseThrow(()-> new RuntimeException("No se encontro la cuenta origen"));

        // Busca cuenta destino por id
        Account target = accountRepository.findById(transfer.getCuentaDestinoId()).orElseThrow(()->new RuntimeException("No se encontro la cuenta de destino"));

        // restamos el montos de la cuenta origen
        source.setSaldo(source.getSaldo() - transfer.getMonto());

        // suma el monto de la cuenta de destino
        target.setSaldo(target.getSaldo() + transfer.getMonto());

        // Guarda los nuevos saldos en la base de datos
        accountRepository.save(source);
        accountRepository.save(target);

        // Cambiamos el status a COMPLETADA
        transfer.setEstado("COMPLETADA");

        // Registramos la fecha y hora actual
        transfer.setFechaTransferencia(LocalDateTime.now());

        // Guardamos la transferencia en la BD y la devuelve
        return transferRepository.save(transfer);
    }
    // Devuelve todas las transferencias
    public List<Transfer> findAll(){
        return transferRepository.findAll();
    }
}
