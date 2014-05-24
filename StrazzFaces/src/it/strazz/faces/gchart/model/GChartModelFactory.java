package it.strazz.faces.gchart.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GChartModelFactory {
	
	private static final Map<String,Class<? extends GChartModel>> chartClasses;
	
	static{
		chartClasses = new HashMap<String, Class<? extends GChartModel>>();
		
		chartClasses.put(PieChartModel.CHART_TYPE, PieChartModel.class);
		chartClasses.put(BarChartModel.CHART_TYPE, BarChartModel.class);
		chartClasses.put(ColumnChartModel.CHART_TYPE, ColumnChartModel.class);
		chartClasses.put(AreaChartModel.CHART_TYPE, AreaChartModel.class);
	}
	
	private GChartModelFactory(){}
	
	public static GChartModel newChartModel(String chartType){
		Class<? extends GChartModel> clazz = chartClasses.get(chartType);
		try {
			return clazz != null ? clazz.newInstance() : null;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Collection<String> getTypes(){
		return chartClasses.keySet();
	}
	
}
