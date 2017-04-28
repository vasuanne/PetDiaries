package spring.dao;
import java.util.List;

import spring.model.User;

public interface UserDAO {
	
	public boolean addUser(User p);
	public void updateUser(User p);
	public List<User> listUsers();
	public User getUserById(int id);
	public void removeUser(int id);
	public boolean validateUser(User p);
	public int getPetCount(int id);
	public boolean isContactInfoSet(int id);
	public void logInvalidAttempt(User p);
}