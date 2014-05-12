/**
 * @author f1l0
 */
PrimeFaces.widget.ExtPanel = PrimeFaces.widget.BaseWidget.extend({

    init: function(cfg) {

        this._super(cfg);
        this.name = cfg.widgetName;
        this.position = cfg.position;
        this.title = cfg.title;
        this.isOpen = false;

        var that = this;

        jQuery(document).ready(function() {

            if(that.position === "top" || that.position === "bottom") {
                that.refreshTop();
            }

            that.bindEvent();
        });

    },

    bindEvent: function() {
        var _this = this;
        jQuery(this.jqId).find("DIV.extpanel-header").click(function() { _this.toggle(); });
    },

    toggle: function() {
        if(this.isOpen)
            this.close();
        else this.open();
    },

    refreshTop: function() {
        jQuery(this.jqId).css("margin-left", "-"+ parseInt(jQuery(this.jqId).width()/2, 10) +"px");
    },

    open: function() {
        if(!this.isOpen) {
            jQuery(this.jqId).removeClass("extpanel-close").addClass("extpanel-open");
            jQuery(this.jqId).find("SPAN.ui-icon").removeClass("ui-icon-triangle-1-s").addClass("ui-icon-triangle-1-n");
            jQuery(this.jqId).find("DIV.extpanel-content").css("display", "");
            this.refreshTop();
            this.isOpen = true;
        }
    },
            
    close: function() {
        if(this.isOpen) {
            jQuery(this.jqId).removeClass("extpanel-open").addClass("extpanel-close");
            jQuery(this.jqId).find("SPAN.ui-icon").removeClass("ui-icon-triangle-1-n").addClass("ui-icon-triangle-1-s");
            jQuery(this.jqId).find("DIV.extpanel-content").css( "display","none");
            this.refreshTop();
            this.isOpen = false;
        }
    }
});
