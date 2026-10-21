package com.DonPepe.tiendapepe;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CalculadoraVentas {

    public VentaResponse calcular(VentaRequest r) {
        validar(r);

        double subtotal = r.getCantidad() * r.getPrecioUnitario();
        String metodoPago = r.getMetodoPago().trim().toUpperCase();

        // Descuento sobre el subtotal
        double descuento = subtotal > 500 ? subtotal * 0.10 : 0;
        double base = subtotal - descuento;
        double igv = base * 0.18;
        double totalConIgv = base + igv;
        double comision = metodoPago.equals("TARJETA") ? totalConIgv * 0.03 : 0;
        double totalFinal = totalConIgv + comision;

        return VentaResponse.builder()
                .cliente(r.getCliente())
                .producto(r.getProducto())
                .cantidad(r.getCantidad())
                .subtotal(redondear(subtotal))
                .descuento(redondear(descuento))
                .igv(redondear(igv))
                .comision(redondear(comision))
                .total(redondear(totalFinal))
                .metodoPago(metodoPago)
                .build();
    }

    private void validar(VentaRequest r) {
        if (r == null) throw new ValidacionException("La solicitud no puede ser nula");
        if (r.getCliente() == null || r.getCliente().trim().isEmpty())
            throw new ValidacionException("El cliente no puede ser nulo ni vacío");
        if (r.getProducto() == null || r.getProducto().trim().isEmpty())
            throw new ValidacionException("El producto no puede ser nulo ni vacío");
        if (r.getMetodoPago() == null || r.getMetodoPago().trim().isEmpty())
            throw new ValidacionException("El método de pago no puede ser nulo ni vacío");
        if (r.getCantidad() == null || r.getCantidad() <= 0)
            throw new ValidacionException("La cantidad debe ser mayor a 0");
        if (r.getPrecioUnitario() == null || r.getPrecioUnitario() <= 0)
            throw new ValidacionException("El precio unitario debe ser mayor a 0");

        String metodo = r.getMetodoPago().trim().toUpperCase();
        if (!metodo.equals("TARJETA") && !metodo.equals("EFECTIVO"))
            throw new ValidacionException("Método de pago no válido: solo TARJETA o EFECTIVO");
    }

    private double redondear(double v) {
        return BigDecimal.valueOf(v).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}