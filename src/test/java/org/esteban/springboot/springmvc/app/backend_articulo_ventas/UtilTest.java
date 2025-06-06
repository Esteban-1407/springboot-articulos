package org.esteban.springboot.springmvc.app.backend_articulo_ventas;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;
@ActiveProfiles("test")
public class UtilTest {

    @Test
    void testGenerarCodigoUnico() {
        String codigo = ItemUtil.generarCodigo("CAM");
        assertTrue(codigo.startsWith("CAM-"));
        assertTrue(codigo.length() > 4);
    }
}
