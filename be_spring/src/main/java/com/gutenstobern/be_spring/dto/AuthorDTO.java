package com.gutenstobern.be_spring.dto;

public class AuthorDTO {
    Long id;
    String name;

    public AuthorDTO() {
    }

    public AuthorDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
