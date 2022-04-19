package com.pedrolsoares.marketplace.service;

import com.pedrolsoares.marketplace.dto.request.transaction.FinishPurchaseDTO;
import com.pedrolsoares.marketplace.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;


    public String finishPurchase(String username, FinishPurchaseDTO finishPurchaseBody) {
        List<Product> products = shoppingCartService.getCart(username);

        products.forEach(p -> {
            Product product = productService.findOne(p.getId());
            if(product.getQuantity() - p.getQuantity() < 0 ){
                throw new IllegalArgumentException("Not enough stock");
            }
            product.setQuantity(product.getQuantity() - p.getQuantityRequested());
            productService.updateProduct(product);
        });

        BigDecimal totalPrice = shoppingCartService.calculateCartPrice(products);

        PaymentService paymentService = new PaymentService();

        return paymentService.processPayment(finishPurchaseBody.getPaymentType(), totalPrice);

    }
}
