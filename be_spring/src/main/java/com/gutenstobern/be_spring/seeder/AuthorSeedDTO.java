package com.gutenstobern.be_spring.seeder;

import java.util.List;

public class AuthorSeedDTO {
    String name;
    String birth;
    String death;
    List<String> aliases;
    String wiki;

    public AuthorSeedDTO() {
    }

    public String getName() {
        return name;
    }

    public String getBirth() {
        return birth;
    }

    public String getDeath() {
        return death;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getWiki() {
        return wiki;
    }

}
