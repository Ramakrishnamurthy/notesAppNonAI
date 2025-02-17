package com.java.noteApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/*@Data
@Getter
@Setter
@Builder*/
@Entity
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="noteId")
	private Long noteId;
	
	@Column(name="subject")
	private String subject;
	
	@Column(name="description")
	private String description;
	
	@Column(name="timestampCreated")
	private String timestampCreated;
	
	@Column(name="timestampUpdated")
	private String timestampUpdated;

	//private boolean likes;
	public Note() {
		super();
	}


	public Note(Long noteId, String subject, String description, String timestampCreated, String timestampUpdated) {
		super();
		this.noteId = noteId;
		this.subject = subject;
		this.description = description;
		this.timestampCreated = timestampCreated;
		this.timestampUpdated = timestampUpdated;
	}


	public Long getNoteId() {
		return noteId;
	}


	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getTimestampCreated() {
		return timestampCreated;
	}


	public void setTimestampCreated(String timestampCreated) {
		this.timestampCreated = timestampCreated;
	}


	public String getTimestampUpdated() {
		return timestampUpdated;
	}


	public void setTimestampUpdated(String timestampUpdated) {
		this.timestampUpdated = timestampUpdated;
	}


	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", subject=" + subject + ", description=" + description
				+ ", timestampCreated=" + timestampCreated + ", timestampUpdated=" + timestampUpdated + "]";
	}
	
	
	
	
	
	
}
