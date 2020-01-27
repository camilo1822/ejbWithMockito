/**
 * 
 */
package com.nequi.uts601.ejb.service;

import javax.ejb.Local;
import javax.persistence.EntityManager;

import com.nequi.cmm.consumer.registry.ServiceRegistry;
import com.nequi.uts601.ejb.exception.MBSException;

@Local
public interface IGeneral {

    /**
     * Metodo que expone la operacion principal del servicio.
     * 
     * @param request
     * @return respuesta del servicio en formato JSON.
     * @throws MBSException
     */
    public String executeOperation(String request,
            ServiceRegistry serviceRegistry, EntityManager em)
            throws MBSException;

}
