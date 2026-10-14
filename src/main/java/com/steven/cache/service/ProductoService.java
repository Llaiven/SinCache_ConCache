package com.steven.cache.service;

import com.steven.cache.model.Producto;
import com.steven.cache.repository.ProductoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarSinCache() {
        return productoRepository.findAll();
    }

    @Cacheable("productos")
    public List<Producto> listarConCache() {
        return  productoRepository.findAll();
    }

    @CacheEvict(value = "productos", allEntries = true)
    public void limpiarCache() {
    }
}
