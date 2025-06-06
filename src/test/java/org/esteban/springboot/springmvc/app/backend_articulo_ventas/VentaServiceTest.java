package org.esteban.springboot.springmvc.app.backend_articulo_ventas;

import org.esteban.springboot.springmvc.app.backend_articulo_ventas.service.VentaService;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ActiveProfiles("test")
public class VentaServiceTest {
    @Test
    void testCalcularTotalVenta() {
        VentaService ventaService = new VentaService();
        int cantidad = 3;
        double precioUnitario = 25000;

        double total = ventaService.calcularTotal(precioUnitario, cantidad);

        assertEquals(75000, total);
    }
}
