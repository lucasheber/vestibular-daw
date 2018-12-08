package lhos.jompscity.vestibular.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lhos.jompscity.vestibular.dao.UserDAO;
import lhos.jompscity.vestibular.model.User;

@ViewScoped
@ManagedBean
public class UserBeans {

	private User user = new User();
	
	public void login() {
		boolean result = new UserDAO().exists(user);
		
		System.out.println(user.getCpf() + " " + result);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
