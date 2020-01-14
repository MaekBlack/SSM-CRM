package com.briup.crm.service;


import com.briup.crm.bean.SysRole;
import com.briup.crm.bean.SysUser;
import com.github.pagehelper.PageInfo;

public interface ManageService {
	public PageInfo<SysRole> findAllRole(int curPage, int size);
	public void deleteRoleById(long roleId);
	public void saveOrUpdateRole(SysRole role);
	public SysRole findRoleById(long roleId);
	
	public PageInfo<SysUser> findAllUser(int curPage, int size);
	public void deleteUserById(long usrId);
	public void saveOrUpdateUser(SysUser user);
	public SysUser findUserById(long usrId);
	public PageInfo<SysUser> findUserByRoleId(int curPage, int size, long roleId);
}
