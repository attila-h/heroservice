package com.reactheroes.userservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeroCard {

    @Id
    @GeneratedValue
    private Long id;

    private Long cardId;

    @ColumnDefault("0")
    private Long xp;

    @JsonBackReference
    @ManyToOne()
    private UserDetail userDetail;
}