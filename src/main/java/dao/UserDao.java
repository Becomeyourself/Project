package dao;

import java.util.List;

import entity.User;

public interface UserDao {
	void add(User u);
	void remove(String uname);
	List<User>  getAll(String sql);
	User getByName(String uname);
}
