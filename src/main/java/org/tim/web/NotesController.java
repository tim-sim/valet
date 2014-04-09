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
import org.tim.service.CommonService;
import org.tim.service.NotesService;

/**
 * @author tim
 */
@Controller
public class NotesController {
    private final static Splitter TAG_SPLITTER = Splitter.on(",").trimResults().omitEmptyStrings();
    @Autowired
    private NotesService notesService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/notes";
    }
    /*
     * Notes view
     */

    @RequestMapping(value = "/notes")
    public String notes(Model model) {
        model.addAttribute("notes", notesService.getAllNotes());
        model.addAttribute("tags", notesService.getAllTags());
        return "notes";
    }

    @RequestMapping(value = "/notes/tag")
    public String noteByTag(@RequestParam long id, Model model) {
        model.addAttribute("notes", notesService.getNotesByTag(id));
        model.addAttribute("tags", notesService.getAllTags());
        return "notes";
    }

    @RequestMapping(value = "/notes/add", method = RequestMethod.POST)
    public String addNote(@RequestParam(value = "noteTags") String noteTags, @RequestParam(value = "noteContent") String noteContent) {
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

    @RequestMapping(value = "/notes/delete", method = RequestMethod.GET)
    public String deleteNote(@RequestParam(value = "id") long id) {
        Note note = notesService.getById(id);
        if (note != null) {
            notesService.removeNote(note);
        }
        return "redirect:/notes/";
    }

    /*
     * Accounts view
     */
    @RequestMapping(value = "/accounts")
    public String accounts(Model model) {
        model.addAttribute("accounts", commonService.getAllAccounts());
        return "accounts";
    }

    /*
     * Contacts view
     */

    @RequestMapping(value = "/contacts")
    public String contacts(Model model) {
        return "contacts";
    }

    /*
     * Lists view
     */

    @RequestMapping(value = "/lists")
    public String lists(Model model) {
        return "lists";
    }

    /*
     * Tasks view
     */

    @RequestMapping(value = "/tasks")
    public String tasks(Model model) {
        model.addAttribute("tasks", commonService.getAllTasks());
        return "tasks";
    }
}
