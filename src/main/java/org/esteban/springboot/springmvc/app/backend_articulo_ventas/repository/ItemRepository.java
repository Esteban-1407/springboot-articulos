package org.esteban.springboot.springmvc.app.backend_articulo_ventas.repository;

import org.esteban.springboot.springmvc.app.backend_articulo_ventas.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}