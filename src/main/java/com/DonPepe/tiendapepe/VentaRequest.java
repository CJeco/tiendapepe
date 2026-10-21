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

public class VentaRequest {
    private String cliente;
    private String producto;
    private Integer cantidad;
    private Double precioUnitario;
    private String metodoPago;
}
