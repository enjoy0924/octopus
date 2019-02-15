package com.octopus.taxcube.eds.service.impl;

import com.octopus.taxcube.common.lang.DateUtils;
import com.octopus.taxcube.eds.dao.AccountRoleEntityMapper;
import com.octopus.taxcube.eds.dao.PermissionEntityMapper;
import com.octopus.taxcube.eds.dao.RoleEntityMapper;
import com.octopus.taxcube.eds.dao.RolePermissionEntityMapper;
import com.octopus.taxcube.eds.entity.AccountRoleEntity;
import com.octopus.taxcube.eds.entity.PermissionEntity;
import com.octopus.taxcube.eds.entity.RoleEntity;
import com.octopus.taxcube.eds.entity.RolePermissionEntity;
import com.octopus.taxcube.eds.pojo.Permission;
import com.octopus.taxcube.eds.pojo.Role;
import com.octopus.taxcube.eds.service.IPermissionService;
import com.octopus.taxcube.util.EdsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private AccountRoleEntityMapper accountRoleEntityMapper;

    @Autowired
    private RoleEntityMapper roleEntityMapper;

    @Autowired
    private RolePermissionEntityMapper rolePermissionEntityMapper;

    @Autowired
    private PermissionEntityMapper permissionEntityMapper;

    private List<Long> findRoleIdsByUserId(String userId){

        List<AccountRoleEntity> accountRoleEntities = accountRoleEntityMapper.selectByUserId(userId);
        if(null == accountRoleEntities || accountRoleEntities.isEmpty()){
            return Collections.emptyList();
        }

        List<Long> roleIds = new ArrayList<>();
        for(AccountRoleEntity accountRoleEntity : accountRoleEntities){
            roleIds.add(accountRoleEntity.getRoleId());
        }

        return roleIds;
    }

    private List<Long> findPermissionIdsByRoleIds(List<Long> roleIds){

        List<RolePermissionEntity> rolePermissionEntities = rolePermissionEntityMapper.selectByRoleIds(roleIds);
        if(null == rolePermissionEntities || rolePermissionEntities.isEmpty()){
            return Collections.emptyList();
        }

        List<Long> permissionIds = new ArrayList<>();
        for(RolePermissionEntity rolePermissionEntity: rolePermissionEntities){
            permissionIds.add(rolePermissionEntity.getPermissionId());
        }

        return permissionIds;
    }

    @Override
    public List<Role> findRolesByUserId(String userId) {

        List<Long> roleIds = findRoleIdsByUserId(userId);

        List<RoleEntity> roleEntities = roleEntityMapper.selectByRoleIds(roleIds);
        if((null == roleEntities) || roleEntities.isEmpty()){
            return Collections.emptyList();
        }

        List<Role> roles = new ArrayList<>();
        for(RoleEntity roleEntity : roleEntities){
            Role role = new Role(roleEntity.getRoleName());
            role.setRoleId(roleEntity.getId());
            role.setDescription(roleEntity.getDescription());

            roles.add(role);
        }

        return roles;
    }


    @Override
    public List<Permission> findPermissionsByUserId(String userId) {

        return findPermissionsByPermissionIds(
                findPermissionIdsByRoleIds(
                        findRoleIdsByUserId(userId)
                )
        );

    }

    @Override
    public List<Permission> findPermissionsByRoleId(Long roleId) {

        List<Long> roleIds = new ArrayList<>();
        roleIds.add(roleId);

        return findPermissionsByPermissionIds(
                findPermissionIdsByRoleIds(roleIds)
        );

    }

    private List<Permission> findPermissionsByPermissionIds(List<Long> permissionIds){

        if(null == permissionIds || permissionIds.isEmpty())
            return Collections.emptyList();

        List<PermissionEntity> permissionEntities = permissionEntityMapper.selectByPermissionIds(permissionIds);
        if(null == permissionEntities || permissionEntities.isEmpty()){
            return Collections.emptyList();
        }

        List<Permission> permissions = new ArrayList<>();
        for(PermissionEntity permissionEntity : permissionEntities){
            Permission permission = new Permission();

            permission.setMenuName(permissionEntity.getMenuName());
            permission.setMenuCode(permissionEntity.getMenuCode());
            permission.setPermissionName(permissionEntity.getPermissionName());
            permission.setPermissionCode(permissionEntity.getPermissionCode());
            permission.setPermissionId(permissionEntity.getId());

            permissions.add(permission);
        }

        return permissions;
    }

    @Override
    public void createRoleByNameAndDescription(String name, String description) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName(name);
        roleEntity.setDescription(description);
        roleEntity.setCreateTime(new Date());
        roleEntity.setCreateBy(1L);

        roleEntityMapper.insertSelective(roleEntity);
    }

    @Override
    public List<Role> findRoles() {
        return EdsUtils.convertToRoles(
                roleEntityMapper.selectAll()
        );
    }

    @Override
    public List<Role> findRolesWithPermission() {

        List<RoleEntity> roleEntities = roleEntityMapper.selectAll();
        if(null == roleEntities || roleEntities.isEmpty()){
            return Collections.emptyList();
        }

        Date date = new Date();
        List<Role> roles = new ArrayList<>();
        for(RoleEntity roleEntity : roleEntities){
            Role role = new Role(roleEntity.getRoleName());
            role.setRoleId(roleEntity.getId());
            role.setDescription(roleEntity.getDescription());
            role.setCreateTime(DateUtils.formatDateTime(date));
            role.setPermissions(findPermissionsByRoleId(roleEntity.getId()));

            roles.add(role);
        }

        return roles;
    }

    @Override
    public void delRoleById(Long roleId) {
        roleEntityMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public void changeRolePermission(Long roleId, List<Long> permissionIds) {
        if(null == permissionIds || permissionIds.isEmpty()){
            return;
        }

        rolePermissionEntityMapper.deleteByRoleId(roleId);

        List<RolePermissionEntity> rolePermissionEntities = EdsUtils.convertToRolePermissionEntities(roleId, permissionIds);

        for(RolePermissionEntity rolePermissionEntity : rolePermissionEntities) {
            rolePermissionEntityMapper.insertSelective(rolePermissionEntity);
        }
    }

    @Override
    public void dismissRoleByUserIdAndRoleId(Long userId, Long roleId) {
        accountRoleEntityMapper.deleteByUserIdAndRoleId(userId, roleId);
    }

    @Override
    public void assignRole(Long userId, Long roleId) {

        AccountRoleEntity accountRoleEntity = new AccountRoleEntity();
        accountRoleEntity.setUserId(userId);
        accountRoleEntity.setRoleId(roleId);
        accountRoleEntity.setCreateTime(new Date());
        accountRoleEntity.setCreateBy(1L);

        accountRoleEntityMapper.insertSelective(accountRoleEntity);
    }

    @Override
    public List<Permission> findPermissions() {

        List<PermissionEntity> permissionEntities = permissionEntityMapper.selectAll();

        return EdsUtils.convertToPermissions(permissionEntities);
    }
}
