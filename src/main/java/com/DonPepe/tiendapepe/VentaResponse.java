package com.DonPepe.tiendapepe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class VentaResponse {
    private String cliente;
    private String producto;
    private Integer cantidad;
    private Double subtotal;
    private Double descuento;
    private Double igv;
    private Double comision;
    private Double total;
    private String metodoPago;
}
