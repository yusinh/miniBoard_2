package com.example.ms1.note.note;

import com.example.ms1.note.notebook.Notebook;
import com.example.ms1.note.notebook.NotebookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books/{notebookId}/notes")
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/write")
    public String write(@PathVariable("notebookId") Long notebookId) {
        Notebook notebook = noteService.getNotebook(notebookId);
        noteService.saveDefault(notebook);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable("notebookId") Long notebookId, @PathVariable("id") Long id) {
        Note note = noteService.getNote(id);

        List<Notebook> notebookList = noteService.getNotebookList();
        Notebook targetNotebook = noteService.getNotebook(notebookId);
        List<Note> noteList = noteService.getNoteListByNotebook(targetNotebook);

        model.addAttribute("notebookList", notebookList);
        model.addAttribute("targetNotebook", targetNotebook);
        model.addAttribute("targetNote", note);
        model.addAttribute("noteList", noteList);

        return "main";
    }
    @PostMapping("/{id}/update")
    public String update(@PathVariable("notebookId") Long notebookId, @PathVariable("id") Long id, String title, String content) {
        Note note = noteService.getNote(id);

        if(title.trim().length() == 0) {
            title = "제목 없음";
        }

        note.setTitle(title);
        note.setContent(content);

        noteService.save(note);
        return "redirect:/books/%d/notes/%d".formatted(notebookId, id);
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("notebookId") Long notebookId, @PathVariable("id") Long id) {

        noteService.delete(id);
        return "redirect:/";
    }


}
