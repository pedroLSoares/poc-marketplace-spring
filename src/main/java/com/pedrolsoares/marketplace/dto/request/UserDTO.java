package com.pedrolsoares.marketplace.dto.request;

import com.pedrolsoares.marketplace.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    private String email;
    private String fullName;
    private String userName;
    private String password;

    public AppUser dtoToModel(){
        return new AppUser(
                email, fullName, userName, password
        );
    }
}
