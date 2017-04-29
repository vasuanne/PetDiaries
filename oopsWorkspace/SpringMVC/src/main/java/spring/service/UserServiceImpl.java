package spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.UserDAO;
import spring.model.ResetToken;
import spring.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public boolean addUser(User p) {
		boolean error=false;
		error=this.userDAO.addUser(p);
		return error;
	}

	@Override
	@Transactional
	public void updateUser(User p) {
		this.userDAO.updateUser(p);
	}

	@Override
	@Transactional
	public List<User> listUsers() {
		return this.userDAO.listUsers();
	}

	@Override
	@Transactional
	public boolean validateUser(User p)
	{
		return this.userDAO.validateUser(p);
	}
	@Override
	@Transactional
	public User getUserById(int id) {
		return this.userDAO.getUserById(id);
	}

	@Override
	@Transactional
	public void removeUser(int id) {
		this.userDAO.removeUser(id);
	}
	
	@Override
	@Transactional
	public int getPetCount(int id)
	{
		return this.userDAO.getPetCount(id);
	}
	
	@Override
	@Transactional
	public boolean isContactInfoSet(int id)
	{
		return this.userDAO.isContactInfoSet(id);
	}
	
	@Override
	@Transactional
	public void logInvalidAttempt(User p)
	{
		this.userDAO.logInvalidAttempt(p);
	}
	
	@Override
	@Transactional
	public void createInitialTokenEntry(ResetToken resetToken)
	{
		this.userDAO.createInitialTokenEntry(resetToken);
	}
	
	@Override
	@Transactional
	public int getUserId(String username)
	{
		return this.userDAO.getUserId(username);
		
	}
	
	
	@Override
	@Transactional
	public int resetTokenId(String username)
	{
		return this.userDAO.resetTokenId(username);
		
	}
	
	@Override
	@Transactional
	public Date getExpiryDate(String username)
	{
		return this.userDAO.getExpiryDate(username);
		
	}
	
	@Override
	@Transactional
	public String getResetToken(String username)

	{
		return this.userDAO.getResetToken(username);
		
	}
}