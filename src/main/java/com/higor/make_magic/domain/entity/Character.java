package com.higor.make_magic.domain.entity;

import com.higor.make_magic.domain.dto.CharacterDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="character")
public class Character implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "Character name must be passed")
    @NotEmpty(message = "Character name must be passed")
    @NotBlank(message = "Character name must be passed")
    private String name;

    @NotNull(message = "Character role must be passed")
    @NotEmpty(message = "Character role must be passed")
    @NotBlank(message = "Character role must be passed")
    private String role;

    @NotNull(message = "Character school must be passed")
    @NotEmpty(message = "Character school must be passed")
    @NotBlank(message = "Character school must be passed")
    private String school;

    @NotNull(message = "Character house must be passed")
    @NotEmpty(message = "Character house must be passed")
    @NotBlank(message = "Character house must be passed")
    private String house;

    @NotNull(message = "Character patronus must be passed")
    @NotEmpty(message = "Character patronus must be passed")
    @NotBlank(message = "Character patronus must be passed")
    private String patronus;

    public Character() {}

    public Character(String name, String role, String school, String house, String patronus) {
        this.name = name;
        this.role = role;
        this.school = school;
        this.house = house;
        this.patronus = patronus;
    }

    public Character(String house, String patronus) {
        this.house = house;
        this.patronus = patronus;
    }

    public Long getId() {return this.id;}
    public String getName() {return this.name;}

    public void setName(String name) {
        if(name != null && !name.isEmpty() && !name.isBlank()){
            this.name = name;
        }
    }

    public String getRole() {return this.role;}

    public void setRole(String role) {
        if(role != null && !role.isEmpty() && !role.isBlank()){
            this.role = role;
        }
    }

    public String getSchool() {return this.school;}

    public void setSchool(String school) {
        if(school != null && !school.isEmpty() && !school.isBlank()){
            this.school = school;
        }
    }

    public String getHouse() {return this.house;}

    public void setHouse(String house) {
        if (house != null && !house.isEmpty() && !house.isBlank()){
            this.house = house;
        }
    }

    public String getPatronus() {return this.patronus;}

    public void setPatronus(String patronus) {
        if(patronus != null && !patronus.isEmpty() && !patronus.isBlank()){
            this.patronus = patronus;
        }
    }

    public void updateData(CharacterDTO characterDTO) {
        this.setName(characterDTO.getName());
        this.setRole(characterDTO.getRole());
        this.setSchool(characterDTO.getSchool());
        this.setPatronus(characterDTO.getPatronus());
        this.setHouse(characterDTO.getHouse());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Character)) return false;
        Character character = (Character) o;
        return Objects.equals(id, character.id) &&
                name.equals(character.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
