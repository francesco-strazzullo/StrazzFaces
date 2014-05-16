/**
 * @author f1l0
 */
PrimeFaces.widget.ExtPanel = PrimeFaces.widget.BaseWidget.extend({

    init: function(cfg) {

        this._super(cfg);
        this.name = cfg.widgetName;
        this.position = cfg.position;
        this.title = cfg.title;
        this.cbOnOpen = cfg.onopen;
        this.cbOnClose = cfg.onclose;
        this.isOpen = false;
        
        this.extpanelJQ = jQuery(this.jqId);

        var that = this;

        jQuery(document).ready(function() {

            if(that.position === "top" || that.position === "bottom") {
                that.refreshTopBottom();
            }
            else if(that.position === "right" || that.position === "left") {
                that.refreshRightLeft();
            }
            that.bindEvent();
        });

    },

    bindEvent: function() {
        var _this = this;
        this.extpanelJQ.find("DIV.extpanel-header").click(function() { _this.toggle(); });
    },

    toggle: function() {
        if(this.isOpen)
            this.close();
        else this.open();
    },

    refreshTopBottom: function() {
        this.extpanelJQ.css("margin-left", "-"+ parseInt(this.extpanelJQ.width()/2, 10) +"px");
    },

    refreshRightLeft: function() {
        this.extpanelJQ.css("margin-top", "-"+ parseInt(this.extpanelJQ.height()/2, 10) +"px");
    },

    open: function() {
        if(!this.isOpen) {
            this.onopen();
            this.extpanelJQ.removeClass("extpanel-close").addClass("extpanel-open");
            if(this.position === "top")
                this.extpanelJQ.find("SPAN.ui-icon").removeClass("ui-icon-circle-triangle-s").addClass("ui-icon-circle-triangle-n");
            else if(this.position === "bottom")
                this.extpanelJQ.find("SPAN.ui-icon").removeClass("ui-icon-circle-triangle-n").addClass("ui-icon-circle-triangle-s");
            this.extpanelJQ.find("DIV.extpanel-content").css("display", "");
            this.refreshTopBottom();
            this.isOpen = true;
        }
    },
            
    close: function() {
        if(this.isOpen) {
            this.onclose();
            this.extpanelJQ.removeClass("extpanel-open").addClass("extpanel-close");
            if(this.position === "top")
                this.extpanelJQ.find("SPAN.ui-icon").removeClass("ui-icon-circle-triangle-n").addClass("ui-icon-circle-triangle-s");
            else if(this.position === "bottom")
                this.extpanelJQ.find("SPAN.ui-icon").removeClass("ui-icon-circle-triangle-s").addClass("ui-icon-circle-triangle-n");
            this.extpanelJQ.find("DIV.extpanel-content").css( "display","none");
            this.refreshTopBottom();
            this.isOpen = false;
        }
    },
    
    onopen: function() {
      if(this.cbOnOpen && this.cbOnOpen !== "") { eval(this.cbOnOpen); }  
    },

     onclose: function() {
      if(this.cbOnClose && this.cbOnClose !== "") {  eval(this.cbOnClose); }  
    }
});
