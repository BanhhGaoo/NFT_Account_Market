package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Entity
@Table(name = "blockchain")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Blockchain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionId")
    private int transactionId;

    @ManyToOne
    @JoinColumn(name = "paymentId", nullable = false)
    private Payment payment;

    @Column(name = "transactionHash", nullable = false, length = 255)
    private String transactionHash;
}
