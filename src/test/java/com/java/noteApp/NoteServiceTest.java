package com.java.noteApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.java.noteApp.model.Note;
import com.java.noteApp.repository.NoteRepository;
import com.java.noteApp.service.NoteServiceImpl;

/*
 * This is NoteServiceTest contain test cases;
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

	/* Handles test case for addNote */
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

	/* Handles test case for modify a Note */
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

	/* Handles test case for fetching a note by id */
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

	/* Handles test case for deleting a note by id */
	@Test
	public void deleteNoteTest() {
		Long id = 1L;
		Note note = new Note();
		note.setNoteId(id);
		when(noteRepository.findById(id)).thenReturn(Optional.of(note));
		noteServiceImpl.deleteNote(id);
		verify(noteRepository, times(1)).deleteById(id);
	}

	/* Handles test case for counting total notes */
	@Test
	public void countTotalNotesTest() {
		when(noteRepository.count()).thenReturn(10L);
		Long totalNotes = noteServiceImpl.countTotalNotes();
		assertEquals(10, totalNotes);
	}

	/* Handles test case for counting total word count in a note */
	@Test
	public void totalWordCountOfSpecificNoteTest() {
		Long id = 1L;
		Note note = new Note();
		note.setNoteId(id);
		note.setDescription("Test for total word count spcefic note");

		when(noteRepository.findById(id)).thenReturn(Optional.of(note));

		Long n1 = noteServiceImpl.totalWordCountOfSpecificNote(id);
		assertEquals(7, n1);
		verify(noteRepository, times(1)).findById(id);
	}

	/* Handles test case for calculating average length note */
	@Test
	public void averageLengthofAllNoteTest() {
		Long id = 1L;
		Note note1 = new Note();
		note1.setNoteId(id);
		note1.setDescription("First Note Description");

		Note note2 = new Note();
		note2.setNoteId(id);
		note2.setDescription("Second Note Description");

		when(noteRepository.findAll()).thenReturn(List.of(note1, note2));

		double avgLength = noteServiceImpl.averageLengthofAllNote();

		assertEquals(((3 + 3) / 2.0), avgLength);
		verify(noteRepository, times(1)).findAll();
	}

	/* Handles test case for fetching note by subject */
	@Test
	public void findBySubjectTest() {
		Long id = 1L;
		Note note = new Note();
		note.setSubject("Note Subject");
		when(noteRepository.findBySubject("Note Subject")).thenReturn(List.of(note));

		List<Note> notes = noteServiceImpl.findBySubject("Note Subject");

		// Then
		assertNotNull(notes);
		assertEquals(1, notes.size());
		verify(noteRepository, times(1)).findBySubject("Note Subject");
	}

	/* Handles test case for liking note by id */
	@Test
	public void likeNoteByIdTest() {
		Long id = 1L;
		Note note = new Note();
		note.setNoteId(id);
		note.setLikes(0);

		when(noteRepository.findById(id)).thenReturn(Optional.of(note));
		when(noteRepository.save(note)).thenReturn(note);

		Note likedNote = noteServiceImpl.likeNoteById(id);

		assertEquals(1, likedNote.getLikes());
		verify(noteRepository, times(1)).findById(id);
		verify(noteRepository, times(1)).save(note);
	}

	/* Handles test case for un-liking note by id */
	@Test
	public void unlikeNoteByIdTest() {
		Long id = 1L;
		Note note = new Note();
		note.setNoteId(id);
		note.setLikes(1);

		when(noteRepository.findById(id)).thenReturn(Optional.of(note));
		when(noteRepository.save(note)).thenReturn(note);

		Note unlikedNote = noteServiceImpl.unlikeNoteById(id);

		assertEquals(0, unlikedNote.getLikes());
		verify(noteRepository, times(1)).findById(id);
		verify(noteRepository, times(1)).save(note);
	}

	/* Handles test case for fetching all liked notes */
	@Test
	public void getAllLikedNotes() {
		Long id = 1L;
		Note note1 = new Note();
		note1.setNoteId(id);
		note1.setLikes(1);

		Note note2 = new Note();
		note2.setNoteId(id);
		note2.setLikes(0);

		when(noteRepository.findAll()).thenReturn(List.of(note1, note2));

		List<Note> likedNotes = noteServiceImpl.getAllLikedNotes();

		assertEquals(1, likedNotes.size());
		verify(noteRepository, times(1)).findAll();

	}

	/* Handles test case for reseting likes on note */
	@Test
	public void resetLikesTest() {
		Long id = 1L;
		Note note = new Note();
		note.setNoteId(id);
		note.setLikes(2);

		when(noteRepository.findById(id)).thenReturn(Optional.of(note));

		Note reset = noteServiceImpl.resetLikes(id);

		assertEquals(0, reset.getLikes());
		verify(noteRepository, times(1)).save(note);

	}

	/* Handles test case for boosting likes on note */
	@Test
	public void likeBoostTest() {
		Long id = 1L;
		Note note = new Note();
		note.setNoteId(id);
		note.setSubject("Note Subject");
		note.setDescription("Note Subject Description");
		note.setLikes(0);

		when(noteRepository.findById(id)).thenReturn(Optional.of(note));
		when(noteRepository.save(note)).thenReturn(note);

		Note boostedNote = noteServiceImpl.likeBoost(id, 5);

		// Then
		assertEquals(5, boostedNote.getLikes());
		verify(noteRepository, times(1)).save(note);

	}

	/* Handles test case for fetching top liked notes */
	@Test
	public void getTopLikedNotes() {
		Long id = 1L;
		List<Note> notes = new ArrayList();
		notes.add(new Note(1L, "Note 1", "Desc1", null, null, 5));
		notes.add(new Note(2L, "Note 2", "Desc2", null, null, 8));
		notes.add(new Note(3L, "Note 3", "Desc3", null, null, 10));
		notes.add(new Note(4L, "Note 4", "Desc3", null, null, 15));
		notes.add(new Note(5L, "Note 5", "Desc4", null, null, 12));
		notes.add(new Note(6L, "Note 6", "Desc5", null, null, 3));

		when(noteRepository.findAll()).thenReturn(notes);
		List<Note> result = noteServiceImpl.getTopLikedNotes();

		assertNotNull(result);
		assertEquals(5, result.size());
		assertEquals(15, result.get(0).getLikes());
		assertEquals(12, result.get(1).getLikes());
		assertEquals(10, result.get(2).getLikes());
		assertEquals(8, result.get(3).getLikes());
		assertEquals(5, result.get(4).getLikes());
		verify(noteRepository, times(1)).findAll();
	}
}
