package com.java.noteApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.noteApp.model.Note;
import com.java.noteApp.repository.NoteRepository;


public interface NoteService {

	Note addNote(Note note);
	Note getNoteById(Long id);
	Note modifyNote(Long id,Note note);
}
