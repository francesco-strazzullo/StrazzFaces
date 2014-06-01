package it.strazz.faces.gchart.model;

public enum GChartType {
	PIE("PieChart"),
	AREA("AreaChart"),
	BAR("BarChart"),
	GEO("GeoChart"),
	COLUMN("ColumnChart");
	
	public String getChartName() {
		return chartName;
	}

	private GChartType(String chartName) {
		this.chartName = chartName;
	}

	private String chartName;
}
