package com.bjuan.tallerpruebas.model.prod;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.bjuan.tallerpruebas.services.validation.AddGroup;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * The persistent class for the productcosthistory database table.
 *
 */
@Entity
@NamedQuery(name = "Productcosthistory.findAll", query = "SELECT p FROM Productcosthistory p")
public class Productcosthistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer productcosthitoryid;

	@NotNull(groups = {AddGroup.class})
	private Integer associatedproduct;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate enddate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifieddate;

	@NotNull(groups = {AddGroup.class})
	@Positive(groups = {AddGroup.class})
	private BigDecimal standardcost;

	// bi-directional many-to-one association to Product
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "productid", updatable = false)
	private Product product;

	public Productcosthistory() {
	}

	public Integer getAssociatedproduct() {
		return this.associatedproduct;
	}

	public LocalDate getEnddate() {
		return this.enddate;
	}

	public Integer getProductcosthitoryid() {
		return this.productcosthitoryid;
	}

	public LocalDate getModifieddate() {
		return this.modifieddate;
	}

	public Product getProduct() {
		return this.product;
	}

	public BigDecimal getStandardcost() {
		return this.standardcost;
	}

	public void setAssociatedproduct(Integer associatedproduct) {
		this.associatedproduct = associatedproduct;
	}

	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}

	public void setPzvzroductcosthitoryid(Integer id) {
		this.productcosthitoryid = id;
	}

	public void setModifieddate(LocalDate modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setStandardcost(BigDecimal standardcost) {
		this.standardcost = standardcost;
	}

}
