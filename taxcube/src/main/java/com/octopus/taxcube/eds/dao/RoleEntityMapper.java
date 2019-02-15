package com.octopus.taxcube.eds.dao;

import com.octopus.taxcube.eds.entity.RoleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbg.generated Tue Feb 12 17:25:23 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbg.generated Tue Feb 12 17:25:23 CST 2019
     */
    int insert(RoleEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbg.generated Tue Feb 12 17:25:23 CST 2019
     */
    int insertSelective(RoleEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbg.generated Tue Feb 12 17:25:23 CST 2019
     */
    RoleEntity selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbg.generated Tue Feb 12 17:25:23 CST 2019
     */
    int updateByPrimaryKeySelective(RoleEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbg.generated Tue Feb 12 17:25:23 CST 2019
     */
    int updateByPrimaryKey(RoleEntity record);

    List<RoleEntity> selectByRoleIds(@Param("roleIds") List<Long> roleIds);

    List<RoleEntity> selectAll();
}