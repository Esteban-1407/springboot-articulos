package org.esteban.springboot.springmvc.app.backend_articulo_ventas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class VentaDTO {

        @NotNull(message = "ID del artículo requerido")
        private Long itemId;

        @NotNull(message = "Cantidad requerida")
        @Min(value = 1, message = "Debe comprar al menos 1")
        private Integer cantidad;
    }

