/**
 * @author f1l0
 */
PrimeFaces.widget.DockablePanel = PrimeFaces.widget.BaseWidget.extend({

    init: function(cfg) {

        this._super(cfg);
        this.name = cfg.widgetName;
        this.position = cfg.position;
        this.title = cfg.title;
        this.cbOnOpen = cfg.onopen;
        this.cbOnClose = cfg.onclose;
        this.isOpen = false;
        
        this.dockablepanelJQ = jQuery(this.jqId);

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
        this.dockablepanelJQ.find("DIV.dockablepanel-header").click(function() { _this.toggle(); });
    },

    toggle: function() {
        if(this.isOpen)
            this.close();
        else this.open();
    },

    refreshTopBottom: function() {
        this.dockablepanelJQ.css("margin-left", "-"+ parseInt(this.dockablepanelJQ.width()/2, 10) +"px");
    },

    refreshRightLeft: function() {
        this.dockablepanelJQ.css("margin-top", "-"+ parseInt(this.dockablepanelJQ.height()/2, 10) +"px");
    },

    open: function() {
        if(!this.isOpen) {
            this.onopen();
            this.dockablepanelJQ.removeClass("dockablepanel-close").addClass("dockablepanel-open");
            if(this.position === "top")
                this.dockablepanelJQ.find("SPAN.ui-icon").removeClass("ui-icon-circle-triangle-s").addClass("ui-icon-circle-triangle-n");
            else if(this.position === "bottom")
                this.dockablepanelJQ.find("SPAN.ui-icon").removeClass("ui-icon-circle-triangle-n").addClass("ui-icon-circle-triangle-s");
            this.dockablepanelJQ.find("DIV.dockablepanel-content").css("display","");
            this.refreshTopBottom();
            this.isOpen = true;
        }
    },
            
    close: function() {
        if(this.isOpen) {
            this.onclose();
            this.dockablepanelJQ.removeClass("dockablepanel-open").addClass("dockablepanel-close");
            if(this.position === "top")
                this.dockablepanelJQ.find("SPAN.ui-icon").removeClass("ui-icon-circle-triangle-n").addClass("ui-icon-circle-triangle-s");
            else if(this.position === "bottom")
                this.dockablepanelJQ.find("SPAN.ui-icon").removeClass("ui-icon-circle-triangle-s").addClass("ui-icon-circle-triangle-n");
            this.dockablepanelJQ.find("DIV.dockablepanel-content").css( "display","none");
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
