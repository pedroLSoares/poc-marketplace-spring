package com.pedrolsoares.marketplace.service;

import com.pedrolsoares.marketplace.model.Storage;
import com.pedrolsoares.marketplace.repository.StorageRepository;
import lombok.AllArgsConstructor;
import org.hibernate.PropertyNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StorageService {

    private final StorageRepository storageRepository;

    public Storage createStorage(Storage storage){
        return storageRepository.save(storage);
    }

    public List<Storage> listStorage(){
        return storageRepository.findAll();
    }

    public Storage findById(final Long id){
        Optional<Storage> optionalStorage = storageRepository.findById(id);

        return optionalStorage.orElseThrow(() -> new PropertyNotFoundException("Storage not found"));
    }
}
