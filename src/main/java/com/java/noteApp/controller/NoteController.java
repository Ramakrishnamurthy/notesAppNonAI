package com.java.noteApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.noteApp.model.Note;
import com.java.noteApp.repository.NoteRepository;
import com.java.noteApp.service.NoteService;

/*
 * This is NoteController  class which contains 
 * all the handler methods for adding , modification , retrieval, words count and average length of notes
 * 
 * @author Shilpi
 * @since 2025-02-18
 */

@RestController
public class NoteController {

	@Autowired
	private NoteService noteService;

	/* Handler method for adding notes */
	@PostMapping("/notes")
	public ResponseEntity<Note> addNotes(@RequestBody Note note) {
		Note savedNote = noteService.addNote(note);
		return new ResponseEntity<Note>(savedNote, HttpStatus.CREATED);
	}

	/* Handler method for fetching notes by Id */
	@GetMapping("/notes/{id}")
	public ResponseEntity<Note> getNotesById(@PathVariable Long id) {
		Note note = noteService.getNoteById(id);
		return new ResponseEntity<Note>(note, HttpStatus.OK);
	}

	/* Handler method for fetching notes by Id */
	@GetMapping("/notes/count")
	public Long getCountOfTotalNotes() {
		return noteService.countTotalNotes();

	}

	/* Handler method for modifying notes by Id */
	@PutMapping("/notes/{id}")
	public ResponseEntity<Note> modifyNotes(@PathVariable Long id, @RequestBody Note note) {
		Note updatedNote = noteService.modifyNote(id, note);
		return new ResponseEntity<Note>(updatedNote, HttpStatus.OK);
	}

	/* Handler method for deleting notes by Id */
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<String> deleteNote(@PathVariable Long id) {
		noteService.deleteNote(id);
		return ResponseEntity.ok("Delete the with given id " + id);
	}

	@GetMapping("/notes/search")
	public List<Note> getNoteBySubject(@RequestParam String subject) {
		return noteService.findBySubject(subject);
	}

	/* Handler method for fetching notes by Id */
	@GetMapping("/api/notes/word-count/{id}")
	public Long getTotalWordCount(@PathVariable Long id) {
		return noteService.totalWordCountOfSpecificNote(id);
	}

	/* Handler method for fetching notes by Id */
	@GetMapping("/api/notes/average-length")
	public double getAverageNoteLength() {

		return noteService.averageLengthofAllNote();
	}

	/* Handler method for get liked notes by Id */
	@PostMapping("/api/notes/{id}/like")
	public ResponseEntity<Note> getLikedNoteById(@PathVariable Long id){
		Note likedNote=noteService.likeNoteById(id);
		return new ResponseEntity<Note>(likedNote, HttpStatus.OK);
	}
	
	/* Handler method for get unlike notes by Id */
	@DeleteMapping("/api/notes/{id}/unlike")
	public ResponseEntity<Note> unlikeNoteById(@PathVariable Long id){
		Note unlikedNote=noteService.unlikeNoteById(id);
		return new ResponseEntity<Note>(unlikedNote, HttpStatus.OK);
	}
	
	/* Handler method for get list of liked notes */
	@GetMapping("/api/notes/like")
	public List<Note> getListOfLikedNotes() {
		return noteService.getAllLikedNotes();
	}
}
