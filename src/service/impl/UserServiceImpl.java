package service.impl;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import service.UserService;
import utils.MD5Utils;
import dao.UserDao;
import domain.User;

public class UserServiceImpl implements UserService{

	private UserDao ud;
	public void setUd(UserDao ud) {
		this.ud = ud;
	}
	@Override
	public User getUserByCodePassword(User u) {
		User existU = ud.getByUserCode(u.getUser_code());
		if(existU==null){
			throw new RuntimeException("没有找到用户");
		}if(!existU.getUser_password().equals(MD5Utils.md5(u.getUser_password()))){
			throw new RuntimeException("密码错误");
		}
		return existU;
	}
	public void saveUser(User u){
		//调用dao根据注册的登录名获得对象
		User existU=ud.getByUserCode(u.getUser_code());
		//如果有说明对象存在，抛出异常
		if(existU!=null){
			try {
				throw new RuntimeException("用户已存在");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//使用MD5加密
		u.setUser_password(MD5Utils.md5(u.getUser_password()));
		//没有则调用dao保存对象
		ud.save(u);
	}

}
