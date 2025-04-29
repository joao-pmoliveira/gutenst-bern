package com.gutenstobern.be_spring.seeder;

import java.util.List;

public class BookSeedDTO {
    String title;
    String issued_at;
    String downloads;
    String publisher;
    String rights;
    String description;
    List<String> language;
    List<String> subjects;
    List<AuthorSeedDTO> authors;
    List<FileSeedDTO> files;

    public BookSeedDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssued_at() {
        return issued_at;
    }

    public void setIssued_at(String issued_at) {
        this.issued_at = issued_at;
    }

    public String getDownloads() {
        return downloads;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> languages) {
        this.language = languages;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<AuthorSeedDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorSeedDTO> authors) {
        this.authors = authors;
    }

    public List<FileSeedDTO> getFiles() {
        return files;
    }

    public void setFiles(List<FileSeedDTO> files) {
        this.files = files;
    }

}
