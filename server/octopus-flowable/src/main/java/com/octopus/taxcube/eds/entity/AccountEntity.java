package com.octopus.taxcube.eds.entity;

import java.util.Date;

public class AccountEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account.id
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account.name
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account.wechat_id
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    private String wechatId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account.type
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account.phone
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account.avatar
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    private String avatar;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account.password
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account.qr_code
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    private Long qrCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account.status
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account.create_time
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account.create_by
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    private Long createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account.update_time
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account.update_by
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    private Long updateBy;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account.id
     *
     * @return the value of sys_account.id
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account.id
     *
     * @param id the value for sys_account.id
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account.name
     *
     * @return the value of sys_account.name
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account.name
     *
     * @param name the value for sys_account.name
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account.wechat_id
     *
     * @return the value of sys_account.wechat_id
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public String getWechatId() {
        return wechatId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account.wechat_id
     *
     * @param wechatId the value for sys_account.wechat_id
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public void setWechatId(String wechatId) {
        this.wechatId = wechatId == null ? null : wechatId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account.type
     *
     * @return the value of sys_account.type
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account.type
     *
     * @param type the value for sys_account.type
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account.phone
     *
     * @return the value of sys_account.phone
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account.phone
     *
     * @param phone the value for sys_account.phone
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account.avatar
     *
     * @return the value of sys_account.avatar
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account.avatar
     *
     * @param avatar the value for sys_account.avatar
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account.password
     *
     * @return the value of sys_account.password
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account.password
     *
     * @param password the value for sys_account.password
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account.qr_code
     *
     * @return the value of sys_account.qr_code
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public Long getQrCode() {
        return qrCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account.qr_code
     *
     * @param qrCode the value for sys_account.qr_code
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public void setQrCode(Long qrCode) {
        this.qrCode = qrCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account.status
     *
     * @return the value of sys_account.status
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account.status
     *
     * @param status the value for sys_account.status
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account.create_time
     *
     * @return the value of sys_account.create_time
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account.create_time
     *
     * @param createTime the value for sys_account.create_time
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account.create_by
     *
     * @return the value of sys_account.create_by
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account.create_by
     *
     * @param createBy the value for sys_account.create_by
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account.update_time
     *
     * @return the value of sys_account.update_time
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account.update_time
     *
     * @param updateTime the value for sys_account.update_time
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account.update_by
     *
     * @return the value of sys_account.update_by
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account.update_by
     *
     * @param updateBy the value for sys_account.update_by
     *
     * @mbg.generated Thu Jan 10 16:05:30 CST 2019
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
}