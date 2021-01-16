package io.kang.bank.messaging.impl;

import io.kang.bank.domain.types.AuditMessage;
import io.kang.bank.messaging.AuditMessageProducer;
import io.kang.bank.messaging.SendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuditMessageProducerImpl implements AuditMessageProducer {
    private final static Logger LOG = LoggerFactory.getLogger(AuditMessageProducerImpl.class);

    private static final String TOPIC_AUDIT_LOG = "TOPIC_AUDIT_LOG";

    @Override
    public SendResult send(AuditMessage message) {
        String messageBody = message.serialize(message);
        LOG.info("topic: {}, msg:{}", TOPIC_AUDIT_LOG, messageBody);
        return SendResult.success();
    }
}
