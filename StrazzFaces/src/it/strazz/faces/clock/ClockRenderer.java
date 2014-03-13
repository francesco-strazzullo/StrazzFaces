package it.strazz.faces.clock;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import org.primefaces.renderkit.CoreRenderer;

@FacesRenderer(componentFamily=Clock.COMPONENT_FAMILY,rendererType=ClockRenderer.RENDERER_TYPE)
public class ClockRenderer extends CoreRenderer {
	
	public static final String RENDERER_TYPE = "it.strazz.faces.ClockRenderer";

	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		
		Clock clock = (Clock) component;
		
		String widgetVar = clock.resolveWidgetVar();
		
		ResponseWriter writer = context.getResponseWriter();
		
		context.getResponseWriter().write("<div id=\"" + component.getClientId() + "\"></div>");
		
		startScript(writer, component.getClientId());
		
		writer.write("if(!" + widgetVar + "){");
		writer.write("var " + widgetVar + " = new window.Clock(" + this.writeCfgObject(createCfgMap(clock)) + ");");
		writer.write("}else{" + widgetVar + ".digital = " + clock.isDigital() + ";}");
		writer.write(widgetVar+".draw();");
		
		endScript(writer);	
		
	}
	
	private Map<String,Object> createCfgMap(Clock clock){
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("id", clock.getClientId());
		map.put("pattern", clock.getPattern());
		map.put("digital", clock.isDigital());
		map.put("time", clock.getStartTime() != null ? clock.getStartTime().getTime() : null);
		
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