package com.octopus.taxcube.eds.entity;

import java.util.Date;

public class EmpOfferEntity extends EmpOfferEntityKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp_offer.status
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp_offer.create_time
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp_offer.create_by
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    private Long createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp_offer.update_time
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp_offer.update_by
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    private Long updateBy;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp_offer.status
     *
     * @return the value of emp_offer.status
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp_offer.status
     *
     * @param status the value for emp_offer.status
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp_offer.create_time
     *
     * @return the value of emp_offer.create_time
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp_offer.create_time
     *
     * @param createTime the value for emp_offer.create_time
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp_offer.create_by
     *
     * @return the value of emp_offer.create_by
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp_offer.create_by
     *
     * @param createBy the value for emp_offer.create_by
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp_offer.update_time
     *
     * @return the value of emp_offer.update_time
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp_offer.update_time
     *
     * @param updateTime the value for emp_offer.update_time
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp_offer.update_by
     *
     * @return the value of emp_offer.update_by
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp_offer.update_by
     *
     * @param updateBy the value for emp_offer.update_by
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
}