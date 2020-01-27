package com.nequi.uts601.ejb.service.co.seiya.senddocument;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.amazonaws.services.kms.AWSKMS;
import com.lowagie.text.pdf.codec.Base64;
import com.nequi.cmm.consumer.exception.CommonUtilException;
import com.nequi.cmm.consumer.exception.RestClientException;
import com.nequi.cmm.consumer.registry.ServiceRegistry;
import com.nequi.cmm.consumer.rest.util.CallServiceUtil;
import com.nequi.cmm.consumer.util.CommonServiceConsumerUtil;
import com.nequi.cmm.consumer.util.UtilJSON;
import com.nequi.cmm.report.exception.ReportException;
import com.nequi.cmm.report.service.ServiceReportGenerator;
import com.nequi.mdw.common.jpa.exception.JPAException;
import com.nequi.mdw.common.jpa.model.entities.Parametro;
import com.nequi.mdw.common.jpa.service.ParameterJPAService;
import com.nequi.mdw.common.jpa.service.impl.ParameterJPAServiceIMPL;
import com.nequi.mdw.common.tracerv7.service.GenericLogger;
import com.nequi.mdw.common.tracerv7.service.LoggerFactory;
import com.nequi.mdw.common.tracerv7.service.ServiceTypeEnum;
import com.nequi.uts601.ejb.exception.MBSException;
import com.nequi.uts601.ejb.service.ServiceBean;
import com.nequi.uts601.ejb.util.CommonUtil;
import com.nequi.uts601.ejb.util.Constant;
import com.nequi.uts601.ejb.util.IntegrationUtil;
import com.nequi.uts601.messaging.services.integration.EmailGeneralRequestType;
import com.nequi.uts601.messaging.services.integration.IntegrationRS;
import com.nequi.uts601.messaging.services.integration.RequestBodyType;
import com.nequi.uts601.messaging.services.seiya.DocumentInfoType;
import com.nequi.uts601.messaging.services.seiya.MessageRQ;
import com.nequi.uts601.messaging.services.seiya.MessageRS;
import com.nequi.uts601.messaging.services.seiya.RequestHeaderType;
import com.nequi.uts601.messaging.services.seiya.SendDocumentRQType;
import com.nequi.uts601.messaging.services.seiya.SendDocumentRSType;

import net.sf.jasperreports.export.PdfExporterConfiguration;

/**
 * Session Bean implementation class COSendDocumentServiceSeiya
 * 
 *
 */
