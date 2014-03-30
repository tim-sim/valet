package org.tim.web;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tim.domain.Note;
import org.tim.domain.Tag;
import org.tim.service.NotesService;

/**
 * @author tim
 */
@Controller
@RequestMapping("/notes")
public class NotesController {
    private final static Splitter TAG_SPLITTER = Splitter.on(",").trimResults().omitEmptyStrings();
    @Autowired
    private NotesService notesService;

    @RequestMapping(value = "/")
    public String list(Model model) {
        model.addAttribute("notes", notesService.getAllNotes());
        model.addAttribute("tags", notesService.getAllTags());
        return "notes";
    }

    @RequestMapping(value = "/tag")
    public String getByTag(@RequestParam long id, Model model) {
        model.addAttribute("notes", notesService.getNotesByTag(id));
        model.addAttribute("tags", notesService.getAllTags());
        return "notes";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestParam(value = "noteTags") String noteTags, @RequestParam(value = "noteContent") String noteContent) {
        Note note = new Note();
        note.setContent(noteContent);
        note.setTags(Lists.transform(TAG_SPLITTER.splitToList(noteTags), new Function<String, Tag>() {
            public Tag apply(String tagName) {
                return new Tag(tagName);
            }
        }));
        notesService.addNote(note);
        return "redirect:/notes/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") long id) {
        Note note = notesService.getById(id);
        if (note != null) {
            notesService.removeNote(note);
        }
        return "redirect:/notes/";
    }
}
