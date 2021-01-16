package io.kang.bank.web;

import io.kang.bank.application.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.Map;

@RestController
public class TransferController {
    @Autowired
    private TransferService transferSerivce;

    @PostMapping("transfer")
    public String doTransfer(@RequestBody Map params) {
        String sourceAccountId = (String)params.get("sourceAccountId");
        String targetAccountNumber = (String)params.get("targetAccountNumber");
        BigDecimal targetAmount = new BigDecimal((String)params.get("targetAmount"));
        String targetCurrency = (String) params.get("targetCurrency");

        return String.valueOf(transferSerivce.transfer(sourceAccountId, targetAccountNumber, targetAmount, targetCurrency));
    }
}
