/**
 * 
 */
package com.nequi.uts601.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.nequi.uts601.sef.service.UtilService;

/**
 * Clase de configuración de los Servicios
 *         Rest de Middleware
 *
 */
public class ConfigServices extends Application {

    /**
     * Metodo para agregar Interfaces de Servicios Rest. Se deben agregar las
     * clases que expongan servicios Rest
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(UtilService.class);
        return classes;
    }
}
