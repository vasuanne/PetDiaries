package spring.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.model.ContactInfo;
import spring.model.Pet;
import spring.model.ResetToken;
@Repository
public class ContactInfoDAOImpl implements ContactInfoDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ContactInfoDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public boolean addContactInfo(ContactInfo p) {
		boolean error=false;
		Session session = this.sessionFactory.getCurrentSession();
		String hql="select id from ContactInfo where userId="+p.getUserId();
		try
		{
			session.save(p);
			//tx.commit();
			int id =  ((Integer) session.createQuery(hql).uniqueResult()).intValue();
			p.setId(id);
			System.out.println("ContactInfo DAO Impl Added contactInfo without exceptions");
			
		}
		catch(Exception e)
		{
			session.clear();
			System.out.println(e.getCause());
			error=true;
		}
		
		return error;
		
	//	logger.info("ContactInfo saved successfully, ContactInfo Details="+p);
	}

	@Override
	public void updateContactInfo(ContactInfo p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("ContactInfo updated successfully, ContactInfo Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContactInfo> listContactInfos(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<ContactInfo> contactInfosList = session.createQuery("from ContactInfo where userId="+String.valueOf(userId)).list();
		for(ContactInfo p : contactInfosList){
			logger.info("ContactInfo List::"+p);
		}
		return contactInfosList;
	}

	@Override
	public ContactInfo getContactInfoById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		ContactInfo p = (ContactInfo) session.load(ContactInfo.class, new Integer(id));
		logger.info("ContactInfo loaded successfully, ContactInfo details="+p);
		return p;
	}

	@Override
	public void removeContactInfo(int id) {

		Session session = this.sessionFactory.getCurrentSession();
		ContactInfo p = (ContactInfo) session.load(ContactInfo.class, new Integer(id));
		if(null != p){
			System.out.println("DELETE DAO- ContactInfo deleted");
			session.delete(p);
	
		}
		logger.info("ContactInfo deleted successfully, contactInfo details="+p);
	}

	
	 
	@Override
	public boolean isRecoveryEmailAddrAvailable(ContactInfo c)
	{
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "select count(emailAddr) from ContactInfo where emailAddr='"+c.getEmailAddr()+"'";
		String hql1 = "select userId from ContactInfo where emailAddr='"+c.getEmailAddr()+"'";
		int count = ((Long) session.createQuery(hql).uniqueResult()).intValue();
		
		if(count>0)
		{
			int userId=((Integer) session.createQuery(hql1).uniqueResult()).intValue();
			c.setUserId(userId);
			return true;
		}
		else
			return false;
			
	}
	
	@Override
	public String getUsernameWithUserId(int id)
	{
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "select username from User where userId="+String.valueOf(id);
	
		String username = ((String) session.createQuery(hql).uniqueResult());
		
		return username;
			
	}
	
	@Override
	public void updateResetToken(ResetToken r) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(r);
	}
	
	
	@Override
	public int resetTokenId(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "select id from ResetToken where userId="+String.valueOf(userId);
		
		int id = ((Integer) session.createQuery(hql).uniqueResult());
		
		return id;

	}

	
}