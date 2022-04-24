package com.ecommerce.antique.store.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long productid;
	
	private String productname;
	private String productdescription;
	private String productimage;
	private BigDecimal price;
	private int discountpercent;
	
	@ManyToOne
	@JoinColumn(name = "categoryid", referencedColumnName = "categoryid")
	@JsonIgnore
	private Product product;
}
