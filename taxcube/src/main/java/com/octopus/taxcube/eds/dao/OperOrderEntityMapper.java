package com.octopus.taxcube.eds.dao;

import com.octopus.taxcube.eds.entity.OperOrderEntity;
import com.octopus.taxcube.eds.entity.OperOrderEntityKey;
import org.springframework.stereotype.Repository;

@Repository
public interface OperOrderEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oper_order
     *
     * @mbg.generated Tue Feb 12 20:33:58 CST 2019
     */
    int deleteByPrimaryKey(OperOrderEntityKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oper_order
     *
     * @mbg.generated Tue Feb 12 20:33:58 CST 2019
     */
    int insert(OperOrderEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oper_order
     *
     * @mbg.generated Tue Feb 12 20:33:58 CST 2019
     */
    int insertSelective(OperOrderEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oper_order
     *
     * @mbg.generated Tue Feb 12 20:33:58 CST 2019
     */
    OperOrderEntity selectByPrimaryKey(OperOrderEntityKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oper_order
     *
     * @mbg.generated Tue Feb 12 20:33:58 CST 2019
     */
    int updateByPrimaryKeySelective(OperOrderEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oper_order
     *
     * @mbg.generated Tue Feb 12 20:33:58 CST 2019
     */
    int updateByPrimaryKey(OperOrderEntity record);
}