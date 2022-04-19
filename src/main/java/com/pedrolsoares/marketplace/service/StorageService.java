package com.pedrolsoares.marketplace.service;

import com.pedrolsoares.marketplace.model.Storage;
import com.pedrolsoares.marketplace.repository.StorageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
