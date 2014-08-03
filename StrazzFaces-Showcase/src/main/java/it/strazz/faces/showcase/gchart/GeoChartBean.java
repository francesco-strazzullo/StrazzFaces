package it.strazz.faces.showcase.gchart;

import it.strazz.faces.gchart.model.GChartModel;
import it.strazz.faces.gchart.model.GChartModelBuilder;
import it.strazz.faces.gchart.model.GChartType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class GeoChartBean implements Serializable {

	private static final long serialVersionUID = 253762400419864192L;

	private GChartModel chartModel = null;

	public GChartModel getChart() {
		return chartModel;
	}

	@PostConstruct
	public void generateModel() {

		Locale englishLocale = Locale.ENGLISH;

		Map<String, Object> colorAxis = new HashMap<String, Object>();
		colorAxis.put("colors", new String[] { "white", "orange" });

		chartModel = new GChartModelBuilder().setChartType(GChartType.GEO)
				.addColumns("Country", "Popularity")
				.addRow(Locale.GERMANY.getDisplayCountry(englishLocale), 1200)
				.addRow(Locale.FRANCE.getDisplayCountry(englishLocale), 1800)
				.addRow("Russia", 1800)
				.addRow(Locale.ITALY.getDisplayCountry(englishLocale), 2000)
				.addRow(Locale.CHINA.getDisplayCountry(englishLocale), 2200)
				.addRow(Locale.US.getDisplayCountry(englishLocale), 2500)
				.addOption("colorAxis", colorAxis)
				.build();
	}
}
