package org.tim.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tim.service.NotesService;

/**
 * Created by tim on 3/7/14.
 */
@Controller
public class NotesController {
    @Autowired
    private NotesService notesService;

    @RequestMapping("/notes")
    public String notes(Model model) {
        model.addAttribute("notesList", notesService.getAllNotes());
        return "notes";
    }
}
