package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GameAccounts")
public class GameAccount {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @Column(nullable = false, length = 50)
    private String accountName;

    @Column(nullable = false, length = 50)
    private String gameName;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private double price;
    
    @OneToMany(mappedBy = "gameAccount", cascade = CascadeType.ALL)
    private List<Image> images;
    
    @Column(nullable = false)
    private boolean selled;
}
