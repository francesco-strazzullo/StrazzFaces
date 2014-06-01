package it.strazz.faces.gchart.model;

import java.util.Collection;

public interface GChartModelRow {
	public abstract Collection<Object> getValues();
	public abstract String getLabel();
}
