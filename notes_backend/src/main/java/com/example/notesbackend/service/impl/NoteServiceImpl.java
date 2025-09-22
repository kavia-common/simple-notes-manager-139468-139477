package com.example.notesbackend.service.impl;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.exception.NotFoundException;
import com.example.notesbackend.mapper.NoteMapper;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.repository.NoteRepository;
import com.example.notesbackend.service.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of NoteService using Spring Data JPA repository.
 */
@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;

    public NoteServiceImpl(NoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Note create(NoteRequest request) {
        Note entity = NoteMapper.toEntity(request);
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Note> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Note update(Long id, NoteRequest request) {
        Note existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Note with id " + id + " not found"));
        NoteMapper.updateEntity(request, existing);
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Note with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}
