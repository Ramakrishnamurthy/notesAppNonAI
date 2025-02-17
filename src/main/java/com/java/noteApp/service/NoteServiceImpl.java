package com.java.noteApp.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.noteApp.exception.ResourceNotFoundException;
import com.java.noteApp.model.Note;
import com.java.noteApp.repository.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService{

	private static final Logger logger=LoggerFactory.getLogger(NoteServiceImpl.class);

	
	@Autowired
	private NoteRepository noteRepository;
	
	@Override
	public Note addNote(Note note) {
		note.setTimestampCreated(Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());
		note.setTimestampUpdated(Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());
		Note savedNote=noteRepository.save(note);
		logger.debug("The note has been saved : "+savedNote);
		return savedNote;
	}

	public Note getNoteById(Long id) {
		Note note=noteRepository.findById(id)
	    		.orElseThrow(()->new ResourceNotFoundException("No note exist with the given id :"+id));
		return note;
	}


	@Override
	public Note modifyNote(Long id,Note note) {
		Note fetchedNote=noteRepository.findById(id)
	    		.orElseThrow(()->new ResourceNotFoundException("No note exist with the given id :"+id));
		fetchedNote.setSubject(note.getSubject());
		fetchedNote.setDescription(note.getDescription());
		fetchedNote.setTimestampUpdated(Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());
		return noteRepository.save(fetchedNote);
	}


}
