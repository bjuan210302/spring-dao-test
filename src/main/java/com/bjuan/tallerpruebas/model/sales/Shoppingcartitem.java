package com.bjuan.tallerpruebas.model.sales;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import com.bjuan.tallerpruebas.services.validation.AddGroup;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * The persistent class for the shoppingcartitem database table.
 * 
 */
@Entity
@NamedQuery(name="Shoppingcartitem.findAll", query="SELECT s FROM Shoppingcartitem s")
public class Shoppingcartitem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SHOPPINGCARTITEM_SHOPPINGCARTITEMID_GENERATOR",allocationSize = 1, sequenceName="SHOPPINGCARTITEM_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SHOPPINGCARTITEM_SHOPPINGCARTITEMID_GENERATOR")
	private Integer shoppingcartitemid;

	@NotNull(groups = {AddGroup.class})
	@PastOrPresent(groups = {AddGroup.class})
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate datecreated;

	private Timestamp modifieddate;

	private Integer productid;

	@NotNull(groups = {AddGroup.class})
	@Positive(groups = {AddGroup.class})
	private Integer quantity;

	private Integer shoppingcartid;

	@NotNull(groups = {AddGroup.class})
	private Integer associatedproduct;

	public Shoppingcartitem() {
	}

	public Integer getShoppingcartitemid() {
		return this.shoppingcartitemid;
	}

	public void setShoppingcartitemid(Integer shoppingcartitemid) {
		this.shoppingcartitemid = shoppingcartitemid;
	}

	public LocalDate getDatecreated() {
		return this.datecreated;
	}

	public void setDatecreated(LocalDate datecreated) {
		this.datecreated = datecreated;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public Integer getProductid() {
		return this.productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getShoppingcartid() {
		return this.shoppingcartid;
	}

	public void setShoppingcartid(Integer shoppingcartid) {
		this.shoppingcartid = shoppingcartid;
	}
	
	public Integer getAssociatedproduct() {
		return this.associatedproduct;
	}

	public void setAssociatedproduct(Integer associatedproduct) {
		this.associatedproduct = associatedproduct;
	}
}
