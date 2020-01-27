/**
 * 
 */
package com.nequi.uts601.ejb.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CommonUtilTest {

    @Test
    public void testResponseErrorMessage() {
        assertNotNull(com.nequi.uts601.ejb.util.CommonUtil
                .getCurrentTimeStampSpanish("yyyy-MM-dd HH:mm:ss"));
    }
}
