package com.example.notesbackend.dto;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request payload for creating or updating a note.
 */
public class NoteRequest {

    @Schema(description = "Short, descriptive title of the note", example = "Meeting Notes")
    @NotBlank(message = "Title is required")
    private String title;

    @Schema(description = "Main content/body of the note", example = "Discuss project milestones and timelines.")
    @NotBlank(message = "Content is required")
    private String content;

    public String getTitle() {
        return title;
    }

    public NoteRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NoteRequest setContent(String content) {
        this.content = content;
        return this;
    }
}
