package it.strazz.faces.showcase.switchcomponent;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIInput;

import org.primefaces.component.behavior.ajax.AjaxBehaviorHandler;

@ManagedBean
@RequestScoped
public class SwitchBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UIInput switchComponent;

	public UIInput getSwitchComponent() {
		return switchComponent;
	}

	public void setSwitchComponent(UIInput switchComponent) {
		this.switchComponent = switchComponent;
	}

	public void onChangeSwitch(){
		System.out.println(this.switchComponent.getValue());
	}
}
