package com.pedrolsoares.marketplace.controller;

import com.pedrolsoares.marketplace.dto.request.transaction.FinishPurchaseDTO;
import com.pedrolsoares.marketplace.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaction")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/user/{id}/purchase-finish")
    public ResponseEntity<Object> finishPurchase(@PathVariable Long id, @RequestBody FinishPurchaseDTO finishPurchaseBody, Authentication authentication){
        String message = transactionService.finishPurchase(authentication.getName(), finishPurchaseBody);

        return ResponseEntity.ok(message);

    }
}
