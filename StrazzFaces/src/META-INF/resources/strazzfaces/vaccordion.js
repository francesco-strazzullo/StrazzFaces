/**
 * @author f1l0
 */
PrimeFaces.widget.vAccordion = PrimeFaces.widget.BaseWidget.extend({

    init: function(cfg) {

        this._super(cfg);
        this.name = cfg.widgetName;
        this.iconPos = cfg.iconPos;
        this.iconOpen = cfg.iconOpen;
        this.iconClose = cfg.iconClose;
        this.styleClassOpen = cfg.styleClassOpen;
        this.styleClassClose = cfg.styleClassClose;

        var that = this;

        jQuery(document).ready(function() {

            var componentJQ = jQuery(that.jqId);
            var content = componentJQ.find(".vaccordion-content > *");

            var firstContent = jQuery(content.get(1));
            firstContent.css("display","");
            
            var maxHeight = firstContent.parent().height();

            content.each(function(idx, el) {
                var elem = jQuery(el);
                elem.css("height", maxHeight +"px");
                if(idx===0) {
                    elem.addClass("ui-corner-left");
                    elem.find("SPAN.ui-icon").addClass(that.iconOpen);
                }
                else if(idx===content.size()-2) {
                    elem.addClass("ui-corner-right");
                    elem.find("SPAN.ui-icon").addClass(that.iconClose);
                }
                else if(elem.hasClass("vtab-content"))
                    elem.css("border-right","0");
                else elem.find("SPAN.ui-icon").addClass(that.iconClose);
            });

            that.bindEvent();
        });

    },

    bindEvent: function() {
        var that = this;
        var componentJQ = jQuery(that.jqId);
        var icons = componentJQ.find("A.vtab-icon");
        

        if(this.iconPos === "top")
            icons.parent().addClass("vtab-icon-top");
        else if(this.iconPos === "bottom")
            icons.parent().addClass("vtab-icon-bottom");
        else icons.parent().addClass("vtab-icon-middle");

        icons.mouseover(function() { jQuery(this).addClass("ui-state-hover") });
        icons.mouseout(function() { jQuery(this).removeClass("ui-state-hover") });
        
        icons.click(function() {
            var icon = jQuery(this).find(".ui-icon");
            if(icon.hasClass(that.iconClose)) {
                icons.find(".ui-icon").removeClass(that.iconOpen).addClass(that.iconClose);
                icon.removeClass(that.iconClose).addClass(that.iconOpen);
                componentJQ.find(".vtab-content").css("display","none");
                jQuery(this).parent().next().css("display","");
            }
//            componentJQ.find(".vtab").each(function(){
//                if(jQuery(this).find("."+ that.iconClose).size()>0)
//                    jQuery(this).css("border-left","0");
//            });

            // Fix last element border
            if(this === icons.last().get(0)) {
                jQuery(this).parent().removeClass("ui-corner-right");
                jQuery(this).parent().next().addClass("ui-corner-right").css("border-right","");
            }
            else {
                icons.last().parent().addClass("ui-corner-right");
                icons.last().parent().next().addClass("ui-corner-right");
            }
        });
    }
});
