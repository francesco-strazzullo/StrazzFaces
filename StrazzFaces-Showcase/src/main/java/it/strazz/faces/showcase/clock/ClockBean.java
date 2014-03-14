package it.strazz.faces.showcase.clock;

import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ClockBean {

	private boolean digital = false;
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
	
	public void swap(){
		this.digital = !this.digital;
	}
	
	public boolean isDigital() {
		return digital;
	}

	public void setDigital(boolean digital) {
		this.digital = digital;
	}
	
}
