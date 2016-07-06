package com.cyb.tms.service;

import java.util.List;

import com.cyb.tms.dto.TmsUsersDTO;
import com.cyb.tms.entity.TmsUsers;

public interface TmsUserService {
	
	public long createUser(TmsUsersDTO tmsUserDTO);
	public void updateUser(TmsUsersDTO tmsUserDTO);
    public void deleteUser(long id);
    public List<TmsUsers> getAllUsers();
    public TmsUsers getUser(long id);
	public boolean isUserExist(String userName);
	public TmsUsersDTO findByName(String userName);
	public List<TmsUsers> getUsersByStatus(long projectId);
	public void updatePassword(TmsUsersDTO tmsUserDTO);
}
