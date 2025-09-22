package com.example.notesbackend.service;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.model.Note;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing notes.
 */
public interface NoteService {

    // PUBLIC_INTERFACE
    Note create(NoteRequest request);
    /** Creates a new note from the provided request and persists it. */

    // PUBLIC_INTERFACE
    List<Note> findAll();
    /** Returns all notes. */

    // PUBLIC_INTERFACE
    Optional<Note> findById(Long id);
    /** Returns a note by its id, if present. */

    // PUBLIC_INTERFACE
    Note update(Long id, NoteRequest request);
    /** Updates an existing note by id with the provided request. */

    // PUBLIC_INTERFACE
    void delete(Long id);
    /** Deletes the note with the given id. */
}
