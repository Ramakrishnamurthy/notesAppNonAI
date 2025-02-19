package com.java.noteApp.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.java.noteApp.exception.ResourceNotFoundException;
import com.java.noteApp.model.Note;
import com.java.noteApp.repository.NoteRepository;

/*
 * This is NoteService implementation class which contains 
 * all the business logic regarding retrieval,  addition , modification;
 * 
 * @author Shilpi
 * @since 2025-02-18
 */

@Service
public class NoteServiceImpl implements NoteService {

	private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

	@Autowired
	private NoteRepository noteRepository;

	/*
	 * This function is for adding notes
	 * 
	 * @param Notes class object
	 */
	@Override
	public Note addNote(Note note) {
		note.setTimestampCreated(Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());
		note.setTimestampUpdated(Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());
		Note savedNote = noteRepository.save(note);
		logger.debug("The note has been saved : " + savedNote);
		return savedNote;
	}

	/*
	 * This function is for fetching the notes by notesId
	 * 
	 * @param noteId
	 * 
	 * @return note class object
	 */
	public Note getNoteById(Long id) {
		Note note = noteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No note exist with the given id :" + id));
		return note;
	}

	/*
	 * This function is for modifying or updating the notes
	 * 
	 * @param noteId, first parameter
	 * 
	 * @param note, second parameter
	 * 
	 * @return note class object
	 */
	@Override
	public Note modifyNote(Long id, Note note) {
		Note fetchedNote = noteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No note exist with the given id :" + id));
		fetchedNote.setSubject(note.getSubject());
		fetchedNote.setDescription(note.getDescription());
		fetchedNote.setTimestampUpdated(Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());
		return noteRepository.save(fetchedNote);
	}

	/*
	 * This function return the total no of words in a specific note
	 * 
	 * @param noteId, single parameter
	 * 
	 * @return long , total no of words
	 */
	@Override
	public Long totalWordCountOfSpecificNote(Long id) {
		Note fetchedNote = noteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No note exist with the given id :" + id));

		Long noOfWords = (long) fetchedNote.getDescription().split(" ").length;
		return noOfWords;
	}

	/*
	 * This function return the average length of all the notes present
	 * 
	 * @return double , average length
	 */
	@Override
	public double averageLengthofAllNote() {
		List<Note> listOfNotes = noteRepository.findAll();
		return listOfNotes.stream().mapToInt(note -> note.getDescription().split(" ").length).average().orElse(0.0);
	}

	/*
	 * This function delete note by given id
	 * 
	 * @param id , Long type
	 * 
	 * @return void ,
	 */
	@Override
	public void deleteNote(Long id) {
		Note fetchedNote = noteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No note exist with the given id :" + id));
		noteRepository.deleteById(id);
	}

	/*
	 * This function return the all the notes present
	 * 
	 * @return Long , total no of notes
	 */
	@Override
	public Long countTotalNotes() {
		return noteRepository.count();
	}

	/*
	 * This function return the List of notes with respect to specific subject
	 * 
	 * @param String, subject
	 * 
	 * @return List , list of notes
	 */
	@Override
	public List<Note> findBySubject(String subject) {
		return noteRepository.findBySubject(subject);
	}

	/*
	 * This function return will like a note with respect to given id
	 * 
	 * @param Long, notesId
	 * 
	 * @return note, note with incrementing likes
	 */
	@Override
	public Note likeNoteById(Long id) {
		Note note = getNoteById(id);
		note.setLikes(note.getLikes() + 1);
		return noteRepository.save(note);
	}

	/*
	 * This function return will unlike a note with respect to given id
	 * 
	 * @param Long, notesId
	 * 
	 * @return note, note with decremented likes
	 */
	@Override
	public Note unlikeNoteById(Long id) {
		Note note = getNoteById(id);
		note.setLikes(note.getLikes() - 1);
		return noteRepository.save(note);

	}

	/*
	 * This function return will list of liked note
	 *
	 * @return List, List of liked notes
	 */
	public List<Note> getAllLikedNotes() {
		List<Note> likedNotes = noteRepository.findAll();
		return likedNotes.stream().filter(note -> note.getLikes() > 0).toList();
	}

}
