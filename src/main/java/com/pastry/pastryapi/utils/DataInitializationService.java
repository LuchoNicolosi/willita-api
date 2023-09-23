package com.pastry.pastryapi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


@Service
public class DataInitializationService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void initializeData() {
        // Eliminar todos los datos de las colecciones que desees reiniciar
        mongoTemplate.remove(new Query(), "Categories");
        mongoTemplate.remove(new Query(), "Products");

        // Insertar nuevos datos de inicio si es necesario
        // mongoTemplate.insert(new TusDatosDeInicio(), "nombre_de_la_coleccion");
    }
}
