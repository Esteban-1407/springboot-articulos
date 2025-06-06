package org.esteban.springboot.springmvc.app.backend_articulo_ventas;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.esteban.springboot.springmvc.app.backend_articulo_ventas.dto.ItemDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class itemE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static Long createdItemId;

    @Test
    @Order(1)
    void testPostCrearItem() throws Exception {
        ItemDTO item = new ItemDTO("Mouse inalámbrico", "Mouse ergonómico", 80000.0, 15);

        String response = mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Mouse inalámbrico"))
                .andReturn().getResponse().getContentAsString();

        // Guardar ID para siguientes pruebas
        Map<String, Object> responseMap = objectMapper.readValue(response, new TypeReference<>() {});
        createdItemId = Long.valueOf(responseMap.get("id").toString());
    }

    @Test
    @Order(2)
    void testGetAllItems() throws Exception {
        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @Order(3)
    void testDeleteItemAndVerify() throws Exception {
        // DELETE
        mockMvc.perform(delete("/items/" + createdItemId))
                .andExpect(status().isNoContent());

        // GET (esperamos 404)
        mockMvc.perform(get("/items/" + createdItemId))
                .andExpect(status().isNotFound());
    }
}