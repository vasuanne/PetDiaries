package spring.model;

import javax.persistence.MappedSuperclass;

import spring.model.User;

@MappedSuperclass
public abstract class Profile extends User{
	void openChatBox()
	{
		//NULL
	}
	void sendMessage()
	{
		//NULL
	}
	abstract void displayError();
	abstract void displaySuccess();
	
}