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
	
	private Date romeTime;
	private Date londonTime;
	private Date newYorkTime;
	
	@PostConstruct
	public void loadTimes(){
		romeTime = new Date();
		
		Calendar c = Calendar.getInstance();
		
		c.setTime(romeTime);
		c.add(Calendar.HOUR, -1);
		londonTime = c.getTime();
		
		c.setTime(romeTime);
		c.add(Calendar.HOUR, -5);
		newYorkTime = c.getTime();
	}
	
	public Date getRomeTime() {
		return romeTime;
	}

	public Date getLondonTime(){
		return londonTime;
	}
	
	public Date getNewYorkTime(){
		return newYorkTime;
	}
	
}
