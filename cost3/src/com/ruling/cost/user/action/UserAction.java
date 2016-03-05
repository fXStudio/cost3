package com.ruling.cost.user.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.common.been.Company;
import com.common.been.ResultHashMap;
import com.opensymphony.xwork2.ActionContext;
import com.ruling.cost.item.domain.ItemUser;
import com.ruling.cost.item.service.ItemManager;
import com.ruling.cost.user.domain.Post;
import com.ruling.cost.user.domain.User;
import com.ruling.cost.user.service.UserManager;
import com.ruling.cost.utils.BaseAction;

public class UserAction extends BaseAction {

	UserManager userService;
	ItemManager itemService;

	private User user;

	private ItemUser itemuser;

	public ItemUser getItemuser() {
		return itemuser;
	}

	public void setItemuser(ItemUser itemuser) {
		this.itemuser = itemuser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserManager getUserService() {
		return userService;
	}

	public void setUserService(UserManager userService) {
		this.userService = userService;
	}

	// 查看职位

	public String lookpost() {
		List<Map<String, List<User>>> allist = new ArrayList<Map<String, List<User>>>();
		List<Post> list = userService.findallpost();// 查询出所有的职位，放到post里面
		List<User> userlist = new ArrayList<User>();
		try {
			for (int i = 0; i < list.size(); i++) {
				Map map = new HashMap();
				userlist = userService.finduserbyPost(list.get(i).getId());// 查询出所有的用户，这个职位的。
				map.put("value", userlist);
				map.put("key", list.get(i).getPostname());
				allist.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.getServletRequest().setAttribute("map", allist);// 查询玩之后放到数组里，

		return "post";
	}

	public String addInput() {
		this.getServletRequest().setAttribute("post", userService.findallpost());
		return "user_add_input";
	}

	public String add() {

		this.userService.addUser(user);

		return "user_add_success";
	}

	public String updateInput() {
		this.getServletRequest().setAttribute("post", userService.findallpost());
		this.getServletRequest().setAttribute("user", this.userService.findUserById(user.getId()));

		return "user_update_input";
	}

	public String update() {
		this.userService.updateUser(user);

		return "user_update_success";
	}

	public String delete() {

		String[] deleteid = user.getDeleteid().split(",");

		List userIdList = new ArrayList();

		for (int i = 0; i < deleteid.length; i++) {

			userIdList.add(Integer.parseInt(deleteid[i].trim()));
		}

		this.userService.delUser(userIdList);

		return "user_del_success";
	}

	public String browe() {
		this.getServletRequest().setAttribute("pm", this.userService.findAllUser(user));

		return "user_browe";
	}

	public String login() throws Exception {

		if (user == null) {

			return "login";
		}

		User u = this.userService.login(user.getUsername(), user.getPassword());
		List list = itemService.queryUseritem(u.getId());
		if (null != list && list.size() > 0) {
			u.setWorklevel("manager");
		}

		// u.setWorklevel("manager");
		ActionContext ct = ActionContext.getContext();

		Map session = ct.getSession();

		session.put("login", u);

		return "login_success";
	}

	public String selectBrowe() {
		this.getServletRequest().setAttribute("pm", this.userService.findAllUnregisterUser(user));

		return "select_browe";
	}

	public String itemUserSelectBrowe() {
		if (itemuser != null) {
			this.getServletRequest().setAttribute("itemid", itemuser.getItemid());
		}
		this.getServletRequest().setAttribute("pm", this.userService.findUserForItem(itemuser.getItemid()));

		return "itemUserSelectBrowe";
	}

	public String loginLeft() {

		return "loginLeft";
	}

	public String loginTitle() {

		return "loginTitle";
	}

	public String broweItemUser() {

		this.getServletRequest().setAttribute("pm",
				this.userService.findUserByItemId(itemuser.getItemid()));

		this.getServletRequest().setAttribute("itemuser", itemuser);

		return "broweItemUser";
	}

	// 加载主页面下部页面
	public String loginBottom() {

		return "loginBottom";
	}

	// 加载主页面桌面
	public String loginCenter() {
		return "loginCenter";
	}

	// 退出系统
	public String logout() {

		ActionContext ct = ActionContext.getContext();

		Map session = ct.getSession();

		this.getServletRequest().getSession().setAttribute("login", null);
		session.remove("login");

		session.clear();

		return "logout";
	}

	public String look() {
		this.getServletRequest()
				.setAttribute("post", userService.findallpost());
		this.getServletRequest().setAttribute("user",
				this.userService.findUserById(user.getId()));

		return "look";
	}

	// 修改密码
	public String editPwd() throws Exception {

		ActionContext ct = ActionContext.getContext();

		Map session = ct.getSession();

		User u = (User) session.get("login");

		u.setPassword(user.getPassword());

		u.setNewpwd(user.getNewpwd());

		this.userService.updatePwd(u);

		return "edit_pwd_success";
	}

	public String broweDeleteItemUser() {

		this.getServletRequest().setAttribute("pm",
				this.userService.findUserByItemId(itemuser.getItemid()));

		this.getServletRequest().setAttribute("itemuser", itemuser);

		return "broweDeleteItemUser";
	}

	public String zzjg() {
		HttpServletRequest request = ServletActionContext.getRequest();

		try {
			List<Company> comps = userService.zzjg(null);
			
			request.setAttribute("comps", comps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showpage";
	}

	public ItemManager getItemService() {
		return itemService;
	}

	public void setItemService(ItemManager itemService) {
		this.itemService = itemService;
	}
}
