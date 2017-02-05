package com.logiccache.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@Entity
public class Character {

    public Character() {
    }

    public Character(String name) {
        Assert.notNull(name, "Name must not be null.");
        this.name = name;
    }

    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;
}
