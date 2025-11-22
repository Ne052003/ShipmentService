package com.neoapps.model;

public class Address {
    private Long id;
    private Long customerId;
    private String country;
    private String addressDetail;
    private Integer zipCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "{" +
                "\"addressDetail\":\"" + addressDetail +
                "\",\"zipCode\":" + zipCode +
                ", \"country\":\"" + country +
                "\"}";
    }
}
