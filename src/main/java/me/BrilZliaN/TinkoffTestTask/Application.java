package me.BrilZliaN.TinkoffTestTask;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Application {

	private int id;
	private int contactId;
	private Date dtCreated;
	private String productName;
	
	@JsonProperty("APPLICATION_ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty("CONTACT_ID")
	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	@JsonProperty("DT_CREATED")
	public Date getDtCreated() {
		return dtCreated;
	}

	public void setDtCreated(Date dtCreated) {
		this.dtCreated = dtCreated;
	}

	@JsonProperty("PRODUCT_NAME")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
