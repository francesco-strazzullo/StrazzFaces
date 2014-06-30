package it.strazz.faces.showcase.gravatar;

public class GravatarProfile {
	private String name;
	private String email;
	private String bio;
	
	public GravatarProfile(String name, String email, String bio) {
		super();
		this.name = name;
		this.email = email;
		this.bio = bio;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
}
