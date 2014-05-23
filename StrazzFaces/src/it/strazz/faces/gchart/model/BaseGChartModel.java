package it.strazz.faces.gchart.model;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseGChartModel implements GChartModel{

	private static final long serialVersionUID = -4757917806522708660L;
	protected List<Object[]> data = new ArrayList<Object[]>(0);

	@Override
	public List<Object[]> getData() {
		return data;
	}

	@Override
	public void addRow(Object...value) {
		data.add(value);
	}

	@Override
	public void setColumns(String[] columns){
		data.add(columns);
	}

}