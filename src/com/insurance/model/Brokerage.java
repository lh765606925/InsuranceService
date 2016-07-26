package com.insurance.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_brokerage")
public class Brokerage extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String productId;
	private String brokerage1;

}
