package com.challenge.orderManager.entities;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.challenge.orderManager.entities.User.UserBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderx")
public class Order {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, unique = true)
    private long order_id;

	@NotNull
	private double totalOrder;
	
	@NotNull
	private int tablex;
	
	@NotNull
	private Date orderDate;
	
	@NotNull
	private boolean paid_account;
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderx", cascade = CascadeType.ALL, targetEntity = OrderItem.class)
    private List<OrderItem> products;
}
