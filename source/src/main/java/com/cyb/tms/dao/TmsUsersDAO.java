package com.cyb.tms.dao;

import java.util.List;

import com.cyb.tms.dto.TmsUsersDTO;
import com.cyb.tms.entity.TmsUsers;

public interface TmsUsersDAO {
	
	public TmsUsers findByUsername(String username);
	public long createUser(TmsUsersDTO tmsUserDTO);
    public void updateUser(TmsUsersDTO tmsUserDTO);
    public void deleteUser(long id);
    public List<TmsUsers> getAllUsers();
    public TmsUsers getUser(long id);	
    public List<TmsUsers> getUsersByStatus(long projectId);
    public void updatePassword(TmsUsersDTO tmsUserDTO);
	
}
