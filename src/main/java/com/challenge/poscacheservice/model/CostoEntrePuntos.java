package com.challenge.poscacheservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("CostoEntrePuntos")
public class CostoEntrePuntos implements Serializable {

    @Id
    private String id; // 

    private Long idOrigen;
    private Long idDestino;
    private Integer costo;
}
