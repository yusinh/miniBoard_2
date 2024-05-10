package com.example.ms1.note.note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public Note saveDefaultNote() {
        Note note = new Note();
        note.setTitle("new title..");
        note.setContent("");
        note.setCreateDate(LocalDateTime.now());

        return noteRepository.save(note);
    }

    public Note getNote(long id){
        Note note = noteRepository.findById(id).get();
        return note;
    }

    public void save(Note note) {
        noteRepository.save(note);
    }

    public void delete(Long id) {
        noteRepository.deleteById(id);
    }
}