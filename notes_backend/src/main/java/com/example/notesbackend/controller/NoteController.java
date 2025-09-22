package com.example.notesbackend.controller;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.dto.NoteResponse;
import com.example.notesbackend.exception.NotFoundException;
import com.example.notesbackend.mapper.NoteMapper;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * REST controller providing CRUD endpoints for notes.
 * All endpoints return clean JSON payloads and follow modern REST conventions.
 */
@RestController
@RequestMapping(value = "/notes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Notes", description = "Ocean Professional — Clean, modern CRUD endpoints for note management")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    // PUBLIC_INTERFACE
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Create a note",
            description = "Creates a new note with a title and content.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Note created",
                            content = @Content(schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content)
            }
    )
    public ResponseEntity<NoteResponse> create(
            @Valid @RequestBody NoteRequest request
    ) {
        /** Creates a new note and returns the created resource. */
        Note created = service.create(request);
        NoteResponse response = NoteMapper.toResponse(created);
        return ResponseEntity
                .created(URI.create("/notes/" + response.getId()))
                .body(response);
    }

    // PUBLIC_INTERFACE
    @GetMapping
    @Operation(
            summary = "List notes",
            description = "Returns all notes.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of notes",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = NoteResponse.class))))
            }
    )
    public List<NoteResponse> list() {
        /** Returns all notes as response DTOs. */
        return service.findAll().stream().map(NoteMapper::toResponse).toList();
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @Operation(
            summary = "Get a note",
            description = "Returns a note by id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Note found",
                            content = @Content(schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Note not found", content = @Content)
            }
    )
    public NoteResponse get(
            @Parameter(description = "ID of the note to retrieve", example = "1")
            @PathVariable Long id
    ) {
        /** Retrieves a single note by id. */
        Note note = service.findById(id).orElseThrow(() -> new NotFoundException("Note with id " + id + " not found"));
        return NoteMapper.toResponse(note);
    }

    // PUBLIC_INTERFACE
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Update a note",
            description = "Updates an existing note’s title and content.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Note updated",
                            content = @Content(schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Note not found", content = @Content)
            }
    )
    public NoteResponse update(
            @Parameter(description = "ID of the note to update", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody NoteRequest request
    ) {
        /** Updates the specified note and returns the updated resource. */
        Note updated = service.update(id, request);
        return NoteMapper.toResponse(updated);
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a note",
            description = "Deletes a note by id.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Note deleted"),
                    @ApiResponse(responseCode = "404", description = "Note not found", content = @Content)
            }
    )
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the note to delete", example = "1")
            @PathVariable Long id
    ) {
        /** Deletes the specified note. */
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
