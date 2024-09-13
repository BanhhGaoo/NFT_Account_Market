package com.example.demo.entity;

import java.io.Serializable;

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
@Table(name = "AdminPayment")
public class AdminPayment {

	@EmbeddedId
	private AdminPaymentKey id;

	@ManyToOne
	@MapsId("adminId")
	@JoinColumn(name = "adminId")
	private Admin admin;

	@ManyToOne
	@MapsId("paymentId")
	@JoinColumn(name = "paymentId")
	private Payment payment;
}
