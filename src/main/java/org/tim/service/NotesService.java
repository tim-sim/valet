package org.tim.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.tim.domain.Note;

import java.util.List;

/**
 * Created by tim on 3/7/14.
 */
@Service()
public class NotesService {
    public List<Note> getAllNotes() {
        return ImmutableList.of(testNote(), testNote(), testNote());
    }

    private Note testNote() {
        Note note = new Note();
        note.setTitle(RandomStringUtils.randomAlphabetic(5));
        note.setContent(RandomStringUtils.randomAlphabetic(15));
        return note;
    }
}
