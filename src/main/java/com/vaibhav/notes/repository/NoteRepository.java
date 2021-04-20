package com.vaibhav.notes.repository;

import com.vaibhav.notes.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.print.DocFlavor;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository("note")
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n FROM note n WHERE n.collectionId=?1")
    public List<Note> getNotesByCollectionId(Long collectionId);

}
