package com.pedrolsoares.marketplace.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedrolsoares.marketplace.dto.ResponseDTO;
import com.pedrolsoares.marketplace.dto.request.AddressDTO;
import com.pedrolsoares.marketplace.exception.AddressNotFoundException;
import com.pedrolsoares.marketplace.model.Address;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class AddressUtils {

    private static final String API_URL = "https://brasilapi.com.br/api/cep/v1/";

    public static Address validateAddress(final AddressDTO address) throws JsonProcessingException {
        try{
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(API_URL.concat(address.getZipcode().toString()), String.class);
            ObjectMapper om = new ObjectMapper();
            ResponseDTO r = om.readValue(result, ResponseDTO.class);

            return new Address(
                    address.getCountry(),
                    r.getState(),
                    r.getCity(),
                    r.getNeighborhood(),
                    r.getStreet(),
                    address.getNumber(),
                    address.getZipcode()
            );
        }catch (HttpClientErrorException.NotFound e){
            throw new AddressNotFoundException(e.getMessage());
        }

    }
}
