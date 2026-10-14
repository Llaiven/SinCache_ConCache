package com.steven.cache.controller;

import com.steven.cache.model.Producto;
import com.steven.cache.service.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/sin-cache")
    public Map<String, Object> sinCache() {
        long inicio = System.currentTimeMillis();
        List<Producto> productos = productoService.listarSinCache();
        long fin = System.currentTimeMillis();

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("tipo", "sin cache");
        respuesta.put("registros", productos.size());
        respuesta.put("tiempoMs", fin - inicio);
        return  respuesta;
    }

    @GetMapping("/con-cache")
    public Map<String, Object> conCache() {
        long inicio = System.currentTimeMillis();
        List<Producto> productos = productoService.listarConCache();
        long fin = System.currentTimeMillis();

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("tipo", "con cache");
        respuesta.put("registros", productos.size());
        respuesta.put("tiempoMs", fin - inicio);
        return respuesta;
    }

    @GetMapping("/limpiar-cache")
    public String limpiarCache() {
        productoService.limpiarCache();
        return "Cache limpiada";
    }
}
