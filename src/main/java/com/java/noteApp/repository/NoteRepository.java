package com.java.noteApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.noteApp.model.Note;
import java.util.List;

/*Note Repository  extending JPA Reposiotry for 
  database calls
  
* @author Shilpi
* @since 2025-02-18
*/

public interface NoteRepository extends JpaRepository<Note, Long> {

	List<Note> findBySubject(String subject);
}
