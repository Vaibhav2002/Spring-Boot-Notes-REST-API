package com.vaibhav.notes.controller;

import com.vaibhav.notes.models.Collection;
import com.vaibhav.notes.service.CollectionService;
import com.vaibhav.notes.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/collection")
public class CollectionApiController {

    CollectionService collectionService;

    @Autowired
    public CollectionApiController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("{userId}")
    List<Collection> getAllCollections(@RequestHeader(name = "api_key") String apiKey, @PathVariable("userId") Long userId) {
        try {
            if (!apiKey.equals(Constants.api_key))
                throw Constants.authenticationException;
            return collectionService.getCollections(userId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }

    @PostMapping
    Collection addCollection(@RequestHeader(name = "api_key") String apiKey, @RequestBody Collection collection) {
        try {
            if (!apiKey.equals(Constants.api_key))
                throw Constants.authenticationException;
            return collectionService.addCollection(collection);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }

    @DeleteMapping
    Collection deleteCollection(@RequestHeader(name = "api_key") String apiKey, @RequestParam("collectionId") Long id) {
        try {
            if (!apiKey.equals(Constants.api_key))
                throw Constants.authenticationException;
            return collectionService.deleteCollection(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }
}
