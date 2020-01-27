/**
 * 
 */
package com.nequi.uts601.ejb.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public final class Constant {

    // PARAMETROS GENERALES

    public static final String COMMON_STRING_COLON = ":";
    public static final String COMMON_STRING_SLASH = "/";
    public static final int COMMON_INT_ZERO = 0;
    public static final int COMMON_INT_ONE = 1;
    public static final int COMMON_INT_TWO = 2;

    public static final String COMMON_STRING_ZERO = "0";
    public static final String COMMON_STRING_ONE = "1";
    public static final String COMMON_STRING_TWO = "2";
    public static final String COMMON_STRING_FOUR = "4";
    public static final String COMMON_STRING_FIVE = "5";
    public static final String COMMON_STRING_SIX = "6";

    public static final String COMMON_FORMAT_DATE_TO_FRONT = "yyyy-MM-dd HH:mm:ss";

    public static final String COMMON_STRING_SUCCESS_MAYUS = "SUCCESS";
    public static final String COMMON_STRING_SUCCESS_CODE = COMMON_STRING_ZERO;

    public static final String COMMON_STRING_EMPTY = "";
    public static final String COMMON_STRING_BLANK_SPACE = " ";
    public static final String COMMON_STRING_IPADDRESS = "ipAddress";
    public static final String COMMON_STRING_NA = "N/A";
    public static final String COMMON_STRING_REGION_CORE = "001";
    public static final String COMMON_STRING_REGION_PANAMA = "P001";
    public static final String COMMON_STRING_REGION_COLOMBIA = "C001";

    public static final String COMMON_STRING_DEFAULT_VERSION = "1.0.0";
    public static final String COMMON_STRING_IKKI = "1.0.1";
    public static final String COMMON_STRING_YOGA = "1.0.2";

    // SISTEMAS
    public static final String SYS_MBS = "MBS";
    public static final String APP_SYSTEM = "APP";
    public static final String MDW_SYSTEM = "MDW";
    public static final String DB_SYSTEM = "BD_SHERPA";
    public static final String API_SYSTEM = "API";
    public static final String AGI_SYSTEM = "IS_API_GATEWAY";

    // CHANNEL
    public static final Map<String, String> CHANNELS;
    public static final String CHANNEL_APP = "mf-001";

    static {
        CHANNELS = new HashMap<>();
        CHANNELS.put(CHANNEL_APP, APP_SYSTEM);
    }

    // DEBUG
    public static final String INITIAL_RQ = "Request entrante: ";

    public static final String COMMON_STRING_SERVICE_ID = "MBS_UTS_601";
    public static final String COMMON_STRING_SERVICE_NAME = "MBS_UtilServices_601";

    public static final String COMMON_STRING_CONTAINER_TYPE_ID = "WAS-Node";
    public static final String COMMON_STRING_CONTAINER_TYPE_NAME = "WebSphere Application Server";

    public static final String INTEGRATION_CLASIFICATION = "INTEGRATION";

    // Constantes para utilidades del TraceManager
    public static final String CLOSE = "]";
    public static final String OPEN = "[";
    public static final String MESSAGEID = "[MessageID:";
    public static final String STATUSCODE = "[StatusCode:";
    public static final String CLIENTID = "[clientID:";
    public static final String CLIENT_TYPE = "[clientType:";
    public static final String OPERATION = "[Operation:";
    public static final String REQUEST_INIT = "[REQUEST][ServiceName:";
    public static final String RQDATE = "[Request Date:";
    public static final String RQREGION = "[Request Region:";
    public static final String RQVERSION = "[Request Version:";
    public static final String PAYLOAD_RQ = "[PAYLOAD RQ: ]";
    public static final String RESPONSE_INIT = "[RESPONSE]";
    public static final String RSDATE = "[Response Date:";
    public static final String RSREGION = "[Response Region:";
    public static final String RSVERSION = "[Response Version:";
    public static final String PAYLOAD_RS = "[PAYLOAD RS: ]";
    public static final String NO_BODY = "NO PAYLOAD";
    public static final String COMMON_STRING_GET = "get";

    // Constantes DIFIE HELLMAN
    public static final String REGISTRY_SERVICE_NAME_DIFFIE_HELLMAN = "SecurityManager";
    public static final String REGISTRY_OPERATION_DIFFIE_HELLMAN = REGISTRY_SERVICE_NAME_DIFFIE_HELLMAN;
    public static final String COMMON_STRING_DECRYPT = "decrypt";
    public static final String COMMON_STRING_ENCRYPT = "encrypt";

    public static final String MESSAGE_RECEIVED_AT = "MessageReceived at:";

    // PARAMETROS DE HOMOLOGACION DE ERRORES
    public static final String ERROR_CODE_DB_CLIENT_NOT_FOUND = "1L";
    public static final String ERROR_CODE_DB_POCKET = "2L";
    public static final String ERROR_CODE_DB_TRANSACTION = "3L";
    public static final String ERROR_CODE_DIFFIE_HELLMAN = "5L";
    public static final String ERROR_CODE_INVALID_DATA = "6L";
    public static final String ERROR_CODE_NO_RESULTS_BD = "9L";
    public static final String ERROR_CODE_PARSING_ERROR = "10L";
    public static final String ERROR_CODE_COMMUNICATION_IIB = "18L";
    public static final String ERROR_CODE_IIB_TIME_OUT_504 = "504";
    public static final String ERROR_CODE_IIB_TIME_OUT_505 = "505";
    public static final String ERROR_CODE_IIB_TIME_OUT_510 = "510";
    public static final String ERROR_CODE_IIB_TIME_OUT_550 = "550";
    public static final String ERROR_CODE_MBS_FIS_602 = "24L";
    public static final String ERROR_PARSE = "17L";
    public static final String ERROR_DEFAULT_CODE = "500";
    public static final String ERROR_CODE_DB_OPERATION = "450";
    public static final String ERROR_CODE_IIB_TIME_OUT = "10-504";
    public static final String ERROR_CODE_INVALID_DATA_MDW = "38L";
    public static final String ERROR_CODE_TIMEOUT_BROKER = "11-18L";
    public static final String ERROR_CODE_REST_CONSUMER_ERROR = "26L";
    public static final String ERROR_CODE_INVALID_PARAMETER = "40L";
    public static final String ERROR_CODE_PARAMETER_NOT_FOUND = "456";
    public static final String ERROR_CODE_AVAILABILTY = "28L";
    public static final String ERROR_CODE_REPORT = "42L";
    public static final String ERROR_CODE_REST = "45L";
    public static final String ERROR_CODE_KMS = "85L";

    // MENSAJES DE ERROR MDW
    public static final String ERROR_MESSAGE_DB_QUERY_NO_RESULTS = "Error No se encuentra registro en BD";
    public static final String ERROR_MESSAGE_API_GATEWAY_COMMUNICATION = "Error al consumir ApiGateway";
    public static final String ERROR_MESSAGE_GET_POCKECT_NO_RESULTS = "Error consultando bolsillo";
    public static final String ERROR_MESSAGE_INVALID_PAREMETER = "Error de validación de campos - campos vacios";
    public static final String ERROR_MESSAGE_INVALID_PAREMETER_DFH = "Error de validación de campos de Seguridad - falta llave para cifrar";
    public static final String ERROR_MESSAGE_CUSTOMER_DOC_TYPE_NOT_ALLOWED = "Error el tipo de documento del cliente no esta permitido";
    public static final String ERROR_MESSAGE_IIB_SOFTOKEN = "Error al tratar de validar softToken ";
    public static final String ERROR_MESSAGE_IIB_COMMUNICATION = "Error de Proveedor: Broker";
    public static final String ERROR_MESSAGE_DIFFIE_HELLMAN_DECRYPT = "ERROR - Se obtuvo código %1$s al descifrar mensaje desde el servicio %2$s";
    public static final String ERROR_MESSAGE_SERCIVE_REGISTRY = "ERROR - Se obtuvo código %1$s al llamar service registry";
    public static final String ERROR_PARSING = "Error al Parsear el JSON:";
    public static final String ERROR_MESSAGE_DIFFIE_HELLMAN = "Error al consumir servicio de diffie hellman";
    public static final String ERROR_MESSAGE_DB_CLIENT_NOT_FOUND = "Error al consultar el cliente no encontrado";
    public static final String ERROR_MESSAGE_PARSING_ERROR = "Error al parsear mensaje";
    public static final String ERROR_DEFAULT_MESSAGE = "¡Uy! Algo va mal, ya vamos a solucionarlo.";
    public static final String ERROR_TO_PARSE_RQ = "Error al parsear el request";
    public static final String ERROR_TO_CALL_REST = "Error al invocar el servicio rest";
    public static final String ERROR_DB_OPERATION = "Error genérico de base de datos";
    public static final String ERROR_MESSAGE_JMS_MESSAGE = "Error al procesar mensaje JMS";
    public static final String ERROR_DEFAULT_MSG = "Middleware Internal Error";
    public static final String ERROR_MESSAGE_DB_PARAMETER_NOT_FOUND = "Parametro no encontrado.";
    public static final String ERROR_REPORT = "Error generando reporte";
    public static final String ERROR_AMAZON_CONNECTION = "Error al intentar conexión con Amazon";
    public static final String ERROR_AMAZON_KMS_DECRYPT = "Error al intentar descifrar con KMS";

    // HOMOLOGADOR
    public static final String HOMOLOGATOR_SERVICE_NAME = "HomologatorService";
    public static final String HOMOLOGATOR_SERVICE_OPERATION = "HomologateError";
    public static final String ERROR_TO_HOMOLOGATE_ERROR = "Error al homologar un error";
    public static final String PARSE_FAILED = "Error al parse un object a un String";
    public static final String ERROR_TO_PARSE_RESPONSE = "Error al parsear el response";
    public static final String ERROR_REST_CLIENT_HOMOLOGATE_ERROR = "Error por cliente REST al homologar error";
    public static final String ERROR_BACKOUT = "Error al intentar enviar JMS";
    public static final Integer CONVERTO_TO_MILISECONDS_1000 = 1000;

    public static final String COMMON_STRING_PERSISTENCE_UNIT_NAME = "JPAManager";

    public static final String COMMON_STRING_CHECKAVAILABILITY_FACTORY = "CheckAvailabilityFactory";
    public static final String COMMON_STRING_SEND_DOCUMENT_FACTORY = "SendDocumentFactory";
    public static final String COMMON_STRING_CO_CHECKAVAILABILITY_FACTORY = "COCheckAvailabilityFactory";
    public static final String COMMON_STRING_CO_SEND_DOCUMENT_FACTORY = "COSendDocumentFactory";
    public static final String COMMON_STRING_COCHECKAVAILABILITY_SERVICE_SEIYA = "COCheckAvailabilityServiceSeiya";
    public static final String COMMON_STRING_SEND_DOCUMENT_SERVICE_SEIYA = "COSendDocumentServiceSeiya";

    // Facade
    public static final String FACADE_UTIL_SERVICES_PATH = "/services/UtilServices";
    public static final String SERVICE_OPERATION_CHECKAVAILABILITY = "/checkAvailability";
    public static final String SERVICE_OPERATION_SEND_DOCUMENT = "/sendDocument";

    public static final String SERVICE_NAME_API_GATEWAY = "APIGateway406Service";
    public static final String SERVICE_OPERATION_SEND_API_REQUEST = "sendApiRequest";
    public static final String SERVICE_OPERATION_AGI_GET_CARDS_BY_CLIENT = "operationAPIGatewayGetCardsByClient";

    public static final String COMMON_STRING_QUEUE_FACTORY = "jms/SHP-CF";
    public static final String COMMON_STRING_ENVIROMENT = "java:comp/env";
    public static final String COMMON_STRING_GENERIC_JMS_MESSAGE = "GenericSendJMSMesssageBean";

    // Parametros
    public static final String COMMON_STRING_AVAILABILITY_PARAMETERS_CODE = "203";
    public static final String COMMON_STRING_INITIAL_HOUR_ATTRIBUTE = "initialHour";
    public static final String COMMON_STRING_FINAL_HOUR_ATTRIBUTE = "finalHour";
    public static final String COMMON_STRING_CHANNEL_ID = "MBS-UTS-601";
    public static final String COMMON_STRING_REPORT_OUT_FILE = "ReportOutFile";

    public static final String COMMON_STRING_TRUE = "true";
    public static final String FORMAT_DATE_PATTERN_DOCUMENTS = "EEEE, d 'de' MMMM 'de' yyyy";
    public static final String CONSTANT_LANGUAGE_SPANISH_LOWER_CASE = "es";
    public static final String CONSTANT_LANGUAGE_SPANISH_UPPER_CASE = "ES";
    public static final String PATTERN_STRING_REPORT_DATE = "REPORT_DATE";

    public static final String COMMON_STRING_OP_GET_SERVICE_EMAIL = "EmailService";
    public static final String COMMON_STRING_OP_SEND_EMAIL_GENERAL = "sendEmailGeneral";

    private Constant() {
    }

}
