package com.java.noteApp.service;

import java.util.List;

import com.java.noteApp.model.Note;

/*
 * This is NoteService  Interface which contains 
 * all the methods regarding retrieval,  addition , modification;
 * 
 * @author Shilpi
 * @since 2025-02-18
 */

public interface NoteService {

	Note addNote(Note note);

	Note getNoteById(Long id);

	Note modifyNote(Long id, Note note);

	Long totalWordCountOfSpecificNote(Long id);

	double averageLengthofAllNote();

	void deleteNote(Long id);

	Long countTotalNotes();

	List<Note> findBySubject(String subject);

	Note likeNoteById(Long Id);

	Note unlikeNoteById(Long id);

	List<Note> getAllLikedNotes();

	List<Note> getTopLikedNotes();

	Note resetLikes(Long id);

	Note likeBoost(Long id, int boost);
}
