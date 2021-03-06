package spring.service;

import java.util.List;

import spring.model.AdminProfile;

public interface AdminProfileService
{
	public boolean addAdminProfile(AdminProfile p);
	public boolean validateAdminProfile(AdminProfile p);
	public void updateAdminProfile(AdminProfile p);
	public List<AdminProfile> listAdminProfiles();
	public AdminProfile getAdminProfileById(int id);
	public void removeAdminProfile(int id);
	public int getAdminProfileCount(int id);
	public boolean isContactInfoSet(int id);
	public List<String> getExpiredEmails();
	public void deleteExpiredProfiles();
}