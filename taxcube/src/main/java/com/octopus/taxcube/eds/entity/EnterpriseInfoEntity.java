package com.octopus.taxcube.eds.entity;

import java.util.Date;

public class EnterpriseInfoEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column enterprise_info.id
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column enterprise_info.name
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column enterprise_info.address
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column enterprise_info.licence_image
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    private String telephone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column enterprise_info.create_time
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column enterprise_info.create_by
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    private Long createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column enterprise_info.update_time
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column enterprise_info.update_by
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    private Long updateBy;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column enterprise_info.id
     *
     * @return the value of enterprise_info.id
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column enterprise_info.id
     *
     * @param id the value for enterprise_info.id
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column enterprise_info.name
     *
     * @return the value of enterprise_info.name
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column enterprise_info.name
     *
     * @param name the value for enterprise_info.name
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column enterprise_info.address
     *
     * @return the value of enterprise_info.address
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column enterprise_info.address
     *
     * @param address the value for enterprise_info.address
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column enterprise_info.licence_image
     *
     * @return the value of enterprise_info.licence_image
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column enterprise_info.licence_image
     *
     * @param telephone the value for enterprise_info.licence_image
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column enterprise_info.create_time
     *
     * @return the value of enterprise_info.create_time
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column enterprise_info.create_time
     *
     * @param createTime the value for enterprise_info.create_time
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column enterprise_info.create_by
     *
     * @return the value of enterprise_info.create_by
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column enterprise_info.create_by
     *
     * @param createBy the value for enterprise_info.create_by
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column enterprise_info.update_time
     *
     * @return the value of enterprise_info.update_time
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column enterprise_info.update_time
     *
     * @param updateTime the value for enterprise_info.update_time
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column enterprise_info.update_by
     *
     * @return the value of enterprise_info.update_by
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column enterprise_info.update_by
     *
     * @param updateBy the value for enterprise_info.update_by
     *
     * @mbg.generated Tue Feb 12 11:46:24 CST 2019
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
}