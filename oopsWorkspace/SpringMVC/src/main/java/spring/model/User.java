package spring.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import java.util.Date;


@Entity
@Table(name="DB1.USER")
public class User {

	@Id
	@Column(name="userId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@Column(unique = true)
	private String username;
	
	private String password;

	private String firstName;
	
	private String lastName;
	
	private String userType;

	private Date loginTime;
	
	private int invalidLoginAttempts;
	
	private String error;
	
	private boolean accountLocked; 
	
	private boolean expiryEmailSent;
	
	private Date expiresOnDate;
	
	private Date expiryEmailSentDate;
	
	public void setUserId(int userId)
	{
		this.userId=userId;
	}
	
	public int getUserId()
	{
		return this.userId;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString(){
		return "id="+userId+", username="+username+", password="+password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}


	public int getInvalidLoginAttempts() {
		return invalidLoginAttempts;
	}

	public void setInvalidLoginAttempts(int invalidLoginAttempts) {
		this.invalidLoginAttempts = invalidLoginAttempts;
	}
	
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	
	public Date getExpiresOnDate() {
		return expiresOnDate;
	}

	public void setExpiresOnDate(Date expiresOnDate) {
		this.expiresOnDate = expiresOnDate;
	}

	public Date getExpiryEmailSentDate() {
		return expiryEmailSentDate;
	}

	public void setExpiryEmailSentDate(Date expiryEmailSentDate) {
		this.expiryEmailSentDate = expiryEmailSentDate;
	}

	public boolean isExpiryEmailSent() {
		return expiryEmailSent;
	}

	public void setExpiryEmailSent(boolean expiryEmailSent) {
		this.expiryEmailSent = expiryEmailSent;
	}
	

	
}