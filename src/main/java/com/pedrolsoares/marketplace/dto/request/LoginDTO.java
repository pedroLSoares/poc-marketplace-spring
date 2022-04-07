package com.pedrolsoares.marketplace.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO {

    private String userName;
    private String password;
}
