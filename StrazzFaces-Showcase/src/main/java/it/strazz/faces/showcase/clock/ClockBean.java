package it.strazz.faces.showcase.clock;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ClockBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date time = new Date();
		
	public Date getTime() {
		return time;
	}
	
}
