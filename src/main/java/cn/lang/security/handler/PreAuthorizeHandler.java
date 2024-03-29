package cn.lang.security.handler;

import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * ClassName : PreAuthorizeHandler
 * description : 鉴权处理器 需要使用者自己实现对应的方法
 * @author : Lang
 * date: 2022-06-17
 */
public interface PreAuthorizeHandler {

    /**
     * 所有权限标识
     */
    String ALL_PERMISSION = "*:*:*";

    /**
     * 管理员角色权限标识
     */
    String SUPER_ADMIN = "admin";

    /**
     * 数组为0时
     */
    Integer ARRAY_EMPTY = 0;

    /**
     * 验证用户是否具备某权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    default Boolean hasPermission(String permission) {
         return Boolean.TRUE;
    }

    /**
     * 验证用户是否不具备某权限，与 lackPermission逻辑相反
     *
     * @param permission 权限字符串
     * @return 用户是否不具备某权限
     */
    default Boolean lacksPermission(String permission) {
        return !hasPermission(permission);
    }

    /**
     * 验证用户是否具有以下任意一个权限
     *
     * @param permissions 权限列表
     * @return 用户是否具有以下任意一个权限
     */
    default Boolean hasAnyPermission(String[] permissions) {
        return Boolean.TRUE;
    }

    /**
     * 判断用户是否拥有某个角色
     *
     * @param role 角色字符串
     * @return 用户是否具备某角色
     */
    default Boolean hasRole(String role) {
        return Boolean.TRUE;
    }

    /**
     * 验证用户是否不具备某角色，与 isRole逻辑相反。
     *
     * @param role 角色名称
     * @return 用户是否不具备某角色
     */
    default Boolean lacksRole(String role) {
        return !hasRole(role);
    }

    /**
     * 验证用户是否具有以下任意一个角色
     *
     * @param roles 角色列表
     * @return 用户是否具有以下任意一个角色
     */
    default Boolean hasAnyRoles(String[] roles) {
        return Boolean.TRUE;
    }

    /**
     * 判断是否包含权限
     *
     * @param authorities 权限列表
     * @param permission  权限字符串
     * @return 用户是否具备某权限
     */
    default Boolean hasPermissions(Collection<String> authorities, String permission) {
        return authorities.stream().filter(StringUtils::hasText)
                .anyMatch(x -> ALL_PERMISSION.contains(x) || PatternMatchUtils.simpleMatch(permission, x));
    }
}
