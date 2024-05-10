package com.example.ms1.note.notebook;

import com.example.ms1.note.MainService;
import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class NotebookController {

    private final NotebookService notebookService;
    private final MainService mainService;

    @PostMapping("/books/write")
    public String write() {
        mainService.saveDefaultNotebook();
        return "redirect:/";

    }

    @PostMapping("/groups/{notebookId}/books/write")
    public String groupWrite(@PathVariable("notebookId") Long notebookId) {

        mainService.saveGroupNotebook(notebookId);
        return "redirect:/";
    }

    @GetMapping("/books/{id}")
    public String detail(@PathVariable("id") Long id) {
        Notebook notebook = notebookService.getNotebook(id);
        Note note = notebook.getNoteList().get(0);

        return "redirect:/books/%d/notes/%d".formatted(id, note.getId());
    }

    @PostMapping("/books/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
//        mainService.delete(id);
        notebookService.delete(id);
        return "redirect:/";
    }

}