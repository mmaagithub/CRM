package service;

import domain.User;

public interface UserService {

	User getUserByCodePassword(User u);
	//保存
	void saveUser(User u);
	
}
