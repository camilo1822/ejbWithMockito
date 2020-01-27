
package com.nequi.uts601.ejb.factory.impl;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import com.nequi.uts601.ejb.factory.IFactory;
import com.nequi.uts601.ejb.factory.senddocument.COSendDocumentFactory;
import com.nequi.uts601.ejb.service.ServiceBean;
import com.nequi.uts601.ejb.util.Constant;

/**
 * Session Bean implementation class SendDocumentFactory
 */
@Singleton(mappedName = Constant.COMMON_STRING_SEND_DOCUMENT_FACTORY)
@LocalBean
public class SendDocumentFactory implements IFactory {

    @EJB(beanName = Constant.COMMON_STRING_CO_SEND_DOCUMENT_FACTORY)
    private COSendDocumentFactory factory;

    /**
     * Mapeo de service bean a generar por la fabrica.
     * 
     * @param region
     * @param version
     * @return {@link ServiceBean}
     */
    @Override
    public ServiceBean getServiceBean(String region, String version) {
        ServiceBean bean = null;

        switch (region) {
        case Constant.COMMON_STRING_REGION_COLOMBIA:
            bean = factory.buildServiceBean(version);
            break;
        default:
            bean = factory.buildServiceBean(version);
            break;
        }
        return bean;
    }
}
