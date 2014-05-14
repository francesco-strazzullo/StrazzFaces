package it.strazz.faces.countdown;

import java.util.Date;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;

import org.primefaces.component.api.Widget;

@FacesComponent(value = CountDown.COMPONENT_TYPE)
@ResourceDependencies({
    @ResourceDependency(library = "primefaces", name = "jquery/jquery.js"),
    @ResourceDependency(library = "primefaces", name = "primefaces.css"),
    @ResourceDependency(library = "primefaces", name = "primefaces.js"),
    @ResourceDependency(library = "strazzfaces", name = "countdown.js"),
    @ResourceDependency(library = "strazzfaces", name = "kkcountdown.js"),
    @ResourceDependency(library = "css", name = "countdown.css"),
})
/**
 * @author f1l0
 */
public class CountDown extends UIInput implements Widget, ClientBehaviorHolder {
 
	public static final String COMPONENT_TYPE = "it.strazz.faces.CountDown";
	public static final String COMPONENT_FAMILY = "it.strazz.faces.components";

        @Override
	public String getFamily() {
            return COMPONENT_FAMILY;
	}

	public String getWidgetVar() {
            return (String) getStateHelper().eval(PropertyKeys.widgetVar, null);
	}

	public void setWidgetVar(String _widgetVar) {
            getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
	}
	
        public Date getDate() {
            return (Date) getStateHelper().eval(PropertyKeys.date, null);
	}

	public void setDate(Date date) {
            getStateHelper().put(PropertyKeys.date, date);
	}

        // Text displayed after certain number of days included in countdown (when number days = 1)
       	public String getDayText() {
            return (String) getStateHelper().eval(PropertyKeys.dayText, ":");
	}

	public void setDayText(String dayText) {
            getStateHelper().put(PropertyKeys.dayText, dayText);
	}

        // Text displayed after certain number of days included in countdown
        public String getDaysText() {
            return (String) getStateHelper().eval(PropertyKeys.daysText, ":");
	}

	public void setDaysText(String daysText) {
            getStateHelper().put(PropertyKeys.daysText, daysText);
	}

        // Text displayed after certain number of hours included in countdown
        public String getHoursText() {
            return (String) getStateHelper().eval(PropertyKeys.hoursText, ":");
	}

	public void setHoursText(String hoursText) {
            getStateHelper().put(PropertyKeys.hoursText, hoursText);
	}

        // Text displayed after certain number of minutes included in countdown
        public String getMinutesText() {
            return (String) getStateHelper().eval(PropertyKeys.minutesText, ":");
	}

	public void setMinutesText(String minutesText) {
            getStateHelper().put(PropertyKeys.minutesText, minutesText);
	}

        // Text displayed after certain number of seconds included in countdown
        public String getSecondsText() {
            return (String) getStateHelper().eval(PropertyKeys.secondsText, null);
	}

	public void setSecondsText(String secondsText) {
            getStateHelper().put(PropertyKeys.secondsText, secondsText);
	}

        // Class will be added if there is less than 24 hours untill the end of countdown
        public boolean isOneDayClass() {
            return (boolean) getStateHelper().eval(PropertyKeys.oneDayClass, false);
	}

	public void setOneDayClass(boolean oneDayClass) {
            getStateHelper().put(PropertyKeys.oneDayClass, oneDayClass);
	}

        // Text displayed after certain number of seconds included in countdown
        public String getTextAfterCount() {
            return (String) getStateHelper().eval(PropertyKeys.textAfterCount, "Countdown complete!");
	}

	public void setTextAfterCount(String textAfterCount) {
            getStateHelper().put(PropertyKeys.textAfterCount, textAfterCount);
	}

        // Displaying the number of days remaining till the end of countdown: 
        // true -> the number of days is displayed, 
        // false -> the number of days is not displayed
        public boolean isDisplayDays() {
            return (boolean) getStateHelper().eval(PropertyKeys.displayDays, true);
	}

	public void setDisplayDays(boolean displayDays) {
            getStateHelper().put(PropertyKeys.displayDays, displayDays);
	}

        // Displaying the number of days when number of days is 0.
        public boolean isDisplayZeroDays() {
            return (boolean) getStateHelper().eval(PropertyKeys.displayZeroDays, false);
	}

	public void setDisplayZeroDays(boolean displayZeroDays) {
            getStateHelper().put(PropertyKeys.displayZeroDays, displayZeroDays);
	}

        // Adding a class to markers indicating countdown (e.g. indicated the name of call e.g. ‘class-name’)
        public String getAddClass() {
            return (String) getStateHelper().eval(PropertyKeys.addClass, null);
	}

	public void setAddClass(String addClass) {
            getStateHelper().put(PropertyKeys.addClass, addClass);
	}

        // Indicate the function which should be launched after the end of countdown
        public String getOncomplete() {
            return (String) getStateHelper().eval(PropertyKeys.oncomplete, null);
	}

	public void setOncomplete(String oncomplete) {
            getStateHelper().put(PropertyKeys.oncomplete, oncomplete);
	}

        // Inline style
        public String getStyle() {
            return (String) getStateHelper().eval(PropertyKeys.style, null);
	}

	public void setStyle(String style) {
            getStateHelper().put(PropertyKeys.style, style);
	}

        // CSS styleClass
        public String getStyleClass() {
            return (String) getStateHelper().eval(PropertyKeys.styleClass, null);
	}

	public void setStyleClass(String styleClass) {
            getStateHelper().put(PropertyKeys.styleClass, styleClass);
	}

        @Override
	public String resolveWidgetVar() {
            FacesContext context = getFacesContext();
            String userWidgetVar = (String) getAttributes().get("widgetVar");

            if (userWidgetVar != null)
                return userWidgetVar;
            else return "widget_"+ getClientId(context).replaceAll("-|" + UINamingContainer.getSeparatorChar(context), "_");
	}
	
	protected static enum PropertyKeys {
            date, 
            dayText,
            daysText,
            hoursText,
            minutesText,
            secondsText,
            oneDayClass,
            textAfterCount,
            displayDays,
            displayZeroDays,
            addClass,
            oncomplete,
            style,
            styleClass,
            widgetVar;
	}
}
