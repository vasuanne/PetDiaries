package spring.service;

import java.util.List;

import spring.model.ContactInfo;
import spring.model.ResetToken;
import spring.model.ResetToken;
public interface ContactInfoService
{
	public boolean addContactInfo(ContactInfo p);
	public void updateContactInfo(ContactInfo p);
	public List<ContactInfo> listContactInfos(int userId);
	public ContactInfo getContactInfoById(int id);
	public void removeContactInfo(int id);
	public boolean isRecoveryEmailAddrAvailable(ContactInfo c);
	public String getUsernameWithUserId(int id);
	public void updateResetToken(ResetToken r);
	public int resetTokenId(int userId);
}