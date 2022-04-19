package com.pedrolsoares.marketplace.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pedrolsoares.marketplace.dto.request.StorageDTO;
import com.pedrolsoares.marketplace.dto.response.StorageListDTO;
import com.pedrolsoares.marketplace.model.Address;
import com.pedrolsoares.marketplace.model.Storage;
import com.pedrolsoares.marketplace.service.StorageService;
import com.pedrolsoares.marketplace.util.AddressUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/storage")
@AllArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @PostMapping
    public ResponseEntity<?> createStorage(@RequestBody @Valid StorageDTO storageDTO, UriComponentsBuilder uriBuilder) throws JsonProcessingException {
        Address address = AddressUtils.validateAddress(storageDTO.getLocation());

        Storage storageToCreate = new Storage(
                storageDTO.getName(),
                address,
                new ArrayList<>()
        );

        Storage created = storageService.createStorage(storageToCreate);

        URI uri = uriBuilder.path("/api/v1/storage/{id}").buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<?> listAllStorage(UriComponentsBuilder uriBuilder){
        List<Storage> storageList = storageService.listStorage();

        List<StorageListDTO> responseList = StorageListDTO.modelToDto(storageList, uriBuilder, "/api/v1/storage");

        return ResponseEntity.ok(responseList);
    }

}
