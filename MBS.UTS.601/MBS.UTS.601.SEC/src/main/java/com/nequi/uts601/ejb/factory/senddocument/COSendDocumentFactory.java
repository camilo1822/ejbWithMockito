
package com.nequi.uts601.ejb.factory.senddocument;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import com.nequi.uts601.ejb.service.ServiceBean;
import com.nequi.uts601.ejb.util.Constant;

/**
 * Session Bean implementation class COSendDocumentFactory
 */
@Singleton(mappedName =  Constant.COMMON_STRING_CO_SEND_DOCUMENT_FACTORY)
@LocalBean
public class COSendDocumentFactory {

    @EJB(beanName = Constant.COMMON_STRING_SEND_DOCUMENT_SERVICE_SEIYA)
    private ServiceBean serviceBeanSeiya;

    public ServiceBean buildServiceBean(String version) {
        switch (String.valueOf(version)) {
        case Constant.COMMON_STRING_DEFAULT_VERSION:
            return serviceBeanSeiya;
        default:
            return serviceBeanSeiya;
        }
    }
}
