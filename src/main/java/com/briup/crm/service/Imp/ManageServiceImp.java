package com.briup.crm.service.Imp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.SysRole;
import com.briup.crm.bean.SysRoleExample;
import com.briup.crm.bean.SysUser;
import com.briup.crm.bean.SysUserExample;
import com.briup.crm.dao.SysRoleMapper;
import com.briup.crm.dao.SysUserMapper;
import com.briup.crm.service.ManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ManageServiceImp implements ManageService {

	@Autowired
	private SysRoleMapper roleMapper;
	
	@Override
	public PageInfo<SysRole> findAllRole(int curPage, int size) {
		PageHelper.startPage(curPage, size);
		SysRoleExample example = new SysRoleExample();
		List<SysRole> rolelist = roleMapper.selectByExample(example);
		PageInfo<SysRole> roleInfo = new PageInfo<SysRole>(rolelist);
		return roleInfo;
	}

	@Override
	public void deleteRoleById(long roleId) {
		roleMapper.deleteByPrimaryKey(roleId);
	}

	@Override
	public void saveOrUpdateRole(SysRole role) {
		if(role.getRoleId()==null)
			roleMapper.insertSelective(role);
		else
			roleMapper.updateByPrimaryKey(role);
	}

	@Override
	public SysRole findRoleById(long roleId) {
		SysRole role = roleMapper.selectByPrimaryKey(roleId);
		return role;
	}
	
	/*  ----------------------------  */
	
	@Autowired
	private SysUserMapper userMapper;

	@Override
	public PageInfo<SysUser> findAllUser(int curPage, int size) {
		PageHelper.startPage(curPage, size);
		SysUserExample example = new SysUserExample();
		List<SysUser> userlist = userMapper.selectByExample(example);
		PageInfo<SysUser> userInfo = new PageInfo<SysUser>(userlist);
		return userInfo;
	}

	@Override
	public void deleteUserById(long usrId) {
		userMapper.deleteByPrimaryKey(usrId);
	}
	
	@Override
	public void saveOrUpdateUser(SysUser user) {
		SysRole role = findRoleById(user.getUsrRoleId());
		user.setUsrRoleName(role.getRoleName());
		if (user.getUsrId() == null) {
			userMapper.insertSelective(user);
		} else {
			userMapper.updateByPrimaryKeySelective(user);
		}
	}

	@Override
	public SysUser findUserById(long usrId) {
		SysUser user = userMapper.selectByPrimaryKey(usrId);
		return user;
	}

	@Override
	public PageInfo<SysUser> findUserByRoleId(int curPage, int size, long roleId) {
		PageHelper.startPage(curPage, size);
		SysUserExample example = new SysUserExample();
		example.createCriteria().andUsrRoleIdEqualTo(roleId);
		List<SysUser> userlist = userMapper.selectByExample(example);
		PageInfo<SysUser> userInfo = new PageInfo<SysUser>(userlist);
		return userInfo;
	}
}
