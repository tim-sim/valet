package org.tim.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tim.domain.Note;
import org.tim.service.NotesService;

/**
 * Created by tim on 3/7/14.
 */
@Controller
@RequestMapping("/notes")
public class NotesController {
    @Autowired
    private NotesService notesService;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("notesList", notesService.getAllNotes());
        return "notes";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        Note note = new Note();
        note.setTitle(String.valueOf(model.asMap().get("title")));
        note.setContent(String.valueOf(model.asMap().get("content")));
        notesService.addNote(note);
        return list(model);
    }
}
