package jpact5.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jpact5.configs.JPAConfig;
import jpact5.dao.IUserDao;
import jpact5.entity.User;

public class UserDaoImpl implements IUserDao {

	@Override
	public List<User> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}

	@Override
	public User findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		User user = enma.find(User.class, id);
		return user;
	}

	@Override
	public void insert(User user) {

		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(user);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	public static void main(String[] args) {
		try {
			UserDaoImpl userDao = new UserDaoImpl();
			System.out.println(userDao.findById(1));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public User findByUserName(String username) {
		EntityManager enma = JPAConfig.getEntityManager();
		User user = enma.find(User.class, username);
		return user;
	}

	@Override
	public boolean checkExistEmail(String email) {
		User user = null;
		user = this.findByEmail(email);
		if (user == null)
			return false;
		return true;

	}

	@Override
	public boolean checkExistUsername(String username) {
		User user = null;
		user = this.findByUserName(username);
		if (user == null)
			return false;
		return true;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findByEmail(String email) {
		EntityManager enma = JPAConfig.getEntityManager();
		User user = enma.find(User.class, email);
		return user;
	}

	@Override
	public void updatePassword(String email, String password) {
	}

	@Override
	public void update(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(user);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

}
