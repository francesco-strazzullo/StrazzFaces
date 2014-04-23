package it.strazz.faces.showcase.clock;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ClockBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date time;
	
	@PostConstruct
	public void loadTimes(){
		time = new Date();
		
		Calendar c = Calendar.getInstance();
		
		c.setTime(time);
	}
	
	public Date getTime() {
		return time;
	}
	
}
