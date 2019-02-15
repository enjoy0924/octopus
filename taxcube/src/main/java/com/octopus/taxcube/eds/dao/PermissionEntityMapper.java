package com.octopus.taxcube.eds.dao;

import com.octopus.taxcube.eds.entity.PermissionEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbg.generated Tue Feb 12 17:25:23 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbg.generated Tue Feb 12 17:25:23 CST 2019
     */
    int insert(PermissionEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbg.generated Tue Feb 12 17:25:23 CST 2019
     */
    int insertSelective(PermissionEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbg.generated Tue Feb 12 17:25:23 CST 2019
     */
    PermissionEntity selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbg.generated Tue Feb 12 17:25:23 CST 2019
     */
    int updateByPrimaryKeySelective(PermissionEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbg.generated Tue Feb 12 17:25:23 CST 2019
     */
    int updateByPrimaryKey(PermissionEntity record);

    List<PermissionEntity> selectByPermissionIds(@Param("permissionIds") List<Long> permissionIds);

    List<PermissionEntity> selectAll();
}