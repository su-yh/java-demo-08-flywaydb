package com.suyh.base.mp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author suyh
 * @since 2025-03-18
 */
@Data
@TableName(value = "sys_user_role", autoResultMap = true)
public class SysUserRoleEntity {
    private Long userId;
    private Long roleId;
}
