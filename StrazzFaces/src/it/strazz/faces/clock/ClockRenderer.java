package it.strazz.faces.clock;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

@FacesRenderer(componentFamily=Clock.COMPONENT_FAMILY,rendererType=ClockRenderer.RENDERER_TYPE)
public class ClockRenderer extends Renderer {
	
	public static final String RENDERER_TYPE = "it.strazz.faces.ClockRenderer";

	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		
		Clock clock = (Clock) component;
		
		if(clock.isDigital()){
			context.getResponseWriter().write("<div id='" + component.getClientId() + "'></div>");
			context.getResponseWriter().write("<script>document.getElementById('" + component.getClientId() + "').innerHTML = (new Date()).toString();setInterval(function(){document.getElementById('" + component.getClientId() + "').innerHTML = (new Date()).toString();},1000);</script>");
		}else{
			context.getResponseWriter().write("<canvas id='" + component.getClientId() + "' class='CoolClock'></canvas>");
			context.getResponseWriter().write("<script>CoolClock.findAndCreateClocks();</script>");
		}
		
		
	}
	
}