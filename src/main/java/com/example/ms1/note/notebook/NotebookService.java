package com.example.ms1.note.notebook;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotebookService {
    private final NotebookRepository notebookRepository;
    public List<Notebook> getNotebookList(){
        List<Notebook> notebookList = notebookRepository.findAll();
        return notebookList;
    }

    public Notebook getNotebook(long id){
        Notebook notebook = notebookRepository.findById(id).get();
        return notebook;
    }

    public Notebook save(Notebook notebook) {
        return notebookRepository.save(notebook);
    }
}
