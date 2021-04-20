package com.vaibhav.notes.repository;

import com.vaibhav.notes.models.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("collectionRepo")
public interface CollectionRepository extends JpaRepository<Collection, Long> {

    @Query("SELECT c FROM collection_table c WHERE c.userId = ?1")
    List<Collection> getAllCollection(Long userId);


}
