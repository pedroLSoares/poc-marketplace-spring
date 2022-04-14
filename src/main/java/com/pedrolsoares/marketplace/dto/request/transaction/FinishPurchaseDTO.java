package com.pedrolsoares.marketplace.dto.request.transaction;

import com.pedrolsoares.marketplace.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FinishPurchaseDTO {

    private PaymentType paymentType;
}
