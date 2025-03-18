package com.suyh.base.service;

import com.suyh.base.mp.mapper.SysUserRoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author suyh
 * @since 2025-03-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysUserRoleService {
    private final SysUserRoleMapper sysUserRoleMapper;
}
