package com.java.noteApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class NoteController {

	@Autowired
	private NoteService noteService;

	/* Handler method for adding notes*/	
	@PostMapping("/notes")
	public ResponseEntity<Note> addNotes( @RequestBody Note note) {
		Note savedNote=noteService.addNote(note);
		return new ResponseEntity<Note>(savedNote, HttpStatus.CREATED);
	}
	
	/* Handler method for fetching notes by Id */
	@GetMapping("/notes/{id}")
	public ResponseEntity<Note> getNotesById(@PathVariable Long id){
		Note note=noteService.getNoteById(id);
		 return new ResponseEntity<Note>(note,HttpStatus.OK);
		}
	
	/* Handler method for modifying notes by Id */
	@PutMapping("/notes/{id}")
	public ResponseEntity<Note> modifyNotes(@PathVariable Long id,@RequestBody Note note){
		Note updatedNote=noteService.modifyNote(id,note);
		return new ResponseEntity<Note>(updatedNote,HttpStatus.OK);
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
	
}
