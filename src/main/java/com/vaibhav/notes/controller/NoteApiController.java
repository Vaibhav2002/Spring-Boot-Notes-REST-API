package com.vaibhav.notes.controller;

import com.vaibhav.notes.models.Note;
import com.vaibhav.notes.service.NoteService;
import com.vaibhav.notes.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteApiController {

    NoteService noteService;

    @Autowired
    public NoteApiController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    Note insertNote(@RequestHeader(name = "api_key") String apiKey, @RequestBody Note note) {
        try {
            if (!apiKey.equals(Constants.api_key))
                throw Constants.authenticationException;
            return noteService.insertNote(note);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), exception);
        }

    }

    @PutMapping("{id}")
    Note updateNote(@RequestHeader(name = "api_key") String apiKey, @PathVariable("id") Long id, @RequestParam("text") String text, @RequestParam("isImportant") boolean isImportant) {
        try {
            if (!apiKey.equals(Constants.api_key))
                throw Constants.authenticationException;
            return noteService.updateNote(id, text, isImportant);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }

    @DeleteMapping
    Note deleteNote(@RequestHeader(name = "api_key") String apiKey, @RequestParam("noteId") Long id) {
        try {
            if (!apiKey.equals(Constants.api_key))
                throw Constants.authenticationException;
            return noteService.deleteNote(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }

    @GetMapping("{collectionId}")
    List<Note> getAllNotes(@RequestHeader(name = "api_key") String apiKey, @PathVariable Long collectionId) {
        try {
            if (!apiKey.equals(Constants.api_key))
                throw Constants.authenticationException;
            return noteService.getAllNotes(collectionId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }

    }

}
