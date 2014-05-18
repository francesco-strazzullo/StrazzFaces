/**
 * @author f1l0
 */
PrimeFaces.widget.CountDown = PrimeFaces.widget.BaseWidget.extend({

    init: function(cfg) {
        this._super(cfg);
        this.name = cfg.widgetName;
        var that = this;

        jQuery(document).ready(function() {
            jQuery(that.jqId).kkcountdown({
                dayText         : that.cfg.dayText,
                daysText        : that.cfg.daysText,
                hoursText       : that.cfg.hoursText,
                minutesText     : that.cfg.minutesText,
                secondsText     : that.cfg.secondsText,
                oneDayClass     : that.cfg.oneDayClass,
                textAfterCount  : that.cfg.textAfterCount,
                displayDays     : that.cfg.displayDays,
                displayZeroDays : that.cfg.displayZeroDays,
                addClass        : that.cfg.addClass,
                callback        : that.cfg.callback
            });
        });
    }
});
