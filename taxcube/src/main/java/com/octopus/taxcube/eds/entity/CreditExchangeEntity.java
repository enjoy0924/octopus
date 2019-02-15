package com.octopus.taxcube.eds.entity;

import java.util.Date;

public class CreditExchangeEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_exchange.id
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_exchange.account_id
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    private Long accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_exchange.credit
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    private Integer credit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_exchange.exchange_type
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    private String exchangeType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_exchange.exchange_value
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    private Double exchangeValue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_exchange.remark
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_exchange.status
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_exchange.create_time
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_exchange.create_by
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    private Long createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_exchange.update_time
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_exchange.update_by
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    private Long updateBy;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_exchange.id
     *
     * @return the value of credit_exchange.id
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_exchange.id
     *
     * @param id the value for credit_exchange.id
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_exchange.account_id
     *
     * @return the value of credit_exchange.account_id
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_exchange.account_id
     *
     * @param accountId the value for credit_exchange.account_id
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_exchange.credit
     *
     * @return the value of credit_exchange.credit
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public Integer getCredit() {
        return credit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_exchange.credit
     *
     * @param credit the value for credit_exchange.credit
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_exchange.exchange_type
     *
     * @return the value of credit_exchange.exchange_type
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public String getExchangeType() {
        return exchangeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_exchange.exchange_type
     *
     * @param exchangeType the value for credit_exchange.exchange_type
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType == null ? null : exchangeType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_exchange.exchange_value
     *
     * @return the value of credit_exchange.exchange_value
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public Double getExchangeValue() {
        return exchangeValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_exchange.exchange_value
     *
     * @param exchangeValue the value for credit_exchange.exchange_value
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public void setExchangeValue(Double exchangeValue) {
        this.exchangeValue = exchangeValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_exchange.remark
     *
     * @return the value of credit_exchange.remark
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_exchange.remark
     *
     * @param remark the value for credit_exchange.remark
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_exchange.status
     *
     * @return the value of credit_exchange.status
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_exchange.status
     *
     * @param status the value for credit_exchange.status
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_exchange.create_time
     *
     * @return the value of credit_exchange.create_time
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_exchange.create_time
     *
     * @param createTime the value for credit_exchange.create_time
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_exchange.create_by
     *
     * @return the value of credit_exchange.create_by
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_exchange.create_by
     *
     * @param createBy the value for credit_exchange.create_by
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_exchange.update_time
     *
     * @return the value of credit_exchange.update_time
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_exchange.update_time
     *
     * @param updateTime the value for credit_exchange.update_time
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_exchange.update_by
     *
     * @return the value of credit_exchange.update_by
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_exchange.update_by
     *
     * @param updateBy the value for credit_exchange.update_by
     *
     * @mbg.generated Sat Jan 19 15:52:08 CST 2019
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
}