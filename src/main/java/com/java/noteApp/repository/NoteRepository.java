package com.java.noteApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.noteApp.model.Note;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

	List<Note> findBySubject(String subject);
}
