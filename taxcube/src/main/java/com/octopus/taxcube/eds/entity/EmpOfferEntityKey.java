package com.octopus.taxcube.eds.entity;

public class EmpOfferEntityKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp_offer.emp_id
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    private Long empId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp_offer.service_id
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    private Long serviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp_offer.location
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    private String location;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp_offer.emp_id
     *
     * @return the value of emp_offer.emp_id
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    public Long getEmpId() {
        return empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp_offer.emp_id
     *
     * @param empId the value for emp_offer.emp_id
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp_offer.service_id
     *
     * @return the value of emp_offer.service_id
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    public Long getServiceId() {
        return serviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp_offer.service_id
     *
     * @param serviceId the value for emp_offer.service_id
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp_offer.location
     *
     * @return the value of emp_offer.location
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp_offer.location
     *
     * @param location the value for emp_offer.location
     *
     * @mbg.generated Tue Jan 15 18:51:04 CST 2019
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }
}