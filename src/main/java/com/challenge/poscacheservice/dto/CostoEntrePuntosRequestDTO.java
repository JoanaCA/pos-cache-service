package com.challenge.poscacheservice.dto;

import lombok.Data;

@Data
public class CostoEntrePuntosRequestDTO {
    private Long idOrigen;
    private Long idDestino;
    private Integer costo;
}
