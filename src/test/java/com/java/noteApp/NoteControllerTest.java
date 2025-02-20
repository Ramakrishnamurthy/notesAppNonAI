package com.java.noteApp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.noteApp.controller.NoteController;
import com.java.noteApp.model.Note;
import com.java.noteApp.service.NoteServiceImpl;


@WebMvcTest(NoteController.class)
public class NoteControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteServiceImpl noteServiceImpl;
    
    private ObjectMapper objectMapper = new ObjectMapper();


    private Note note;

    @BeforeEach
    public void setup() {
        note = new Note();
        note.setNoteId(1L);
        note.setSubject("Test Subject");
        note.setDescription("Test Content");
    }

    // Test case for adding a note
    @Test
    public void testAddNote() throws Exception {
        when(noteServiceImpl.addNote(any(Note.class))).thenReturn(note);
        
        mockMvc.perform(post("/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"subject\":\"Test Subject\",\"content\":\"Test Content\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.subject").value("Test Subject"))
                .andExpect(jsonPath("$.description").value("Test Content"));
    }

 // Test case for getting note by ID
    @Test
    public void testGetNoteById() throws Exception {
        when(noteServiceImpl.getNoteById(1L)).thenReturn(note);

        mockMvc.perform(get("/notes/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subject").value("Test Subject"))
                .andExpect(jsonPath("$.description").value("Test Content"));
    }
    
    // Test case for getting total count of notes
    @Test
    public void testGetTotalNoteCount() throws Exception {
        when(noteServiceImpl.countTotalNotes()).thenReturn(10L);

        mockMvc.perform(get("/notes/count"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf("10")));

    }
	
    
    
 // Test case for deleting a note by ID
    @Test
    public void testDeleteNote() throws Exception {
        doNothing().when(noteServiceImpl).deleteNote(1L);

        mockMvc.perform(delete("/notes/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Delete the with given id 1"));
    }


 // Test case for searching notes by subject
    @Test
    public void testSearchNoteBySubject() throws Exception {
        when(noteServiceImpl.findBySubject("Test")).thenReturn(List.of(note));

        mockMvc.perform(get("/notes/search")
                .param("subject", "Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].subject").value("Test Subject"))
                .andExpect(jsonPath("$[0].description").value("Test Content"));
    }
    // Test case for getting the total word count of a specific note
    @Test
    public void testGetTotalWordCount() throws Exception {
        when(noteServiceImpl.totalWordCountOfSpecificNote(1L)).thenReturn(10L);

        mockMvc.perform(get("/api/notes/word-count/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

    // Test case for getting the average note length
    @Test
    public void testGetAverageNoteLength() throws Exception {
        when(noteServiceImpl.averageLengthofAllNote()).thenReturn(150.5);

        mockMvc.perform(get("/api/notes/average-length"))
                .andExpect(status().isOk())
                .andExpect(content().string("150.5"));
    }
 // Test case for liking a note
    @Test
    public void testLikeNote() throws Exception {
        when(noteServiceImpl.likeNoteById(1L)).thenReturn(note);

        mockMvc.perform(post("/api/notes/{id}/like", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subject").value("Test Subject"))
                .andExpect(jsonPath("$.description").value("Test Content"));
    }

    @Test
    void testUnlikeNoteById() throws Exception {
        Note note = new Note();
        note.setNoteId(1L); 
        note.setSubject("Test Subject");
   	    note.setDescription("Test Content"); 
   	    note.setLikes(0);
        when(noteServiceImpl.unlikeNoteById(1L)).thenReturn(note);

        mockMvc.perform(delete("/api/notes/{id}/unlike", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subject").value("Test Subject"))
                .andExpect(jsonPath("$.likes").value(0));
    }

    @Test
    void testGetListOfLikedNotes() throws Exception {
        Note note1 = new Note();
        note1.setNoteId(1L); 
        note1.setSubject("Liked Subject1");
   	    note1.setDescription("Liked Subject1"); 
   	    note1.setLikes(0);
   	    
        Note note2 = new Note();
        note2.setNoteId(2L); 
        note2.setSubject("Liked Subject2");
   	    note2.setDescription("Liked Subject2"); 
   	    note2.setLikes(0);
        List<Note> likedNotes = Arrays.asList(note1, note2);

        when(noteServiceImpl.getAllLikedNotes()).thenReturn(likedNotes);

        mockMvc.perform(get("/api/notes/like"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].subject").value("Liked Subject1"))
                .andExpect(jsonPath("$[1].subject").value("Liked Subject2"));
    }
    @Test
    void testGetTopLikedNotes() throws Exception {
        Note note1 = new Note(1L, "Top Liked Subject1", "Top Liked Content1",null,null, 10);
        Note note2 = new Note(2L, "Top Liked Subject2", "Top Liked Content2",null,null, 8);
        List<Note> topLikedNotes = Arrays.asList(note1, note2);

        when(noteServiceImpl.getTopLikedNotes()).thenReturn(topLikedNotes);

        mockMvc.perform(get("/api/notes/top-liked"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].subject").value("Top Liked Subject1"))
                .andExpect(jsonPath("$[1].subject").value("Top Liked Subject2"));
    }
    @Test
    void testResetLikes() throws Exception {
        Note note = new Note(1L, "Test Subject", "Test Content",null,null, 0);
        when(noteServiceImpl.resetLikes(1L)).thenReturn(note);

        mockMvc.perform(delete("/api/notes/{id}/like-reset", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subject").value("Test Subject"))
                .andExpect(jsonPath("$.likes").value(0));
    }
    
    @Test
    void testLikeBoost() throws Exception {
        Note note = new Note(1L, "Test Subject", "Test Content",null,null, 5);
        when(noteServiceImpl.likeBoost(1L, 3)).thenReturn(note);

        mockMvc.perform(post("/api/notes/{id}/like-boost?boost=3", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subject").value("Test Subject"))
                .andExpect(jsonPath("$.likes").value(5));
    }



}
