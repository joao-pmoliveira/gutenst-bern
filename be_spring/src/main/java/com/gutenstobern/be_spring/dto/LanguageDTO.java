package com.gutenstobern.be_spring.dto;

public class LanguageDTO {
    private String isoCode;
    private String name;

    public LanguageDTO(String isoCode, String name) {
        this.isoCode = isoCode;
        this.name = name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public String getName() {
        return name;
    }
}
