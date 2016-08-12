package com.cyb.tms.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.tms.entity.TmsUsers;

@Repository
public class HibernateUtil {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
		
    public <T> Serializable create(final T entity) {
        return sessionFactory.getCurrentSession().save(entity);        
    }
    
    public <T> void createOrUpdate(final T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);        
    }
    
    public <T> T update(final T entity) {
        sessionFactory.getCurrentSession().update(entity);   
        return entity;
    }
    
	public <T> void delete(final T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	public <T> void delete(Serializable id, Class<T> entityClass) {
		T entity = fetchById(id, entityClass);
		delete(entity);
	}
    
    @SuppressWarnings("unchecked")	
    public <T> List<T> fetchAll(Class<T> entityClass) {        
        return sessionFactory.getCurrentSession().createQuery(" FROM "+entityClass.getName()).list();        
    }
  
    @SuppressWarnings("rawtypes")
	public <T> List fetchAll(String query) {        
        return sessionFactory.getCurrentSession().createSQLQuery(query).list();        
    }
    
    @SuppressWarnings("unchecked")
	public <T> T fetchById(Serializable id, Class<T> entityClass) {
        return (T)sessionFactory.getCurrentSession().get(entityClass, id);
    }
    
    @SuppressWarnings("unchecked")
	public <T> T findByUsername(String propertyName, Serializable name, Class<T> entityClass) {
    	
    	List<?> list = null;
    	list = sessionFactory.getCurrentSession().createCriteria(entityClass)
    						.add(Restrictions.eq(propertyName, name)).list();
    	return (T) ((list != null && list.size() > 0) ? list.get(0) : null);
    }
    
    @SuppressWarnings("unchecked")
   	public <T> T findByJiraId(String propertyName, Serializable name, Class<T> entityClass) {
       	
       	List<?> list = null;
       	list = sessionFactory.getCurrentSession().createCriteria(entityClass)
       						.add(Restrictions.eq(propertyName, name)).list();
       	return (T) ((list != null && list.size() > 0) ? list.get(0) : null);
    }

	@SuppressWarnings("unchecked")
	public <T> T findByPropertyName(String propertyName, String value,
			Class<T> entityClass) {
		List<?> list = null;
    	list = sessionFactory.getCurrentSession().createCriteria(entityClass)
    						.add(Restrictions.eq(propertyName, value)).list();
    	return (T) ((list != null && list.size() > 0) ? list.get(0) : null);
	}

	
	@SuppressWarnings("unchecked")
	public <T> T findByProperty(Session session, String propertyName, String value,
			Class<T> entityClass) {
		List<?> list = null;
    	list = session.createCriteria(entityClass)
    						.add(Restrictions.eq(propertyName, value)).list();
    	return (T) ((list != null && list.size() > 0) ? list.get(0) : null);
	}
	
    
    
	
}
