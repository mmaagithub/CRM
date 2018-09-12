package web.action;

import service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import domain.User;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();
	private UserService us;
							
	public void setUs(UserService us) {
		this.us = us;
	}

	public String login() throws Exception {
		User u = us.getUserByCodePassword(user);
		ActionContext.getContext().getSession().put("user", u);
		return "toHome";
	}

	public String regist() throws Exception {
		try {
			us.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			ActionContext.getContext().put("error", e.getMessage());
			return "regist";
		}
		return "toLogin";
	}

	public String logOut() throws Exception {
		ActionContext.getContext().getSession().clear();
		return "toLogin";
	}

	@Override
	public User getModel() {

		return user;
	}

}
