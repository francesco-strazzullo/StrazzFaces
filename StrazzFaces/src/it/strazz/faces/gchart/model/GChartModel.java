package it.strazz.faces.gchart.model;

import java.io.Serializable;
import java.util.List;

public interface GChartModel extends Serializable{
	public abstract List<Object[]> getData();

	public abstract String getChartType();

	public abstract void addRow(Object...value);

	public abstract void setColumns(String[] columns);
}
