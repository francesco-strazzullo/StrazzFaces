package it.strazz.faces.showcase.clock;

import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ClockBean {

	private Date romeTime = new Date();

	
	public Date getRomeTime() {
		return romeTime;
	}

	public Date getLondonTime(){
		Calendar c = Calendar.getInstance();
		c.setTime(romeTime);
		c.add(Calendar.HOUR, -1);
		return c.getTime();
	}
	
	public Date getNewYorkTime(){
		Calendar c = Calendar.getInstance();
		c.setTime(romeTime);
		c.add(Calendar.HOUR, -5);
		return c.getTime();
	}
	
}
