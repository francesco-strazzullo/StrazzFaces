package it.strazz.faces.analogclock.model;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {

	private static final long serialVersionUID = -2809548631544370757L;

	private Color face;
	private Color border;
	private Color hourSigns;
	private Color hourHand;
	private Color minuteHand;
	private Color secondHand;
	private Color pin;
	
	public DefaultColorTheme(){}

	@Override
	public Color getFace() {
		return face;
	}

	@Override
	public void setFace(Color face) {
		this.face = face;
	}

	@Override
	public Color getBorder() {
		return border;
	}

	@Override
	public void setBorder(Color border) {
		this.border = border;
	}

	@Override
	public Color getHourSigns() {
		return hourSigns;
	}

	@Override
	public void setHourSigns(Color hourSigns) {
		this.hourSigns = hourSigns;
	}

	@Override
	public Color getHourHand() {
		return hourHand;
	}

	@Override
	public void setHourHand(Color hourHand) {
		this.hourHand = hourHand;
	}

	@Override
	public Color getMinuteHand() {
		return minuteHand;
	}

	@Override
	public void setMinuteHand(Color minuteHand) {
		this.minuteHand = minuteHand;
	}

	@Override
	public Color getSecondHand() {
		return secondHand;
	}

	@Override
	public void setSecondHand(Color secondHand) {
		this.secondHand = secondHand;
	}

	@Override
	public Color getPin() {
		return pin;
	}

	@Override
	public void setPin(Color pin) {
		this.pin = pin;
	}

	
}
