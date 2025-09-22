package com.example.notesbackend.mapper;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.dto.NoteResponse;
import com.example.notesbackend.model.Note;

/**
 * Mapping utilities between Note entity and DTOs.
 */
public final class NoteMapper {

    private NoteMapper() {}

    // PUBLIC_INTERFACE
    public static Note toEntity(NoteRequest request) {
        /** Converts a NoteRequest to a Note entity. */
        return new Note()
                .setTitle(request.getTitle())
                .setContent(request.getContent());
    }

    // PUBLIC_INTERFACE
    public static void updateEntity(NoteRequest request, Note entity) {
        /** Applies updates from NoteRequest to an existing Note entity. */
        entity.setTitle(request.getTitle());
        entity.setContent(request.getContent());
    }

    // PUBLIC_INTERFACE
    public static NoteResponse toResponse(Note entity) {
        /** Converts a Note entity to a NoteResponse DTO. */
        return new NoteResponse()
                .setId(entity.getId())
                .setTitle(entity.getTitle())
                .setContent(entity.getContent())
                .setCreatedAt(entity.getCreatedAt())
                .setUpdatedAt(entity.getUpdatedAt());
    }
}
