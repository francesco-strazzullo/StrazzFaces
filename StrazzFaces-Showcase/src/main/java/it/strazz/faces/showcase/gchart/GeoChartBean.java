package it.strazz.faces.showcase.gchart;

import it.strazz.faces.gchart.model.GChartModel;
import it.strazz.faces.gchart.model.GChartModelBuilder;
import it.strazz.faces.gchart.model.GChartType;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class GeoChartBean implements Serializable {
	
	private static final long serialVersionUID = 253762400419864192L;

	private GChartModel chartModel = null;
	
	public GChartModel getChart(){
		return chartModel;
	}
	
	@PostConstruct
	public void generateModel() {
		chartModel = new GChartModelBuilder().setChartType(GChartType.GEO)
				.addColumns("Country", "Popularity").addRow("Germany", 400).addRow("France", 600)
				.addRow("Italy", 1000).build();
	}
}
