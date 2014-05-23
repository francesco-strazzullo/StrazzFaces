package it.strazz.faces.gchart.model;


public class BarChartModel extends BaseGChartModel{

	public static final String CHART_TYPE = "BarChart";
	private static final long serialVersionUID = -3622876611665620067L;
	
	@Override
	public String getChartType(){
		return CHART_TYPE;
	}
	
	public void addRow(String label,Number value){
		this.addRow(new Object[]{label,value});
	}
}
