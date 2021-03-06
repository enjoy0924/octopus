package com.octopus.taxcube.eds.dao;

import com.octopus.taxcube.eds.entity.OrderTaskEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.security.PermitAll;
import java.util.List;

@Repository
public interface OrderTaskEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_trace_task
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_trace_task
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    int insert(OrderTaskEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_trace_task
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    int insertSelective(OrderTaskEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_trace_task
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    OrderTaskEntity selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_trace_task
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    int updateByPrimaryKeySelective(OrderTaskEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_trace_task
     *
     * @mbg.generated Thu Jan 24 12:38:26 CST 2019
     */
    int updateByPrimaryKey(OrderTaskEntity record);

    List<OrderTaskEntity> selectByOrderId(@Param("orderId") String orderId);

    List<OrderTaskEntity> selectOrderTasksByOrderIds(@Param("orderIds") List<String> orderIds);

}