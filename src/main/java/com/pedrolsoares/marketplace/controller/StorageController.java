package com.pedrolsoares.marketplace.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pedrolsoares.marketplace.dto.request.StorageDTO;
import com.pedrolsoares.marketplace.dto.request.StorageProductsDTO;
import com.pedrolsoares.marketplace.model.Address;
import com.pedrolsoares.marketplace.model.Storage;
import com.pedrolsoares.marketplace.service.StorageService;
import com.pedrolsoares.marketplace.util.AddressUtils;
import com.pedrolsoares.marketplace.assembler.StorageAssembler;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/storage")
@AllArgsConstructor
public class StorageController {

    private final StorageService storageService;
    private final StorageAssembler assembler;

    @PostMapping
    public ResponseEntity<?> createStorage(@RequestBody @Valid StorageDTO storageDTO, UriComponentsBuilder uriBuilder) throws JsonProcessingException {
        Address address = AddressUtils.validateAddress(storageDTO.getLocation());

        Storage storageToCreate = new Storage(
                storageDTO.getName(),
                address,
                new ArrayList<>()
        );

        Storage created = storageService.createStorage(storageToCreate);

        EntityModel<Storage> entityModel = assembler.toModel(created);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }

    @GetMapping
    public CollectionModel<EntityModel<Storage>> listAllStorage(){
        List<Storage> storageList = storageService.listStorage();

        List<EntityModel<Storage>> storages = storageList.stream().map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(storages,
                linkTo(methodOn(StorageController.class).listAllStorage()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Storage> getStorage(@PathVariable Long id){
        Storage storage = storageService.findById(id);

        return assembler.toModel(storage);
    }

    @PostMapping("/{id}")
    public EntityModel<Storage> addProducts(@PathVariable Long id, @RequestBody @Valid StorageProductsDTO productsDTO) {
        Storage updatedStorage = storageService.addProducts(id, productsDTO.getProducts());

        return assembler.toModel(updatedStorage);
    }


}
