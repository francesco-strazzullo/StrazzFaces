package it.strazz.faces.analogclock;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import org.primefaces.renderkit.CoreRenderer;

@FacesRenderer(componentFamily=AnalogClock.COMPONENT_FAMILY,rendererType=AnalogClockRenderer.RENDERER_TYPE)
public class AnalogClockRenderer extends CoreRenderer {
	
	public static final String RENDERER_TYPE = "it.strazz.faces.AnalogClockRenderer";

	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		
		AnalogClock analogClock = (AnalogClock) component;
		
		String widgetVar = analogClock.resolveWidgetVar();
		
		ResponseWriter writer = context.getResponseWriter();
		
		context.getResponseWriter().write("<div id=\"" + component.getClientId() + "\"></div>");
		
		startScript(writer, component.getClientId());
		
		writer.write("if(!" + widgetVar + "){");
		writer.write("var " + widgetVar + " = new window.AnalogClock(" + this.writeCfgObject(createCfgMap(analogClock)) + ");");
		writer.write("}");
		writer.write(widgetVar+".draw();");
		
		endScript(writer);	
		
	}
	
	private Map<String,Object> createCfgMap(AnalogClock clock){
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("id", clock.getClientId());
		map.put("time", clock.getStartTime() != null ? clock.getStartTime().getTime() : null);
		map.put("mode", clock.getMode());
		
		return map;
	}
	
	private String writeCfgObject(Map<String,Object> attrs){
		StringBuilder sb = new StringBuilder("{");
		
		int i = 0;
		for (Entry<String, Object> entry : attrs.entrySet()) {
			
			String key = entry.getKey();
			Object value = entry.getValue();
			
			if(value != null){
				if(value instanceof CharSequence){
					value = "'" + value + "'";
				}
				
				sb.append(key).append(":").append(value);
				
				if(i < attrs.size()-1){
					sb.append(",");
				}
			}
			
			i++;
		
		}
		
		sb.append("}");
		
		return sb.toString();
	}
	
}