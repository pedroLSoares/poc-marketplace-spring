package com.pedrolsoares.marketplace.service;

import com.pedrolsoares.marketplace.enums.PaymentType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.Callable;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentType paymentType;
    private final BigDecimal value;

    public String processPayment() {
        Map<PaymentType, Callable<?>> translationMap = Map.of(
                PaymentType.CREDIT_CARD, this::creditCardPayment,
                PaymentType.DEBIT_CART, this::debitCardPayment,
                PaymentType.BILLET, this::billetCardPayment,
                PaymentType.PIX, this::pixPayment
        );

        try {
            return (String)translationMap.get(paymentType).call();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String creditCardPayment(){
        return "Pagamento com cartão de crédito";
    }

    private String debitCardPayment(){
        return "Pagamento com cartão de débito";
    }

    private String billetCardPayment(){
        return "Pagamento no boleto";
    }

    private String pixPayment(){
        return "Pagamento por pix";
    }
}
