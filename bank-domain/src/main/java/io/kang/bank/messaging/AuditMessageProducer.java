package io.kang.bank.messaging;

import io.kang.bank.domain.types.AuditMessage;

public interface AuditMessageProducer {
    SendResult send(AuditMessage message);
}
