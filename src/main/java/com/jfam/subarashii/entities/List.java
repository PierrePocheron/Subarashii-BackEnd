package com.jfam.subarashii.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "list")
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_list;

    @Column @NotBlank
    private String nom;

    @Column
    private Boolean Default;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

//    @ManyToMany
//    @JoinTable(name = "user_list",
//            joinColumns = @JoinColumn(name = "id_Anime"),
//            inverseJoinColumns = @JoinColumn(name = "id_List"))
//    private  Li;

}
