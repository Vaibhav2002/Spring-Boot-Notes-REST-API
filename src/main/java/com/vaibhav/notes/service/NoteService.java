package com.vaibhav.notes.service;

import com.vaibhav.notes.exceptions.NoteException;
import com.vaibhav.notes.models.Note;
import com.vaibhav.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    NoteRepository noteRepository;

    @Autowired
    public NoteService(@Qualifier("note") NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note insertNote(Note note) {
        return noteRepository.save(note);
    }

    public Note updateNote(Long id, String text, boolean isImportant) throws NoteException {
        if (!noteRepository.existsById(id))
            throw new NoteException("Note does not exits");
        noteRepository.findById(id).map(note1 -> {
            note1.setText(text);
            note1.setImportant(isImportant);
            return true;
        });
        return noteRepository.findById(id).get();
    }

    public Note deleteNote(Long id) throws NoteException {
        if (!noteRepository.existsById(id))
            throw new NoteException("Note does not exist");
        Optional<Note> note = noteRepository.findById(id);
        noteRepository.deleteById(id);
        return note.get();
    }

    public List<Note> getAllNotes(Long collectionId) {
        return noteRepository.getNotesByCollectionId(collectionId);
    }


}
