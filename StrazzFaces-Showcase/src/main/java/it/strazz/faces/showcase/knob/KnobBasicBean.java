package it.strazz.faces.showcase.knob;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class KnobBasicBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int value = 25;

	public void confirm() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You chose " + value));
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
