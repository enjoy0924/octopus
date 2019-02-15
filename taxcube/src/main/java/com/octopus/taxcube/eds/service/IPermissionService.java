package com.octopus.taxcube.eds.service;

import com.octopus.taxcube.eds.pojo.Permission;
import com.octopus.taxcube.eds.pojo.Role;

import java.util.List;

public interface IPermissionService {

    List<Role> findRolesByUserId(String userId);

    List<Permission> findPermissionsByUserId(String userId);

    List<Permission> findPermissionsByRoleId(Long roleId);

    void createRoleByNameAndDescription(String name, String description);

    List<Role> findRoles();

    void delRoleById(Long roleId);

    void changeRolePermission(Long roleId, List<Long> permissionIds);

    void dismissRoleByUserIdAndRoleId(Long userId, Long roleId);

    void assignRole(Long userId, Long roleId);

    List<Permission> findPermissions();

    List<Role> findRolesWithPermission();

}
