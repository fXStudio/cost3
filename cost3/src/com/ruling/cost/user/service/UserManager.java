package com.ruling.cost.user.service;

import java.util.List;

import com.common.been.Company;
import com.common.been.ParameterMap;
import com.ruling.cost.user.domain.Post;
import com.ruling.cost.user.domain.User;
import com.ruling.cost.utils.PageModel;

public interface UserManager {

	public void addUser(User u);

	public void delUser(List usersid);

	public void updateUser(User u);

	public User findUserById(int uid);

	public PageModel findAllUser(User user);

	public PageModel findUserByItemId(int itemid);

	public User login(String username, String password) throws Exception;

	public void updatePwd(User u) throws Exception;

	public PageModel findUserForItem(int itemid);

	public List<Post> findallpost();

	public List<User> finduserbyPost(int postid);

	public List<Company> zzjg(ParameterMap pm);
	
	public PageModel findAllUnregisterUser(User user);
}
