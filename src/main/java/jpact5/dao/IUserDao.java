package jpact5.dao;

import java.util.List;

import jpact5.entity.User;


public interface IUserDao {
	
	List<User> findAll();
	
	User findById(int id);
	
	void insert(User user);

	User findByUserName(String username);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
	boolean checkExistPhone(String phone);
	
	User findByEmail(String email);
	
	void updatePassword(String email, String password);
	
	void update(User user);
}
