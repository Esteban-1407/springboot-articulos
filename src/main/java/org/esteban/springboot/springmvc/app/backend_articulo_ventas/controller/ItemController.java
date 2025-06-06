package org.esteban.springboot.springmvc.app.backend_articulo_ventas.controller;

import jakarta.validation.Valid;
import org.esteban.springboot.springmvc.app.backend_articulo_ventas.dto.ItemDTO;
import org.esteban.springboot.springmvc.app.backend_articulo_ventas.dto.VentaDTO;
import org.esteban.springboot.springmvc.app.backend_articulo_ventas.entity.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.esteban.springboot.springmvc.app.backend_articulo_ventas.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }
    @PostMapping
    public ResponseEntity<Item> createItem(@Valid @RequestBody ItemDTO itemDTO) {
        Item item = Item.builder()
                .name(itemDTO.getName())
                .description(itemDTO.getDescription())
                .price(itemDTO.getPrice())
                .stock(itemDTO.getStock())
                .build();


        Item saved = itemService.createItem(item);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build(); // 204
    }

    @PostMapping("/venta")
    public ResponseEntity<Item> venderItem(@Valid @RequestBody VentaDTO ventaDTO) {
        Item actualizado = itemService.venderItem(ventaDTO.getItemId(), ventaDTO.getCantidad());
        return ResponseEntity.ok(actualizado);
    }

}
