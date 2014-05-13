/**
 * @author f1l0
 */
PrimeFaces.widget.CountDown = PrimeFaces.widget.BaseWidget.extend({

    init: function(cfg) {

        this._super(cfg);
        this.name = cfg.widgetName;
//        this.dayText = cfg.dayText;
//        this.daysText = cfg.daysText;
//        this.hoursText = cfg.hoursText;
//        this.minutesText = cfg.minutesText;
//        this.secondsText = cfg.secondsText;
//        this.oneDayClass = cfg.oneDayClass;
//        this.textAfterCount = cfg.textAfterCount;
//        this.displayDays = cfg.displayDays;
//        this.displayZeroDays = cfg.displayZeroDays;
//        this.addClass = cfg.addClass;
//        this.callback = cfg.callback;
        
        //this.countdownJQ = jQuery(this.jqId);

        var that = this;

        jQuery(document).ready(function() {
            
            jQuery(that.jqId).kkcountdown({
                dayText         : cfg.dayText,
                daysText        : cfg.daysText,
                hoursText       : cfg.hoursText,
                minutesText     : cfg.minutesText,
                secondsText     : cfg.secondsText,
                oneDayClass     : cfg.oneDayClass,
                textAfterCount  : cfg.textAfterCount,
                displayDays     : cfg.displayDays,
                displayZeroDays : cfg.displayZeroDays,
                addClass        : cfg.addClass,
                callback        : cfg.callback
            });
        });
    }
});
