package com.DonPepe.tiendapepe;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ventas/calcular")
@RequiredArgsConstructor
public class VentasController {

    private final CalculadoraVentas calculadoraVentas;

    @PostMapping
    public VentaResponse registroVenta(@RequestBody VentaRequest request) {
        return calculadoraVentas.calcular(request); // 1 sola línea
    }
}
