package com.pedrolsoares.marketplace.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pedrolsoares.marketplace.dto.request.StorageDTO;
import com.pedrolsoares.marketplace.dto.request.StorageProductsDTO;
import com.pedrolsoares.marketplace.model.Address;
import com.pedrolsoares.marketplace.model.Storage;
import com.pedrolsoares.marketplace.service.StorageService;
import com.pedrolsoares.marketplace.util.AddressUtils;
import com.pedrolsoares.marketplace.assembler.StorageAssembler;
import com.pedrolsoares.marketplace.util.IntegerUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
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
    public CollectionModel<EntityModel<Storage>> listAllStorage(
            @RequestParam(required = false, defaultValue = "0", value = "page") String pageNumber,
            @RequestParam(required = false, defaultValue = "10", value = "size") String sizeNumber
    ){
        Integer page = IntegerUtils.parseToInt(pageNumber, 0, false);
        Integer size = IntegerUtils.parseToInt(sizeNumber, 10, false);
        Page<Storage> storageList = storageService.listStorage(page > 0 ? page - 1 : page, size);

        List<EntityModel<Storage>> storages = storageList.stream().map(assembler::toModel).collect(Collectors.toList());


        List<Link> links = new ArrayList<>(List.of(
                linkTo(methodOn(StorageController.class).listAllStorage(pageNumber, sizeNumber)).withSelfRel()
        ));

        if(storageList.getTotalPages() > page + 1){
            links.add(linkTo(methodOn(StorageController.class).listAllStorage(String.valueOf(page + 1), sizeNumber)).withRel("next_page"));
        }


        return CollectionModel.of(storages,links);

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
