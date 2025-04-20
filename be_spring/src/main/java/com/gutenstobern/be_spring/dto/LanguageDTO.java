
package com.gutenstobern.be_spring.dto;

import com.gutenstobern.be_spring.entity.Language;

public class LanguageDTO {
    Long id;
    String name;
    String code;

    public LanguageDTO() {
    }

    public LanguageDTO(Language language) {
        this.id = language.getId();
        this.name = language.getName();
        this.code = language.getIsoCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
