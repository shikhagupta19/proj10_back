package com.rays.p10.services;

import org.springframework.stereotype.Service;

import com.rays.p10.common.BaseServiceImpl;
import com.rays.p10.dao.RoleDaoInt;
import com.rays.p10.dto.RoleDTO;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDTO, RoleDaoInt> implements RoleServiceInt {

}
