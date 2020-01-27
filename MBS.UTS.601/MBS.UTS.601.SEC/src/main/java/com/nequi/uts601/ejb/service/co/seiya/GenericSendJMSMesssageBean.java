package com.nequi.uts601.ejb.service.co.seiya;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.nequi.uts601.ejb.util.Constant;

@Stateless(mappedName = Constant.COMMON_STRING_GENERIC_JMS_MESSAGE)
@LocalBean
public class GenericSendJMSMesssageBean {

    /**
     * JMS que manda un object
     * 
     * @param backoutRequestType
     * @param operation
     * @throws JMSException
     * @throws NamingException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void sendJMS(Serializable backoutRequestType, String operation)
            throws JMSException, NamingException {

        InitialContext initialContext = null;
        Context myEnv = null;
        QueueConnectionFactory sampleQCF;
        QueueConnection queueConn = null;
        Queue backoutQ;
        ObjectMessage objMsg;

        QueueSender queueSender = null;
        QueueSession queueSession = null;

        try {
            initialContext = new InitialContext();
            myEnv = (Context) initialContext
                    .lookup(Constant.COMMON_STRING_ENVIROMENT);

            sampleQCF = (QueueConnectionFactory) myEnv
                    .lookup(Constant.COMMON_STRING_QUEUE_FACTORY);

            queueConn = (QueueConnection) sampleQCF.createConnection();

            queueSession = queueConn.createQueueSession(false, 0);

            backoutQ = (Queue) myEnv.lookup(operation);

            queueSender = queueSession.createSender(backoutQ);

            objMsg = queueSession.createObjectMessage();

            objMsg.setObject(backoutRequestType);

            queueSender.send(objMsg);

        } finally {

            if (null != queueSender) {
                queueSender.close();
            }

            if (null != queueSession) {
                queueSession.close();
            }

            if (null != queueConn) {
                queueConn.close();
            }

            if (null != myEnv) {
                myEnv.close();
            }

            if (null != initialContext) {
                initialContext.close();
            }
        }
    }

    /**
     * JMS para mandar un texto
     * 
     * @param jmsStringMessage
     * @param operation
     * @throws JMSException
     * @throws NamingException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void sendJmsStringMessage(String jmsStringMessage, String operation)
            throws JMSException, NamingException {

        InitialContext initialContext = null;
        Context myEnv = null;
        QueueConnectionFactory sampleQCF;
        QueueConnection queueConn = null;
        Queue backoutQ;
        TextMessage textMsg;

        QueueSender queueSender = null;
        QueueSession queueSession = null;

        try {
            initialContext = new InitialContext();
            myEnv = (Context) initialContext
                    .lookup(Constant.COMMON_STRING_ENVIROMENT);

            sampleQCF = (QueueConnectionFactory) myEnv
                    .lookup(Constant.COMMON_STRING_QUEUE_FACTORY);

            queueConn = (QueueConnection) sampleQCF.createConnection();

            queueSession = queueConn.createQueueSession(false,
                    Constant.COMMON_INT_ZERO);

            backoutQ = (Queue) myEnv.lookup(operation);

            queueSender = queueSession.createSender(backoutQ);

            textMsg = queueSession.createTextMessage();

            textMsg.setText(jmsStringMessage);

            queueSender.send(textMsg);

        } finally {

            if (null != queueSender) {
                queueSender.close();
            }

            if (null != queueSession) {
                queueSession.close();
            }

            if (null != queueConn) {
                queueConn.close();
            }

            if (null != myEnv) {
                myEnv.close();
            }

            if (null != initialContext) {
                initialContext.close();
            }
        }
    }

}
