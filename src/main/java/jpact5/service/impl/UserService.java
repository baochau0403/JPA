package jpact5.service.impl;

import java.util.Random;

import jpact5.dao.IUserDao;
import jpact5.dao.impl.UserDaoImpl;
import jpact5.entity.User;
import jpact5.service.IUserService;



public class UserService implements IUserService{
	
	IUserDao userDao = new UserDaoImpl();

	@Override
	public User login(String username, String password) {
		User user = this.get(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public boolean register(String username, String email, String password, String fullname, String phone ) {

		if (userDao.checkExistUsername(username)) {
			return false;
			}
		long millis = System.currentTimeMillis();
		java.sql.Date date=new java.sql.Date(millis);
		//userDao.insert(user);
		return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public User get(String username) {
		return userDao.findByUserName(username);
	}

	public String RandomPassword() {
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    return generatedString;
	}

	@Override
	public void updatePassword(String password, String email) {
		// TODO Auto-generated method stub
		userDao.updatePassword(email, password);
	}

	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(User user) {
		// TODO Auto-generated method stub
		
	}

}
