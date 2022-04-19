package com.pedrolsoares.marketplace.assembler;

import com.pedrolsoares.marketplace.controller.StorageController;
import com.pedrolsoares.marketplace.model.Storage;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class StorageAssembler implements RepresentationModelAssembler<Storage, EntityModel<Storage>> {
    @Override
    public EntityModel<Storage> toModel(Storage entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(StorageController.class).getStorage(entity.getId())).withSelfRel(),
                linkTo(methodOn(StorageController.class).listAllStorage("1", "10")).withRel("storages")
                );
    }
}
