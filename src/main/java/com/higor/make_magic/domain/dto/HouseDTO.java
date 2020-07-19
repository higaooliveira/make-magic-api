package com.higor.make_magic.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class HouseDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String _id;
    private String name;

    public HouseDTO(String id, String name) {
        this._id = id;
        this.name = name;
    }

    public String getId() {return this._id;}

    public void setId(String id) {this._id = id;}

    public String getName() {return this.name;}

    public void setName(String name) {this.name = name;}
}
