package com.example.demo.entity;

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
@Table(name = "CartItems")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartItemId;

	@ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

	@ManyToOne
	@JoinColumn(name = "accountId", nullable = false)
	private GameAccount gameAccount;
}
