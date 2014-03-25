package it.strazz.faces.showcase.switchcomponent;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class SwitchBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean toogleValue;

	public boolean isToogleValue() {
		return toogleValue;
	}

	public void setToogleValue(boolean toogleValue) {
		this.toogleValue = toogleValue;
	}

	public void onToggleAllSwitch(){
		System.out.println("toggling all to " + toogleValue);
	}
}
