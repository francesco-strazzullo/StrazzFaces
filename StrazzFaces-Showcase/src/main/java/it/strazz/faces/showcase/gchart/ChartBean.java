package it.strazz.faces.showcase.gchart;

import it.strazz.faces.gchart.model.GChartModel;
import it.strazz.faces.gchart.model.GChartModelBuilder;
import it.strazz.faces.gchart.model.GChartModelRow;
import it.strazz.faces.gchart.model.GChartType;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

@ManagedBean
public class ChartBean implements Serializable {
	
	private static final long serialVersionUID = 253762400419864192L;

	private int mushrooms = 1;
	private int onions = 1;
	private GChartType chartType = GChartType.PIE;
	private GChartModel chartModel = null;
	
	public GChartModel getChart(){
		return chartModel;
	}
	
	@PostConstruct
	public void generateModel() {
		chartModel = new GChartModelBuilder().setChartType(getChartType())
				.addColumns("Topping", "Slices").addRow("Mushrooms", mushrooms)
				.addRow("Onions", onions).build();
	}
	
	public void onSelect(SelectEvent event){
		try {
			JSONArray value = (JSONArray) event.getObject();
			if(value.length() > 0){
				JSONObject object = (JSONObject) value.get(0);
				String label = new ArrayList<GChartModelRow>(this.getChart().getRows()).get((Integer) object.get("row")).getLabel();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "You have selected: " + label, null));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public int getMushrooms() {
		return mushrooms;
	}

	public void setMushrooms(int mushrooms) {
		this.mushrooms = mushrooms;
	}

	public int getOnions() {
		return onions;
	}

	public void setOnions(int onions) {
		this.onions = onions;
	}

	public GChartType getChartType() {
		return chartType;
	}

	public void setChartType(GChartType chartType) {
		this.chartType = chartType;
	}
}
