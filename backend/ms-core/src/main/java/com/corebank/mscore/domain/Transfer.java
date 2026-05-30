package com.corebank.mscore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cuentaOrigenId;
    private Long cuentaDestinoId;
    private Double monto;
    private String moneda;
    private String estado; // pendiente - completada - fallida
    private LocalDateTime fechaTransferencia;
}
