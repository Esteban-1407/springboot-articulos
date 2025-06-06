package org.esteban.springboot.springmvc.app.backend_articulo_ventas;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.esteban.springboot.springmvc.app.backend_articulo_ventas.dto.ItemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ItemIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    static final String URL = "/items";

    @Test
    void testInsertarItem() throws Exception {
        ItemDTO item = new ItemDTO("Teclado", "Teclado gamer", 120000.0, 10);

        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Teclado"));
    }

    @Test
    void testConsultarItems() throws Exception {
        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testEliminarItem() throws Exception {
        // Primero crear un item
        ItemDTO item = new ItemDTO("Mouse", "Mouse inalámbrico", 80000.0, 5);
        String body = objectMapper.writeValueAsString(item);

        String response = mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        // Obtener ID del JSON creado
        Long id = objectMapper.readTree(response).get("id").asLong();

        // Eliminar el item
        mockMvc.perform(delete(URL + "/" + id))
                .andExpect(status().isNoContent());

        // Verificar que ya no existe
        mockMvc.perform(get(URL + "/" + id))
                .andExpect(status().isNotFound());
    }
}