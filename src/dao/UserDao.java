package dao;

import domain.User;

public interface UserDao extends BaseDao<User>{

	User getByUserCode(String userCode);

}
