package org.tim.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tim.domain.Note;
import org.tim.service.NotesService;

/**
 * @author tim
 */
@Controller
@RequestMapping("/notes")
public class NotesController {
    @Autowired
    private NotesService notesService;

    @RequestMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("notesList", notesService.getAllNotes());
        return "notes";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        notesService.addNote(note);
        return "redirect:/notes/list";
    }
}
