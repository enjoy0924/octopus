package com.octopus.taxcube.eds.entity;

import java.util.Date;

public class CreditItemEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_item.id
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_item.account_id
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    private Long accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_item.credit
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    private Integer credit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_item.reason_type
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    private String reasonType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_item.source_id
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    private Long sourceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_item.remark
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_item.period
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    private String period;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_item.status
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_item.create_time
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_item.create_by
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    private Long createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_item.update_time
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_item.update_by
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    private Long updateBy;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_item.id
     *
     * @return the value of credit_item.id
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_item.id
     *
     * @param id the value for credit_item.id
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_item.account_id
     *
     * @return the value of credit_item.account_id
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_item.account_id
     *
     * @param accountId the value for credit_item.account_id
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_item.credit
     *
     * @return the value of credit_item.credit
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public Integer getCredit() {
        return credit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_item.credit
     *
     * @param credit the value for credit_item.credit
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_item.reason_type
     *
     * @return the value of credit_item.reason_type
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public String getReasonType() {
        return reasonType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_item.reason_type
     *
     * @param reasonType the value for credit_item.reason_type
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public void setReasonType(String reasonType) {
        this.reasonType = reasonType == null ? null : reasonType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_item.source_id
     *
     * @return the value of credit_item.source_id
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public Long getSourceId() {
        return sourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_item.source_id
     *
     * @param sourceId the value for credit_item.source_id
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_item.remark
     *
     * @return the value of credit_item.remark
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_item.remark
     *
     * @param remark the value for credit_item.remark
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_item.period
     *
     * @return the value of credit_item.period
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public String getPeriod() {
        return period;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_item.period
     *
     * @param period the value for credit_item.period
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public void setPeriod(String period) {
        this.period = period == null ? null : period.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_item.status
     *
     * @return the value of credit_item.status
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_item.status
     *
     * @param status the value for credit_item.status
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_item.create_time
     *
     * @return the value of credit_item.create_time
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_item.create_time
     *
     * @param createTime the value for credit_item.create_time
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_item.create_by
     *
     * @return the value of credit_item.create_by
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_item.create_by
     *
     * @param createBy the value for credit_item.create_by
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_item.update_time
     *
     * @return the value of credit_item.update_time
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_item.update_time
     *
     * @param updateTime the value for credit_item.update_time
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_item.update_by
     *
     * @return the value of credit_item.update_by
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_item.update_by
     *
     * @param updateBy the value for credit_item.update_by
     *
     * @mbg.generated Sat Jan 19 10:58:42 CST 2019
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
}