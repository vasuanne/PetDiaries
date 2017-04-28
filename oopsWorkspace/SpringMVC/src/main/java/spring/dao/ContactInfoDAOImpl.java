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
		
		try
		{
			session.save(p);
			//tx.commit();
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
	public List<ContactInfo> listContactInfos() {
		Session session = this.sessionFactory.getCurrentSession();
		List<ContactInfo> contactInfosList = session.createQuery("from ContactInfo").list();
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
	public int getContactInfoCount(int id)
	{
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "select count(*) from ContactInfo where contactInfoId="+String.valueOf(id);
		int count = ((Long) session.createQuery(hql).uniqueResult()).intValue();
		return count;
	}
	
	@Override
	public boolean isContactInfoSet(int id)
	{
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "select count(contactInfoId) from ContactInfo where contactInfoId="+String.valueOf(id);
		int count = ((Long) session.createQuery(hql).uniqueResult()).intValue();

		if(count>0)
			return true;
		else
			return false;
			
	}
	
	
	
}