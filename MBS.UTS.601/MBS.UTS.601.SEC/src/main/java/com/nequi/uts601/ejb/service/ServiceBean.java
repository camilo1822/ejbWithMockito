/**
 * 
 */
package com.nequi.uts601.ejb.service;

import javax.ejb.Local;

import com.nequi.uts601.ejb.exception.MBSException;

@Local
public interface ServiceBean {

    /**
     * Metodo que expone la operacion principal del servicio.
     * 
     * @param request
     * @return respuesta del servicio en formato JSON.
     * @throws MBSException
     */
    public String executeOperation(String request) throws MBSException;

}
