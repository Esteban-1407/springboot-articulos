package org.esteban.springboot.springmvc.app.backend_articulo_ventas;

import java.util.UUID;

public class ItemUtil {
    public static String generarCodigo(String prefijo) {
        return prefijo + "-" + UUID.randomUUID().toString().substring(0, 8);
    }
}
