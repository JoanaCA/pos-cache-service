package com.challenge.poscacheservice.model;

//import jakarta.persistence.Id;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("PuntoDeVenta")
public class PuntoDeVenta implements Serializable {

    @Id
    private Long id;
    private String nombre;

    //Agregué esto porque no agarra lombok
//    public void setId(Long id) {
//        this.id = id;
//    }

}
