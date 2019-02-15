package com.octopus.taxcube.eds.entity;

import java.util.Date;

public class OrderEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.id
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.service_id
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private Long serviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.customer_id
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private Long customerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.emp_id
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private Long empId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.location
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private String location;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.company_id
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private Long companyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.price_quotation
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private Double priceQuotation;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.price_pay
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private Double pricePay;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.credit_emp
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private Double creditEmp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.order_status
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private String orderStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.serve_status
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private String serveStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.check_status
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private String checkStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.pay_status
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private String payStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.distribute_status
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private String distributeStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.quotation_status
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private String quotationStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.remark
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.create_time
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.create_by
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private Long createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.update_time
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.update_by
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    private Long updateBy;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.id
     *
     * @return the value of order_item.id
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.id
     *
     * @param id the value for order_item.id
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.service_id
     *
     * @return the value of order_item.service_id
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public Long getServiceId() {
        return serviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.service_id
     *
     * @param serviceId the value for order_item.service_id
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.customer_id
     *
     * @return the value of order_item.customer_id
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.customer_id
     *
     * @param customerId the value for order_item.customer_id
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.emp_id
     *
     * @return the value of order_item.emp_id
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public Long getEmpId() {
        return empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.emp_id
     *
     * @param empId the value for order_item.emp_id
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.location
     *
     * @return the value of order_item.location
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.location
     *
     * @param location the value for order_item.location
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.company_id
     *
     * @return the value of order_item.company_id
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.company_id
     *
     * @param companyId the value for order_item.company_id
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.price_quotation
     *
     * @return the value of order_item.price_quotation
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public Double getPriceQuotation() {
        return priceQuotation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.price_quotation
     *
     * @param priceQuotation the value for order_item.price_quotation
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setPriceQuotation(Double priceQuotation) {
        this.priceQuotation = priceQuotation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.price_pay
     *
     * @return the value of order_item.price_pay
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public Double getPricePay() {
        return pricePay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.price_pay
     *
     * @param pricePay the value for order_item.price_pay
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setPricePay(Double pricePay) {
        this.pricePay = pricePay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.credit_emp
     *
     * @return the value of order_item.credit_emp
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public Double getCreditEmp() {
        return creditEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.credit_emp
     *
     * @param creditEmp the value for order_item.credit_emp
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setCreditEmp(Double creditEmp) {
        this.creditEmp = creditEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.order_status
     *
     * @return the value of order_item.order_status
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.order_status
     *
     * @param orderStatus the value for order_item.order_status
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.serve_status
     *
     * @return the value of order_item.serve_status
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public String getServeStatus() {
        return serveStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.serve_status
     *
     * @param serveStatus the value for order_item.serve_status
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setServeStatus(String serveStatus) {
        this.serveStatus = serveStatus == null ? null : serveStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.check_status
     *
     * @return the value of order_item.check_status
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public String getCheckStatus() {
        return checkStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.check_status
     *
     * @param checkStatus the value for order_item.check_status
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.pay_status
     *
     * @return the value of order_item.pay_status
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public String getPayStatus() {
        return payStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.pay_status
     *
     * @param payStatus the value for order_item.pay_status
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.distribute_status
     *
     * @return the value of order_item.distribute_status
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public String getDistributeStatus() {
        return distributeStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.distribute_status
     *
     * @param distributeStatus the value for order_item.distribute_status
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setDistributeStatus(String distributeStatus) {
        this.distributeStatus = distributeStatus == null ? null : distributeStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.quotation_status
     *
     * @return the value of order_item.quotation_status
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public String getQuotationStatus() {
        return quotationStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.quotation_status
     *
     * @param quotationStatus the value for order_item.quotation_status
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setQuotationStatus(String quotationStatus) {
        this.quotationStatus = quotationStatus == null ? null : quotationStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.remark
     *
     * @return the value of order_item.remark
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.remark
     *
     * @param remark the value for order_item.remark
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.create_time
     *
     * @return the value of order_item.create_time
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.create_time
     *
     * @param createTime the value for order_item.create_time
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.create_by
     *
     * @return the value of order_item.create_by
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.create_by
     *
     * @param createBy the value for order_item.create_by
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.update_time
     *
     * @return the value of order_item.update_time
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.update_time
     *
     * @param updateTime the value for order_item.update_time
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.update_by
     *
     * @return the value of order_item.update_by
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.update_by
     *
     * @param updateBy the value for order_item.update_by
     *
     * @mbg.generated Thu Jan 24 16:50:39 CST 2019
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
}