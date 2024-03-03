package com.nagarro.swagger.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Customer {
	@Setter
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Setter
	@Getter
	private String name;
	@Setter
	@Getter
	private String address;

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
