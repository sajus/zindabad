package com.cyb.tms.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsUsersDAO;
import com.cyb.tms.dto.TmsProjectDTO;
import com.cyb.tms.dto.TmsUsersDTO;
import com.cyb.tms.entity.TmsCodeReview;
import com.cyb.tms.entity.TmsProject;
import com.cyb.tms.entity.TmsUsers;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsUsersDAOImpl implements TmsUsersDAO {

private static final String USER_NAME = "userName";
	
	@Autowired
    private HibernateUtil hibernateUtil;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public TmsUsers findByUsername(String username) {
		return hibernateUtil.findByUsername(USER_NAME, username, TmsUsers.class);
	}

	@Override
	public long createUser(TmsUsersDTO tmsUserDTO) {
		TmsProject project = hibernateUtil.fetchById(tmsUserDTO.getProjectId(), TmsProject.class);
		TmsUsers user= new TmsUsers();
		BeanUtils.copyProperties(tmsUserDTO, user);
		user.setTmsProject(project);
		user.setPassword(passwordEncoder.encode(tmsUserDTO.getPassword()));
		return (Long) hibernateUtil.create(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateUser(TmsUsersDTO tmsUserDTO) {
		TmsUsers user = hibernateUtil.fetchById(tmsUserDTO.getId(), TmsUsers.class);
		user.setUserRole(tmsUserDTO.getUserRole());
		user.setIsActive(tmsUserDTO.getIsActive());
		hibernateUtil.update(user); 
		
	}

	@Override
	public void deleteUser(long id) {
		TmsUsers user = new TmsUsers();
		user.setId(id);
        hibernateUtil.delete(user);
	}

	@Override
	public List<TmsUsers> getAllUsers() {
		return hibernateUtil.fetchAll(TmsUsers.class);
	}

	@Override
	public TmsUsers getUser(long id) {
		return hibernateUtil.fetchById(id, TmsUsers.class);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<TmsUsers> getUsersByStatus(long projectId) {
		Object[] roles = {"MANAGER", "ADMIN"};
		List<TmsUsers> users = hibernateUtil.getCurrentSession().createCriteria(TmsUsers.class, "tu")
				              .createAlias("tmsProject", "proj")
			                  .add(Restrictions.eq("proj.pid", projectId))
			                  .add(Restrictions.not(Restrictions.in("userRole", roles)))
			                  .add(Restrictions.eq("tu.isActive", "ACTIVE")).list();
		return users;
	}

	@Override
	public void updatePassword(TmsUsersDTO tmsUserDTO) {
		TmsUsers user = hibernateUtil.fetchById(tmsUserDTO.getId(), TmsUsers.class);
		user.setPassword(passwordEncoder.encode(tmsUserDTO.getPassword()));
		hibernateUtil.update(user);
		
	}


}
