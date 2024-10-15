package jpact5.service;

import jpact5.entity.User;

public interface IUserService {

	User login(String username, String password);
	
	User get(String username);
	
	User findByUserName (String username);	
		
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
	boolean register(String email, String password, String username, String fullname, String phone);
	
	void insert(User user);
	
	void updatePassword(String password, String email);
	
}
