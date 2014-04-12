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
import org.tim.service.ReadDataService;
import org.tim.service.WriteDataService;
import org.tim.service.tag.TagManager;

/**
 * @author tim
 */
@Controller
public class ValetController {
    private final static Splitter TAG_SPLITTER = Splitter.on(",").trimResults().omitEmptyStrings();
    @Autowired
    private WriteDataService writeDataService;
    @Autowired
    private ReadDataService readDataService;
    @Autowired
    private TagManager tagManager;

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/notes";
    }
    /*
     * Notes view
     */

    @RequestMapping(value = "/notes")
    public String notes(Model model) {
        model.addAttribute("notes", readDataService.getAllNotes());
        model.addAttribute("tags", readDataService.getAllTags());
        return "notes";
    }

    @RequestMapping(value = "/notes/tag")
    public String noteByTag(@RequestParam long id, Model model) {
        model.addAttribute("notes", readDataService.getNotesByTag(id));
        model.addAttribute("tags", readDataService.getAllTags());
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
        writeDataService.addNote(note);
        tagManager.processNote(note);

        return "redirect:/notes/";
    }

    @RequestMapping(value = "/notes/delete", method = RequestMethod.GET)
    public String deleteNote(@RequestParam(value = "id") long id) {
        writeDataService.removeNote(id);
        return "redirect:/notes/";
    }

    /*
     * Accounts view
     */
    @RequestMapping(value = "/accounts")
    public String accounts(Model model) {
        model.addAttribute("accounts", readDataService.getAllAccounts());
        return "accounts";
    }

    /*
     * Events view
     */

    @RequestMapping(value = "/events")
    public String contacts(Model model) {
        model.addAttribute("events", readDataService.getAllEvents());
        return "events";
    }

    /*
     * Expenses view
     */

    @RequestMapping(value = "/expenses")
    public String lists(Model model) {
        model.addAttribute("expenses", readDataService.getAllExpenses());
        return "expenses";
    }

    /*
     * Tasks view
     */

    @RequestMapping(value = "/tasks")
    public String tasks(Model model) {
        model.addAttribute("tasks", readDataService.getAllTasks());
        return "tasks";
    }
}
