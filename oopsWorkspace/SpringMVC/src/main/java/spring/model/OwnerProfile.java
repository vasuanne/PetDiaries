package spring.model;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;
import spring.model.Profile;

@Entity
@Table(name="CARETAKER_PROFILE")
public class OwnerProfile extends Profile{
	
	void openChatBox()
	{
		//NULL
	}
	void sendMessage()
	{
		//NULL
	}
	void displayError()
	{
		
	}
	void displaySuccess()
	{
		
	}
	
}
