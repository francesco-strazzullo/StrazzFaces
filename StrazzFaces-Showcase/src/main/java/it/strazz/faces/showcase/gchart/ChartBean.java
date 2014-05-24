package it.strazz.faces.showcase.gchart;

import it.strazz.faces.gchart.model.GChartModel;
import it.strazz.faces.gchart.model.GChartModelFactory;
import it.strazz.faces.gchart.model.PieChartModel;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ManagedBean
public class ChartBean implements Serializable {
	
	private static final long serialVersionUID = 253762400419864192L;

	private int mushrooms = 1;
	private int onions = 1;
	private String chartType = PieChartModel.CHART_TYPE;
	private GChartModel chartModel = null;
	
	public GChartModel getChart(){
		return chartModel;
	}
	
	@PostConstruct
	public void generateModel(){
		
		chartModel = GChartModelFactory.newChartModel(getChartType());
		chartModel.setColumns(new String[]{"Topping","Slices"});
		chartModel.addRow("Mushrooms", mushrooms);
		chartModel.addRow("Onions", onions);
	
	}
	
	public void onSelect(SelectEvent event){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "You have selected: " + event.getObject(), null));
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

	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
}
