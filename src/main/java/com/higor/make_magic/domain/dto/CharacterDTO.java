package com.higor.make_magic.domain.dto;

import com.higor.make_magic.domain.entity.Character;


public class CharacterDTO {

    private String name;
    private String role;
    private String school;
    private String house;
    private String patronus;

    public String getName() {return this.name;}

    public void setName(String name) {this.name = name;}

    public String getRole() {return this.role;}

    public void setRole(String role) {this.role = role;}

    public String getSchool() {return this.school;}

    public void setSchool(String school) {this.school = school;}

    public String getHouse() {return this.house;}

    public void setHouse(String house) {this.house = house;}

    public String getPatronus() {return this.patronus;}

    public void setPatronus(String patronus) {this.patronus = patronus;}

    public Character toEntity(){
        return new Character(
                this.name,
                this.role,
                this.school,
                this.house,
                this.patronus
        );
    }
}
