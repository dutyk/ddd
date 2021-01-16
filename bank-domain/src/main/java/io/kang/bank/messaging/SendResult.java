package io.kang.bank.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendResult {
    private String code;
    private String msg;

    public static SendResult success() {
        return new SendResult("00", "success");
    }
}
