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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.noteApp.model.Note;
import com.java.noteApp.service.NoteService;

/*
 * This is NoteController  class which contains 
 * all the handler methods for adding , modification , retrieval, words count and average length of notes
 * 
 * @author Shilpi
 * @since 2025-02-18
 */

@RestController
@RequestMapping("/api/notes")
public class NoteController {

	@Autowired
	private NoteService noteService;

	/* Handler method for adding notes */
	@PostMapping("")
	public ResponseEntity<Note> addNotes(@RequestBody Note note) {
		Note savedNote = noteService.addNote(note);
		return new ResponseEntity<Note>(savedNote, HttpStatus.CREATED);
	}

	/* Handler method for fetching notes by Id */
	@GetMapping("/{id}")
	public ResponseEntity<Note> getNotesById(@PathVariable Long id) {
		Note note = noteService.getNoteById(id);
		return new ResponseEntity<Note>(note, HttpStatus.OK);
	}

	/* Handler method for fetching notes by Id */
	@GetMapping("/count")
	public Long getCountOfTotalNotes() {
		return noteService.countTotalNotes();

	}

	/* Handler method for modifying notes by Id */
	@PutMapping("/{id}")
	public ResponseEntity<Note> modifyNotes(@PathVariable Long id, @RequestBody Note note) {
		Note updatedNote = noteService.modifyNote(id, note);
		return new ResponseEntity<Note>(updatedNote, HttpStatus.OK);
	}

	/* Handler method for deleting notes by Id */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteNote(@PathVariable Long id) {
		noteService.deleteNote(id);
		return ResponseEntity.ok("Delete the with given id " + id);
	}

	@GetMapping("/search")
	public List<Note> getNoteBySubject(@RequestParam String subject) {
		return noteService.findBySubject(subject);
	}

	/* Handler method for fetching notes by Id */
	@GetMapping("/word-count/{id}")
	public Long getTotalWordCount(@PathVariable Long id) {
		return noteService.totalWordCountOfSpecificNote(id);
	}

	/* Handler method for fetching notes by Id */
	@GetMapping("/average-length")
	public double getAverageNoteLength() {
		return noteService.averageLengthofAllNote();
	}

	/* Handler method for get liked notes by Id */
	@PostMapping("/{id}/like")
	public ResponseEntity<Note> getLikedNoteById(@PathVariable Long id) {
		Note likedNote = noteService.likeNoteById(id);
		return new ResponseEntity<Note>(likedNote, HttpStatus.OK);
	}

	/* Handler method for get unlike notes by Id */
	@DeleteMapping("/{id}/unlike")
	public ResponseEntity<Note> unlikeNoteById(@PathVariable Long id) {
		Note unlikedNote = noteService.unlikeNoteById(id);
		return new ResponseEntity<Note>(unlikedNote, HttpStatus.OK);
	}

	/* Handler method for get list of liked notes */
	@GetMapping("/like")
	public List<Note> getListOfLikedNotes() {
		return noteService.getAllLikedNotes();
	}

	/* Handler method for get list of top liked notes */
	@GetMapping("/top-liked")
	public List<Note> getTopLikedNotes() {
		return noteService.getTopLikedNotes();
	}

	/* Handler method for reset likes for notes */
	@DeleteMapping("/{id}/like-reset")
	public ResponseEntity<Note> resetLikes(@PathVariable Long id) {
		Note note = noteService.resetLikes(id);
		return new ResponseEntity<Note>(note, HttpStatus.OK);
	}

	/* Handler method for boost likes for notes */
	@PostMapping("/{id}/like-boost")
	public ResponseEntity<Note> likeBoost(@PathVariable Long id, @RequestParam int boost) {
		return new ResponseEntity<>(noteService.likeBoost(id, boost), HttpStatus.OK);
	}
}