@Stateless(mappedName = Constant.COMMON_STRING_SEND_DOCUMENT_SERVICE_SEIYA)
@LocalBean
public class COSendDocumentServiceSeiya extends CallServiceUtil
        implements ServiceBean {
    @PersistenceContext(unitName = Constant.COMMON_STRING_PERSISTENCE_UNIT_NAME)
    private EntityManager em;
    private ServiceRegistry serviceRegistry;

    private GenericLogger logger;
    private ParameterJPAService parameterJPAService;

    @PostConstruct
    void init() {
        logger = LoggerFactory.getLogger(ServiceTypeEnum.BUSSINES,
                COSendDocumentServiceSeiya.class);
        this.parameterJPAService = new ParameterJPAServiceIMPL();
    }

    /**
     * Metodo que implementa de la interfaz {@link ServiceBean}
     * 
     * @see ServiceBean#executeOperation(String)
     */
    @Override
    public String executeOperation(String request) {

        MessageRS messageRS = null;
        MessageRQ messageRQ = null;
        RequestHeaderType requestHeader = null;
        String messageId = Constant.COMMON_STRING_EMPTY;
        String consumerId = Constant.COMMON_STRING_EMPTY;
        SendDocumentRQType sendDocumentRQType = null;
        String response = null;
        try {
            serviceRegistry = ServiceRegistry.getInstance();

            messageRQ = UtilJSON.parsePayloadWithJaxbAnnotation(request,
                    MessageRQ.class);

            requestHeader = messageRQ.getRequestMessage().getHeader();

            messageId = requestHeader.getMessageID();

            if (null != requestHeader.getConsumer()) {
                consumerId = requestHeader.getConsumer().getId();
            }

            sendDocumentRQType = messageRQ.getRequestMessage().getBody()
                    .getSendDocumentRQ();

            // mandamos correo con adjunto
            messageRS = sendPdfMail(messageRQ, sendDocumentRQType, messageId,
                    consumerId);

        } catch (JPAException e) {

            logger.traceError(Constant.ERROR_DB_OPERATION, e, messageId,
                    consumerId);
            messageRS = CommonUtil.responseErrorMessage(requestHeader,
                    Constant.ERROR_DEFAULT_CODE, Constant.DB_SYSTEM,
                    Constant.APP_SYSTEM, logger, serviceRegistry);

        } catch (CommonUtilException e) {

            logger.traceError(Constant.ERROR_MESSAGE_PARSING_ERROR, e,
                    messageId, consumerId);
            messageRS = CommonUtil.responseErrorMessage(requestHeader,
                    Constant.ERROR_PARSE, Constant.MDW_SYSTEM,
                    Constant.APP_SYSTEM, logger, serviceRegistry);

        } catch (RestClientException e) {
            logger.traceError(Constant.ERROR_TO_CALL_REST, e, messageId,
                    consumerId);
            messageRS = CommonUtil.responseErrorMessage(requestHeader,
                    Constant.ERROR_CODE_REST_CONSUMER_ERROR, Constant.SYS_MBS,
                    Constant.APP_SYSTEM, logger, serviceRegistry);
        }
        SendDocumentRSType sendDocumentRSType = new SendDocumentRSType();
        messageRS.getResponseMessage().getBody()
                .setSendDocumentRS(sendDocumentRSType);
        response = CommonUtil.generateResponseAsString(messageRS, logger,
                messageId, consumerId);
        return response;
    }

    /**
     * Metodo que arma pdf y lo manda por correo
     * 
     * @param messageRQ
     * @param sendDocumentRQType
     * @param messageId
     * @param consumerId
     * @return {@code MessageRS}
     * @throws JPAException
     * @throws CommonUtilException
     * @throws RestClientException
     */
    private MessageRS sendPdfMail(MessageRQ messageRQ,
            SendDocumentRQType sendDocumentRQType, String messageId,
            String consumerId)
            throws JPAException, CommonUtilException, RestClientException {

        RequestHeaderType headerType = messageRQ.getRequestMessage()
                .getHeader();
        // Consultamos parametros
        List<Parametro> parameters = parameterJPAService.getRegionParameter(
                sendDocumentRQType.getParameterType(),
                headerType.getDestination().getServiceRegion(), em);

        // Armamos documentos
        List<DocumentInfoType> documentInfoTypes = sendDocumentRQType
                .getDocumentInfo();
        List<String> files = new ArrayList<>();
        List<String> names = new ArrayList<>();
        String file;
        for (DocumentInfoType documentInfoType : documentInfoTypes) {
            file = buildPdf(sendDocumentRQType, documentInfoType, parameters,
                    messageId, consumerId);
            if (null == file) {

                return CommonUtil.responseErrorMessage(headerType,
                        Constant.ERROR_CODE_REPORT, Constant.MDW_SYSTEM,
                        Constant.APP_SYSTEM, logger, serviceRegistry);
            }
            files.add(file);
            names.add(documentInfoType.getDocumentName());
        }

        // Enviamos correo
        return sendEmail(messageRQ, sendDocumentRQType, files, names, messageId,
                consumerId);
    }

    /**
     * Metodo para enviar correo
     * 
     * @param messageRQ
     * @param sendDocumentRQType
     * @param files
     * @param content
     * @param names
     * @param messageId
     * @param consumerId
     * @return {@code MessageRS}
     * @throws CommonUtilException
     * @throws RestClientException
     */
    private MessageRS sendEmail(MessageRQ messageRQ,
            SendDocumentRQType sendDocumentRQType, List<String> files,
            List<String> names, String messageId, String consumerId)
            throws CommonUtilException, RestClientException {

        RequestBodyType requestBodyObjectType = new RequestBodyType();
        EmailGeneralRequestType emailGeneralRequestType = IntegrationUtil
                .getEmailGeneralRequestType(sendDocumentRQType.getMailBodyId(),
                        sendDocumentRQType.getFrom(),
                        sendDocumentRQType.getExtension(), files,
                        IntegrationUtil.getListContentType(
                                sendDocumentRQType.getContent()),
                        names, sendDocumentRQType.getTo());
        requestBodyObjectType.setEmailGeneralRequest(emailGeneralRequestType);

        IntegrationRS integrationRS = IntegrationUtil.callIntegrationService(
                messageRQ, serviceRegistry,
                Constant.COMMON_STRING_OP_SEND_EMAIL_GENERAL,
                Constant.COMMON_STRING_OP_GET_SERVICE_EMAIL,
                Constant.COMMON_STRING_DEFAULT_VERSION, requestBodyObjectType,
                logger);

        if (!IntegrationUtil.successResponse(integrationRS)) {

            logger.traceError(Constant.ERROR_TO_CALL_REST,
                    new MBSException(Constant.ERROR_TO_CALL_REST), messageId,
                    consumerId);
            return CommonUtil.responseErrorMessage(
                    messageRQ.getRequestMessage().getHeader(),
                    Constant.ERROR_CODE_REST, Constant.MDW_SYSTEM,
                    Constant.APP_SYSTEM, logger, serviceRegistry);
        }

        return CommonUtil.getResponseStructure(
                messageRQ.getRequestMessage().getHeader(),
                Constant.COMMON_STRING_SUCCESS_CODE,
                Constant.COMMON_STRING_SUCCESS_MAYUS);
    }

    /**
     * Metodo que devuelve base64 de un PDF
     *
     * @param sendDocumentRQType
     * @param documentInfoType
     * @param parameters
     * @param messageId
     * @param consumerId
     * @return {@code String}
     * @throws JPAException
     */
    private String buildPdf(SendDocumentRQType sendDocumentRQType,
            DocumentInfoType documentInfoType, List<Parametro> parameters,
            String messageId, String consumerId) {

        try (ByteArrayOutputStream outputFile = new ByteArrayOutputStream()) {

            Parametro sourceFile = CommonUtil.getParameterByName(parameters,
                    documentInfoType.getParameterName());

            Map<String, Object> reportParameter = new HashMap<>();
            String reportDate = CommonUtil.getCurrentTimeStampSpanish(
                    Constant.FORMAT_DATE_PATTERN_DOCUMENTS);
            reportDate = CommonServiceConsumerUtil
                    .generateString(
                            reportDate
                                    .substring(Constant.COMMON_INT_ZERO,
                                            Constant.COMMON_INT_ONE)
                                    .toUpperCase(),
                            reportDate.substring(Constant.COMMON_INT_ONE));
            reportParameter.put(Constant.PATTERN_STRING_REPORT_DATE,
                    reportDate);

            String pass = Constant.COMMON_STRING_EMPTY;
            // validamos si reporte debe tener clave
            if (Constant.COMMON_STRING_TRUE
                    .equalsIgnoreCase(documentInfoType.getSecurity())) {

                // Obtenemos clave
                pass = getPass(sendDocumentRQType.getPassword(), messageId,
                        consumerId);

                if (null == pass || pass.isEmpty()) {

                    logger.traceError(Constant.ERROR_AMAZON_KMS_DECRYPT,
                            new MBSException(Constant.ERROR_AMAZON_KMS_DECRYPT),
                            messageId, consumerId);
                    return null;
                }

                reportParameter.put(
                        PdfExporterConfiguration.PROPERTY_USER_PASSWORD, pass);
                reportParameter.put(PdfExporterConfiguration.PROPERTY_ENCRYPTED,
                        Boolean.TRUE);
            }

            ServiceReportGenerator.buildEncryptedPdfFromJSON(
                    sourceFile.getValor(), reportParameter,
                    documentInfoType.getInfo(), pass, null, outputFile);

            // retornamos base64
            return Base64.encodeBytes(outputFile.toByteArray());

        } catch (ReportException | IOException e) {

            logger.traceError(Constant.ERROR_REPORT, e, messageId, consumerId);
            return null;
        }

    }

    /**
     * Metodo para desencriptar clave
     * 
     * @param encryptPass
     * @param messageId
     * @param consumerId
     * @return {@code String}
     */
    private String getPass(String encryptPass, String messageId,
            String consumerId) {

        AWSKMS awsKms = CommonUtil.getAWSKMSClient(logger, messageId,
                consumerId);
        if (null == awsKms) {
            return null;
        } else {
            return CommonUtil.getKMSdecrypt(encryptPass, awsKms, messageId,
                    consumerId, logger);
        }
    }

}
