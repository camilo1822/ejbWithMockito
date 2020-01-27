/**
 * 
 */
package  com.nequi.uts601.ejb.util;

import com.ibm.xml.crypto.util.Base64;
import com.nequi.mdw.common.tracerv7.service.GenericLogger;
/**
 *
 */
public class MessageTracerUtil {

    /**
     * Constructor Privado
     */
    private MessageTracerUtil() {

    }

    /**
     * Método que forma una traza de Request Message y la registra en el log si
     * esta habilitado para mensaje muy largos
     * 
     * @param serviceName
     * @param messageID
     * @param serviceOperation
     * @param requestDate
     * @param serviceRegion
     * @param serviceVersion
     * @param body
     * @param logger
     */
    public static void traceRequestLargeMessage(String serviceName,
            String messageID, String serviceOperation, String requestDate,
            String serviceRegion, String serviceVersion, String body,
            GenericLogger logger) {

        StringBuilder traceInfo = buildRequestTraceInfo(serviceName, messageID,
                serviceOperation, requestDate, serviceRegion, serviceVersion,
                body, Boolean.TRUE);

        logger.traceInfo(traceInfo.toString());
    }

    /**
     * Método para trazar los JSON de Respuesta muy largas.
     * 
     * @param statusCode
     * @param messageID
     * @param responseDate
     * @param body
     * @param logger
     */
    public static void traceResponseLargeMessage(String statusCode,
            String messageID, String responseDate, String body,
            GenericLogger logger) {
        StringBuilder traceInfo = buildResponseTraceInfo(statusCode, messageID,
                responseDate, body, Boolean.TRUE);
        logger.traceInfo(traceInfo.toString());

    }

    /**
     * Método que construye la traza para el log del response.
     * 
     * @param statusCode
     * @param messageID
     * @param responseDate
     * @param body
     * @param largeMessage
     * @return StringBuilder
     */
    private static StringBuilder buildResponseTraceInfo(String statusCode,
            String messageID, String responseDate, String body,
            boolean largeMessage) {

        StringBuilder traceInfo;

        traceInfo = new StringBuilder(Constant.RESPONSE_INIT);

        traceInfo.append(Constant.STATUSCODE);
        traceInfo.append(statusCode);
        traceInfo.append(Constant.CLOSE);
        traceInfo.append(Constant.MESSAGEID);
        traceInfo.append(messageID);
        traceInfo.append(Constant.CLOSE);
        traceInfo.append(Constant.RSDATE);
        traceInfo.append(responseDate);
        traceInfo.append(Constant.CLOSE);
        traceInfo.append(Constant.PAYLOAD_RS);
        if (null != body) {
            traceInfo.append(Base64.encode(body.getBytes()));
            traceInfo.append(Constant.CLOSE);
        } else {
            traceInfo.append(Constant.NO_BODY);
        }
        return traceInfo;
    }

    /**
     * Método que construye la traza para el log del request.
     * 
     * @param serviceName
     * @param messageID
     * @param serviceOperation
     * @param requestDate
     * @param serviceRegion
     * @param serviceVersion
     * @param body
     * @param largeMessage
     * @return StringBuilder
     */
    private static StringBuilder buildRequestTraceInfo(String serviceName,
            String messageID, String serviceOperation, String requestDate,
            String serviceRegion, String serviceVersion, String body,
            boolean largeMessage) {

        StringBuilder traceInfo = new StringBuilder(
                Constant.REQUEST_INIT);

        traceInfo.append(serviceName);
        traceInfo.append(Constant.CLOSE);
        traceInfo.append(Constant.MESSAGEID);
        traceInfo.append(messageID);
        traceInfo.append(Constant.CLOSE);
        traceInfo.append(Constant.OPERATION);
        traceInfo.append(serviceOperation);
        traceInfo.append(Constant.CLOSE);
        traceInfo.append(Constant.RQDATE);
        traceInfo.append(requestDate);
        traceInfo.append(Constant.CLOSE);
        traceInfo.append(Constant.RQREGION);
        traceInfo.append(serviceRegion);
        traceInfo.append(Constant.CLOSE);
        traceInfo.append(Constant.RQVERSION);
        traceInfo.append(serviceVersion);
        traceInfo.append(Constant.CLOSE);
        traceInfo.append(Constant.PAYLOAD_RQ);

        if (null != body) {
            traceInfo.append(Base64.encode(body.getBytes()));
            traceInfo.append(Constant.CLOSE);
        } else {
            traceInfo.append(Constant.NO_BODY);
        }

        return traceInfo;
    }
}
