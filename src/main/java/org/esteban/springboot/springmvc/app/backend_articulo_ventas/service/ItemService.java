package org.esteban.springboot.springmvc.app.backend_articulo_ventas.service;

import org.esteban.springboot.springmvc.app.backend_articulo_ventas.entity.Item;
import org.springframework.stereotype.Service;
import org.esteban.springboot.springmvc.app.backend_articulo_ventas.repository.ItemRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class ItemService{
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }


    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }


    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item no encontrado con ID: " + id));
    }
    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new RuntimeException("Item no encontrado con ID: " + id);
        }
        itemRepository.deleteById(id);
    }
    @Transactional
    public Item venderItem(Long itemId, int cantidad) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        if (item.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente para la venta");
        }

        item.setStock(item.getStock() - cantidad);
        return itemRepository.save(item);
    }



}
