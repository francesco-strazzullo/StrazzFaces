package it.strazz.faces.showcase.clock;

import it.strazz.faces.analogclock.model.ColorTheme;
import it.strazz.faces.analogclock.model.DefaultColorTheme;

import java.awt.Color;
import java.io.Serializable;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ThemeClockBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ColorTheme randomTheme = new DefaultColorTheme();
	private ColorTheme customTheme = new DefaultColorTheme();
	private Random random = new Random();

	public ThemeClockBean() {
		
		customTheme.setBorder(Color.RED);
		customTheme.setFace(Color.DARK_GRAY);
		customTheme.setHourHand(Color.WHITE);
		customTheme.setHourSigns(Color.DARK_GRAY);
		customTheme.setMinuteHand(Color.WHITE);
		customTheme.setPin(Color.RED);
		customTheme.setSecondHand(Color.WHITE);
		
		randomTheme.setBorder(randomColor());
		randomTheme.setFace(randomColor());
		randomTheme.setHourHand(randomColor());
		randomTheme.setHourSigns(randomColor());
		randomTheme.setMinuteHand(randomColor());
		randomTheme.setPin(randomColor());
		randomTheme.setSecondHand(randomColor());
		
	}

	public ColorTheme getCustomTheme() {
		return customTheme;
	}

	public ColorTheme getRandomTheme() {
		return randomTheme;
	}

	private Color randomColor() {
		int red = random.nextInt(256);
		int green = random.nextInt(256);
		int blue = random.nextInt(256);
		return new Color(red, green, blue);
	}

}
