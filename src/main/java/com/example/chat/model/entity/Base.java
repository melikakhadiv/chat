package com.example.chat.model.entity;


import com.google.gson.Gson;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder


@MappedSuperclass
public class Base implements Serializable {
    @Column(name = "active")
    @JsonbTransient
    private boolean active;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
