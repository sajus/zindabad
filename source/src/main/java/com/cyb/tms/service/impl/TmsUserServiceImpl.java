package com.cyb.tms.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.tms.dao.TmsUsersDAO;
import com.cyb.tms.dao.impl.TmsProjectDAOImpl;
import com.cyb.tms.dto.TmsProjectDTO;
import com.cyb.tms.dto.TmsUsersDTO;
import com.cyb.tms.entity.TmsUsers;
import com.cyb.tms.service.TmsUserService;

@Service
@Transactional
public class TmsUserServiceImpl implements TmsUserService {
	
	@Autowired
	TmsUsersDAO tmsUsersDao;

	@Override
	public long createUser(TmsUsers tmsUser) {
		return (Long) tmsUsersDao.createUser(tmsUser);
	}

	@Override
	public TmsUsers updateUser(TmsUsers tmsUser) {
		return tmsUsersDao.updateUser(tmsUser);
	}

	@Override
	public void deleteUser(long id) {
		tmsUsersDao.deleteUser(id);
	}

	@Override
	public List<TmsUsers> getAllUsers() {
		return tmsUsersDao.getAllUsers();
	}

	@Override
	public TmsUsers getUser(long id) {
		return tmsUsersDao.getUser(id);
	}

	@Override
	public boolean isUserExist(TmsUsers tmsUser) {
		return tmsUsersDao.findByUsername(tmsUser.getUserName())!=null;
	}

	@Override
	public TmsUsersDTO findByName(String userName) {
		TmsUsersDTO userDto = new TmsUsersDTO();
		TmsUsers users = tmsUsersDao.findByUsername(userName);
		setDoToDto(userDto, users);
		return userDto;
	}

	private void setDoToDto(TmsUsersDTO userDto, TmsUsers users) {
		userDto.setId(users.getId());
		userDto.setUserName(users.getUserName());
		userDto.setEmail(users.getEmail());
		userDto.setUserRole(users.getUserRole());
		userDto.setProjectId(users.getTmsProject().getPid());
		userDto.setProjectName(users.getTmsProject().getName()); 
//		TmsProjectDTO tmsProjectDTO = new TmsProjectDTO();
//		BeanUtils.copyProperties(users.getTmsProject(), tmsProjectDTO);
//		userDto.setTmsProjectDTO(tmsProjectDTO);
	}
	

	@Override
	public List<TmsUsers> getUsersByStatus(long projectId) throws Exception {
		// TODO Auto-generated method stub
        return tmsUsersDao.getUsersByStatus(projectId);
	}
	
	

}
