package com.java.noteApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.java.noteApp.model.Note;
import com.java.noteApp.repository.NoteRepository;
import com.java.noteApp.service.NoteService;
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
	private NoteServiceImpl noteServiceImpl;

	@Test
	public void addNoteTest() {
		Note note = new Note();
		note.setSubject("Metting Notes");
		note.setDescription("Discussed project milestones");
		note.setTimestampCreated(Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());

		when(noteRepository.save(note)).thenReturn(note);

		Note addedNote = noteServiceImpl.addNote(note);

		assertEquals(note.getSubject(), addedNote.getSubject());
		assertEquals(note.getDescription(), addedNote.getDescription());
		verify(noteRepository, times(1)).save(note);

	}

	@Test
	public void modifyNoteTest() {
		Long id = 1L;
		Note note = new Note();
		note.setNoteId(id);
		note.setSubject("Metting Notes");
		note.setDescription("Discussed project milestones");
		note.setTimestampCreated(Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());

		Note updatedNoteDetails = new Note();
		updatedNoteDetails.setSubject("new Subject");
		updatedNoteDetails.setDescription("new Description");

		when(noteRepository.findById(id)).thenReturn(Optional.of(note));
		when(noteRepository.save(note)).thenReturn(note);

		Note updatedNotes = noteServiceImpl.modifyNote(id, updatedNoteDetails);

		assertEquals(updatedNoteDetails.getSubject(), updatedNotes.getSubject());
		assertEquals(updatedNoteDetails.getDescription(), updatedNotes.getDescription());
		verify(noteRepository, times(1)).findById(id);
		verify(noteRepository, times(1)).save(note);
	}

	@Test
	public void getNoteByIdTest() {
		Long noteId = 1L;
		Note note = new Note();
		note.setNoteId(noteId);
		note.setSubject("Test Subject");

		when(noteRepository.findById(noteId)).thenReturn(Optional.of(note));

		Note fetchedNote = noteServiceImpl.getNoteById(noteId);

		assertNotNull(fetchedNote);
		assertEquals(noteId, fetchedNote.getNoteId());
		verify(noteRepository, times(1)).findById(noteId);
	}

	@Test
	public void deleteNoteTest() {
		Long id = 1L;
		Note note = new Note();
		note.setNoteId(id);
		when(noteRepository.findById(id)).thenReturn(Optional.of(note));
		noteServiceImpl.deleteNote(id);
		verify(noteRepository, times(1)).deleteById(id);
	}

	@Test
	public void countTotalNotesTest() {
		when(noteRepository.count()).thenReturn(10L);
		Long totalNotes = noteServiceImpl.countTotalNotes();
		assertEquals(10, totalNotes);
	}

}
