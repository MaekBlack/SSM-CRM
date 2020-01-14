package com.briup.crm.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.SysUser;
import com.briup.crm.bean.SysUserExample;
import com.briup.crm.dao.SysUserMapper;
import com.briup.crm.service.UserService;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private SysUserMapper userMapper;
	
	@Override
	public SysUser login(String name, String password) throws Exception {
		// TODO Auto-generated method stub
		SysUserExample example = new SysUserExample();
		example.createCriteria().andUsrNameEqualTo(name);
		List<SysUser> userlist = userMapper.selectByExample(example);
		if(userlist.size()>0) {
			SysUser user = userlist.get(0);
			//判断密码是否正确
			if(user.getUsrPassword().equals(password)) {
				return user;
			}else {
				throw new Exception("密码输入错误，请重新输入");
			}
		}else {
			throw new Exception("用户名输入错误，请重新输入");
		}
	}

}
