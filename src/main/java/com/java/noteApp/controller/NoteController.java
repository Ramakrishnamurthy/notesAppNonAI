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

@RestController
public class NoteController {

	@Autowired
	private NoteService noteService;

	@PostMapping("/notes")
	public ResponseEntity<Note> addNotes( @RequestBody Note note) {
		Note savedNote=noteService.addNote(note);
		return new ResponseEntity<Note>(savedNote, HttpStatus.CREATED);
	}
	@GetMapping("/notes/{id}")
	public ResponseEntity<Note> getNotesById(@PathVariable Long id){
		Note note=noteService.getNoteById(id);
		 return new ResponseEntity<Note>(note,HttpStatus.OK);
		}
	
	@PutMapping("/notes/{id}")
	public ResponseEntity<Note> modifyNotes(@PathVariable Long id,@RequestBody Note note){
		Note updatedNote=noteService.modifyNote(id,note);
		return new ResponseEntity<Note>(updatedNote,HttpStatus.OK);
	}
}
