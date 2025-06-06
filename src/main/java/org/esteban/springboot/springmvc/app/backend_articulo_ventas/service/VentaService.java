package org.esteban.springboot.springmvc.app.backend_articulo_ventas.service;

public class VentaService {
    public double calcularTotal(double precioUnitario, int cantidad) {
        return precioUnitario * cantidad;
    }
}
