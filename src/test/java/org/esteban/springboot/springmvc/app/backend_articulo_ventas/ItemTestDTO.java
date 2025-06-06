package org.esteban.springboot.springmvc.app.backend_articulo_ventas;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.esteban.springboot.springmvc.app.backend_articulo_ventas.dto.ItemDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ActiveProfiles("test")
public class ItemTestDTO {
    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testInvalidItemDTO_ShouldFailValidation() {
        ItemDTO item = new ItemDTO();
        item.setName(""); // inválido
        item.setPrice((double) -100); // inválido
        item.setStock(-5); // inválido

        Set<ConstraintViolation<ItemDTO>> violations = validator.validate(item);

        assertFalse(violations.isEmpty(), "Se esperaban errores de validación");
    }
}
