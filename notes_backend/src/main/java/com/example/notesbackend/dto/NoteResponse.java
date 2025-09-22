package com.example.notesbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

/**
 * Response payload representing a note returned by the API.
 */
public class NoteResponse {

    @Schema(description = "Unique identifier of the note", example = "1")
    private Long id;

    @Schema(description = "Note title", example = "Meeting Notes")
    private String title;

    @Schema(description = "Note content", example = "Discuss project milestones and timelines.")
    private String content;

    @Schema(description = "Creation timestamp in ISO-8601", example = "2025-01-01T12:00:00Z")
    private Instant createdAt;

    @Schema(description = "Last update timestamp in ISO-8601", example = "2025-01-01T12:30:00Z")
    private Instant updatedAt;

    public Long getId() {
        return id;
    }

    public NoteResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NoteResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NoteResponse setContent(String content) {
        this.content = content;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public NoteResponse setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public NoteResponse setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
