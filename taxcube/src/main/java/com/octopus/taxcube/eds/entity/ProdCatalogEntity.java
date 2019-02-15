package com.octopus.taxcube.eds.entity;

import java.util.Date;

public class ProdCatalogEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column prod_catalog.id
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column prod_catalog.name
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column prod_catalog.create_time
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column prod_catalog.create_by
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    private Long createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column prod_catalog.update_time
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column prod_catalog.update_by
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    private Long updateBy;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column prod_catalog.id
     *
     * @return the value of prod_catalog.id
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column prod_catalog.id
     *
     * @param id the value for prod_catalog.id
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column prod_catalog.name
     *
     * @return the value of prod_catalog.name
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column prod_catalog.name
     *
     * @param name the value for prod_catalog.name
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column prod_catalog.create_time
     *
     * @return the value of prod_catalog.create_time
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column prod_catalog.create_time
     *
     * @param createTime the value for prod_catalog.create_time
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column prod_catalog.create_by
     *
     * @return the value of prod_catalog.create_by
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column prod_catalog.create_by
     *
     * @param createBy the value for prod_catalog.create_by
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column prod_catalog.update_time
     *
     * @return the value of prod_catalog.update_time
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column prod_catalog.update_time
     *
     * @param updateTime the value for prod_catalog.update_time
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column prod_catalog.update_by
     *
     * @return the value of prod_catalog.update_by
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column prod_catalog.update_by
     *
     * @param updateBy the value for prod_catalog.update_by
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
}