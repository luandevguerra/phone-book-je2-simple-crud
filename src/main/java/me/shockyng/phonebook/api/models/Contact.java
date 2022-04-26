package me.shockyng.phonebook.api.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contact_name")
    @NotNull(message = "Name cannot be null")
    private String name;

}
