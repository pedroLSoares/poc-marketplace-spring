package com.pedrolsoares.marketplace.dto.response;

import com.pedrolsoares.marketplace.model.Address;
import com.pedrolsoares.marketplace.model.Storage;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class StorageListDTO {

    private String name;
    private Address location;
    private URI uri;

    public static List<StorageListDTO> modelToDto(final List<Storage> storageList, UriComponentsBuilder uriBuilder, String basePath){
        return storageList.stream().map(storage -> {
            URI uri = uriBuilder.replacePath(basePath.concat("/{id}")).build(storage.getId());

            return new StorageListDTO(
                    storage.getName(),
                    storage.getLocation(),
                    uri
            );
        }).collect(Collectors.toList());
    }
}
