package com.steven.cache.config;

import com.steven.cache.model.Producto;
import com.steven.cache.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class CargaDatos implements CommandLineRunner {

    private final ProductoRepository productoRepository;

    public CargaDatos(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) {
        if (productoRepository.count() > 0) {
            System.out.println("Ya hay datos en la BD: " + productoRepository.count());
            return;
        }

        String[] categorias = {"Tecnologia", "Hogar", "Deportes", "Libros", "Ropa"};
        Random random = new Random();
        int total = 100000;

        System.out.println("Insertando " + total + " productos...");
        long inicio = System.currentTimeMillis();

        List<Producto> lote = new ArrayList<>();
        for (int i = 1; i <= total; i++) {
            String categoria = categorias[random.nextInt(categorias.length)];
            double precio = Math.round(random.nextDouble() * 1000 * 100) / 100.0;
            int stock = random.nextInt(500);

            lote.add(new Producto("Producto " + i, categoria, precio, stock));

            if (lote.size() == 1000) {
                productoRepository.saveAll(lote);
                lote.clear();
            }
        }

        long fin = System.currentTimeMillis();
        System.out.println("Datos insertados en " + (fin - inicio) + " ms");
    }
}