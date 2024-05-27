package com.example.chat.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserData {
    @GeneratedValue
    @Id
    private long id;
    private long[] rooms;
    private String name;
    private long cipherId;
}
