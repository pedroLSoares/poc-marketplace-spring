package com.pedrolsoares.marketplace.util;

import com.pedrolsoares.marketplace.enums.ProductCategory;

import java.util.Map;

public class ProductEnumUtils {

    public static Map<String, ProductCategory> categoryMap(){
        return Map.of(
                "Tecnologia", ProductCategory.TECHNOLOGY,
                "Comida", ProductCategory.FOOD,
                "Casa", ProductCategory.HOME,
                "Vestuário", ProductCategory.CLOTHES,
                "Outros", ProductCategory.OTHER
        );
    }
}
