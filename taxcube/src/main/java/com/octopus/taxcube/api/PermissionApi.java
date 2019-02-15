package com.octopus.taxcube.api;

import com.octopus.taxcube.common.ServerResponse;
import com.octopus.taxcube.common.constenum.CONST;
import com.octopus.taxcube.eds.pojo.Permission;
import com.octopus.taxcube.eds.pojo.Role;
import com.octopus.taxcube.eds.service.IPermissionService;
import com.octopus.taxcube.util.EdsUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@Api(value = "权限管理",tags = "权限管理接口")
public class PermissionApi {

    @Autowired
    private IPermissionService permissionService;

    @RequiresRoles(value = CONST.ACCOUNT_TYPE_SYSADMIN)
    @RequestMapping(value = "/permission/roles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("获取角色列表")
    @ResponseBody
    public ServerResponse<List<Role>> listPageOfRoles(){

        List<Role> roles = permissionService.findRolesWithPermission();

        return ServerResponse.createBySuccess(roles);
    }

    @RequiresRoles(value = CONST.ACCOUNT_TYPE_SYSADMIN)
    @RequestMapping(value = "/permission/permissions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("获取权限列表")
    @ResponseBody
    public ServerResponse<List<Permission>> listPageOfPermissions(){

        List<Permission> permissions = permissionService.findPermissions();

        return ServerResponse.createBySuccess(permissions);
    }


    @RequiresRoles(value = CONST.ACCOUNT_TYPE_SYSADMIN)
    @RequestMapping(value = "/permission/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.POST})
    @ApiOperation("创建角色")
    @ResponseBody
    public ServerResponse createRole(
            @ApiParam(name = "name", value = "角色名称", required = true) @RequestParam String name,
            @ApiParam(name = "description", value = "角色描述", required = true) @RequestParam String description
    ){

        permissionService.createRoleByNameAndDescription(name, description);

        return ServerResponse.createBySuccess();
    }

    @RequiresRoles(value = CONST.ACCOUNT_TYPE_SYSADMIN)
    @RequestMapping(value = "/permission/role/{roleId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.DELETE})
    @ApiOperation("删除角色")
    @ResponseBody
    public ServerResponse delRole(
            @ApiParam(name = "roleId", value = "角色ID", required = true) @PathVariable("roleId") Long roleId
    ){

        permissionService.delRoleById(roleId);
        return ServerResponse.createBySuccess();
    }

    @RequiresRoles(value = CONST.ACCOUNT_TYPE_SYSADMIN)
    @RequestMapping(value = "/permission/role/{roleId}/assign/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.POST})
    @ApiOperation("分配角色给用户")
    @ResponseBody
    public ServerResponse assignRole(
            @ApiParam(name = "roleId", value = "角色ID", required = true) @PathVariable("roleId") Long roleId,
            @ApiParam(name = "userId", value = "用户ID", required = true) @PathVariable("userId") Long userId
    ){
        permissionService.assignRole(userId, roleId);
        return ServerResponse.createBySuccess();
    }

    @RequiresRoles(value = CONST.ACCOUNT_TYPE_SYSADMIN)
    @RequestMapping(value = "/permission/role/{roleId}/assign/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.DELETE})
    @ApiOperation("删除用户关联的角色")
    @ResponseBody
    public ServerResponse dismissRole(
            @ApiParam(name = "roleId", value = "角色ID", required = true) @PathVariable("roleId") Long roleId,
            @ApiParam(name = "userId", value = "用户ID", required = true) @PathVariable("userId") Long userId
    ){
        permissionService.dismissRoleByUserIdAndRoleId(userId, roleId);
        return ServerResponse.createBySuccess();
    }


    @RequiresRoles(value = CONST.ACCOUNT_TYPE_SYSADMIN)
    @RequestMapping(value = "/permission/role/{roleId}/permissions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("获取角色权限列表")
    @ResponseBody
    public ServerResponse<List<Permission>> getRolePermission(
            @ApiParam(name = "roleId", value = "角色ID", required = true) @PathVariable("roleId") Long roleId
    ){
        return ServerResponse.createBySuccess(
                permissionService.findPermissionsByRoleId(roleId)
        );
    }


    @RequiresRoles(value = CONST.ACCOUNT_TYPE_SYSADMIN)
    @RequestMapping(value = "/permission/role/{roleId}/permissions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.PUT})
    @ApiOperation("更改角色权限")
    @ResponseBody
    public ServerResponse changeRolePermission(
            @ApiParam(name = "roleId", value = "角色ID", required = true) @PathVariable("roleId") Long roleId,
            @ApiParam(name = "permissions", value = "权限ID串，逗号隔开", required = true) @PathVariable("permissions") String permissions
    ){

        String[] permissionIdArray = permissions.trim().split(",");
        List<Long> permissionIds = EdsUtils.convertStringToLong(permissionIdArray);

        permissionService.changeRolePermission(roleId, permissionIds);

        return ServerResponse.createBySuccess();
    }
}
