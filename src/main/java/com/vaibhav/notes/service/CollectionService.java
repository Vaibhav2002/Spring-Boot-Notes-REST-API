package com.vaibhav.notes.service;

import com.vaibhav.notes.exceptions.CollectionException;
import com.vaibhav.notes.models.Collection;
import com.vaibhav.notes.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollectionService {


    CollectionRepository collectionRepository;

    @Autowired
    public CollectionService(@Qualifier("collectionRepo") CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public List<Collection> getCollections(Long userId) {
        return collectionRepository.getAllCollection(userId);
    }

    public Collection addCollection(Collection collection) {
        return collectionRepository.save(collection);
    }

    public Collection deleteCollection(Long collectionId) throws CollectionException {
        if(!collectionRepository.existsById(collectionId))
            throw new CollectionException("Collection does not exist");
        else {
            Optional<Collection> old = collectionRepository.findById(collectionId);
            collectionRepository.deleteById(collectionId);
            return old.get();
        }
    }

    //admin only
    public List<Collection> getAllCollectionsOfAllUsers(){
        return collectionRepository.findAll();
    }
}
