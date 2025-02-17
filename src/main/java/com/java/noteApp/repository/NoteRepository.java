package com.java.noteApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.noteApp.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long>{

}
