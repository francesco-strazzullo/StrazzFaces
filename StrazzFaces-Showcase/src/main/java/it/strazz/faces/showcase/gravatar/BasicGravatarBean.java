package it.strazz.faces.showcase.gravatar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class BasicGravatarBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<GravatarProfile> profiles = new ArrayList<GravatarProfile>(0);
	
	public BasicGravatarBean() {
		profiles.add(new GravatarProfile("Francesco Strazzullo","francesco.strazzullo86@gmail.com",  "I'm a J2EE software architect, I usually work applications built with the Spring+Hibernate+JSF 2.X+Primefaces Stack. I'm the lead architect of JAF (Java Apra Framework): a RAD framework used to create enteprise web application. Recently I'm working on AngularJS application. I also deal with Business Process Management using the BPM Engine Activiti. At last I'm developing StrazzFaces: my personal JSF component library! ;)"));
		profiles.add(new GravatarProfile("Massimo Biagioli","biagiolimassimo@gmail.com", "Senior Java Developer"));
		profiles.add(new GravatarProfile("Luca Vargetto","lucavargetto@gmail.com", "Senior Software Engineer"));
	}

	public List<GravatarProfile> getProfiles() {
		return profiles;
	}

}
