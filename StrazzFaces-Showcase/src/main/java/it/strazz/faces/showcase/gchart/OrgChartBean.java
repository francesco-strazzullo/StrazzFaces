package it.strazz.faces.showcase.gchart;

import it.strazz.faces.gchart.model.GChartModel;
import it.strazz.faces.gchart.model.GChartModelBuilder;
import it.strazz.faces.gchart.model.GChartType;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class OrgChartBean implements Serializable {
	
	private static final long serialVersionUID = 253762400419864192L;

	private GChartModel chartModel = null;
	
	public GChartModel getChart(){
		return chartModel;
	}
	
	@PostConstruct
	public void generateModel() {
		chartModel = new GChartModelBuilder().setChartType(GChartType.ORGANIZATIONAL)
			.addColumns("Name","Manager","Role")
			.addRow("Mike", null,"President")
			.addRow("Alice", "Mike","Manager")
			.addRow("Jim", "Mike","Manager")
			.addRow("Bob", "Alice",null)
			.addOption("size", "large")
			.build();
	}
}

