package it.strazz.faces.showcase.gravatar;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class AdvancedGravatarBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int size = 200;
	private String notFound = "default";
	private String username = "iamadummyuser@gmail.com";

	public String getNotFound() {
		return notFound;
	}

	public void setNotFound(String notFound) {
		this.notFound = notFound;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
