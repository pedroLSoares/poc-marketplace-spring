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
            ResponseDTO response = restTemplate.getForObject(API_URL.concat(address.getZipcode().toString()), ResponseDTO.class);

            if(response == null) {
                throw new AddressNotFoundException("CEP não encontrado");
            }

            return new Address(
                    address.getCountry(),
                    response.getState(),
                    response.getCity(),
                    response.getNeighborhood(),
                    response.getStreet(),
                    address.getNumber(),
                    address.getZipcode()
            );
        }catch (HttpClientErrorException.NotFound e){
            throw new AddressNotFoundException(e.getMessage());
        }

    }
}
