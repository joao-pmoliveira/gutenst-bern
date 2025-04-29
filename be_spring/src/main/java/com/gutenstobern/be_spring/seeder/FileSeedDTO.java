package com.gutenstobern.be_spring.seeder;

import java.util.List;

public class FileSeedDTO {
    String url;
    List<String> format;

    public FileSeedDTO() {
    }

    public String getUrl() {
        return url;
    }

    public List<String> getFormat() {
        return format;
    }

}
