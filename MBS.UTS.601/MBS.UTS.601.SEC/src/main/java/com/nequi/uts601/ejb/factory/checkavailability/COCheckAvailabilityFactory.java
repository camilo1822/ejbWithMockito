
package com.nequi.uts601.ejb.factory.checkavailability;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import com.nequi.uts601.ejb.service.ServiceBean;
import com.nequi.uts601.ejb.util.Constant;

/**
 * Session Bean implementation class COCheckAvailabilityFactory
 */
@Singleton(mappedName =  Constant.COMMON_STRING_CO_CHECKAVAILABILITY_FACTORY)
@LocalBean
public class COCheckAvailabilityFactory {

    @EJB(beanName = Constant.COMMON_STRING_COCHECKAVAILABILITY_SERVICE_SEIYA)
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
