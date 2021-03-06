package com.octopus.taxcube.eds.entity;

import java.util.Date;

public class OrderCheckEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_trace_check.id
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_trace_check.order_id
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    private String orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_trace_check.check_status
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    private String checkStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_trace_check.remark
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_trace_check.check_by
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    private Long checkBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_trace_check.check_time
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    private Date checkTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_trace_check.create_by
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    private Long createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_trace_check.create_time
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_trace_check.id
     *
     * @return the value of order_trace_check.id
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_trace_check.id
     *
     * @param id the value for order_trace_check.id
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_trace_check.order_id
     *
     * @return the value of order_trace_check.order_id
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_trace_check.order_id
     *
     * @param orderId the value for order_trace_check.order_id
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_trace_check.check_status
     *
     * @return the value of order_trace_check.check_status
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    public String getCheckStatus() {
        return checkStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_trace_check.check_status
     *
     * @param checkStatus the value for order_trace_check.check_status
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_trace_check.remark
     *
     * @return the value of order_trace_check.remark
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_trace_check.remark
     *
     * @param remark the value for order_trace_check.remark
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_trace_check.check_by
     *
     * @return the value of order_trace_check.check_by
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    public Long getCheckBy() {
        return checkBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_trace_check.check_by
     *
     * @param checkBy the value for order_trace_check.check_by
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    public void setCheckBy(Long checkBy) {
        this.checkBy = checkBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_trace_check.check_time
     *
     * @return the value of order_trace_check.check_time
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    public Date getCheckTime() {
        return checkTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_trace_check.check_time
     *
     * @param checkTime the value for order_trace_check.check_time
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_trace_check.create_by
     *
     * @return the value of order_trace_check.create_by
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_trace_check.create_by
     *
     * @param createBy the value for order_trace_check.create_by
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_trace_check.create_time
     *
     * @return the value of order_trace_check.create_time
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_trace_check.create_time
     *
     * @param createTime the value for order_trace_check.create_time
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}