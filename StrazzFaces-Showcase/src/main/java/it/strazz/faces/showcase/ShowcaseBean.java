package it.strazz.faces.showcase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class ShowcaseBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Theme> themes;
	    
    @PostConstruct
    public void init() {
        themes = new ArrayList<Theme>();
//        themes.add(new Theme(0, "Afterdark", "afterdark"));
//        themes.add(new Theme(1, "Afternoon", "afternoon"));
//        themes.add(new Theme(2, "Afterwork", "afterwork"));
//        themes.add(new Theme(3, "Aristo", "aristo"));
//        themes.add(new Theme(4, "Black-Tie", "black-tie"));
//        themes.add(new Theme(5, "Blitzer", "blitzer"));
//        themes.add(new Theme(6, "Bluesky", "bluesky"));
//        themes.add(new Theme(7, "Bootstrap", "bootstrap"));
//        themes.add(new Theme(8, "Casablanca", "casablanca"));
//        themes.add(new Theme(9, "Cupertino", "cupertino"));
//        themes.add(new Theme(10, "Cruze", "cruze"));
//        themes.add(new Theme(11, "Dark-Hive", "dark-hive"));
//        themes.add(new Theme(12, "Delta", "delta"));
//        themes.add(new Theme(13, "Dot-Luv", "dot-luv"));
//        themes.add(new Theme(14, "Eggplant", "eggplant"));
//        themes.add(new Theme(15, "Excite-Bike", "excite-bike"));
//        themes.add(new Theme(16, "Flick", "flick"));
//        themes.add(new Theme(17, "Glass-X", "glass-x"));
//        themes.add(new Theme(18, "Home", "home"));
//        themes.add(new Theme(19, "Hot-Sneaks", "hot-sneaks"));
//        themes.add(new Theme(20, "Humanity", "humanity"));
//        themes.add(new Theme(21, "Le-Frog", "le-frog"));
//        themes.add(new Theme(22, "Midnight", "midnight"));
//        themes.add(new Theme(23, "Mint-Choc", "mint-choc"));
//        themes.add(new Theme(24, "Overcast", "overcast"));
//        themes.add(new Theme(25, "Pepper-Grinder", "pepper-grinder"));
//        themes.add(new Theme(26, "Redmond", "redmond"));
//        themes.add(new Theme(27, "Rocket", "rocket"));
//        themes.add(new Theme(28, "Sam", "sam"));
//        themes.add(new Theme(29, "Smoothness", "smoothness"));
//        themes.add(new Theme(30, "South-Street", "south-street"));
//        themes.add(new Theme(31, "Start", "start"));
//        themes.add(new Theme(32, "Sunny", "sunny"));
//        themes.add(new Theme(33, "Swanky-Purse", "swanky-purse"));
//        themes.add(new Theme(34, "Trontastic", "trontastic"));
//        themes.add(new Theme(35, "UI-Darkness", "ui-darkness"));
//        themes.add(new Theme(36, "UI-Lightness", "ui-lightness"));
//        themes.add(new Theme(37, "Vader", "vader"));
        
        themes.add(new Theme(3, "Aristo", "aristo"));
        themes.add(new Theme(4, "Black-Tie", "black-tie"));
    }
     
    public List<Theme> getThemes() {
        return themes;
    } 
}
