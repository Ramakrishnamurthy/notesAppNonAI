package com.java.noteApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.java.noteApp.model.Note;
import com.java.noteApp.repository.NoteRepository;
import com.java.noteApp.service.NoteServiceImpl;

/*
 * This is NoteServiceTest represents;
 * 
 * @author Shilpi
 * @since 2025-02-18
 */

@SpringBootTest
public class NoteServiceTest {

	@Mock
	 private NoteRepository noteRepository;
	
	@InjectMocks
	private NoteServiceImpl NoteServiceImpl;
	
	@Test
	public void addNoteTest() {
		Note note=new Note();
		note.setSubject("Metting Notes");
		note.setDescription("Discussed project milestones");
		note.setTimestampCreated(Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());
		
		when(noteRepository.save(note)).thenReturn(note);
		
		Note addedNote=NoteServiceImpl.addNote(note);
		
		assertEquals(note.getSubject(), addedNote.getSubject());
		assertEquals(note.getDescription(), addedNote.getDescription());
		verify(noteRepository,times(1)).save(note);
		
	}
	
	
}
