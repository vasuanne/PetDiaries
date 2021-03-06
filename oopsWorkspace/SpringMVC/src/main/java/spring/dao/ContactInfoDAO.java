package spring.dao;
import java.util.List;

import spring.model.ContactInfo;
import spring.model.ResetToken;

public interface ContactInfoDAO {
	
	public boolean addContactInfo(ContactInfo p);
	public void updateContactInfo(ContactInfo p);
	public List<ContactInfo> listContactInfos(int userId);
	public ContactInfo getContactInfoById(int id);
	public void removeContactInfo(int id);
	public boolean isRecoveryEmailAddrAvailable(ContactInfo c);
	public String getUsernameWithUserId(int id);
	public void updateResetToken(ResetToken r);
	public int resetTokenId(int userId);
	public int getIdFromUserId(int userId);
}