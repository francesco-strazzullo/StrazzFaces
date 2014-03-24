PrimeFaces.widget.Switch = PrimeFaces.widget.BaseWidget.extend({
	init : function(cfg) {
		
		this._super(cfg);
		
		this.input = jQuery(this.jqId+"_hidden");
		this.name = cfg.widgetName;
		
		var that = this;
		
		jQuery(document).ready(function(){
			if(!that.switchComponent){
				that.switchComponent = Drinks.createElement('switch', {"id":that.id+'_switch'});
				that.switchComponent.setAttribute("value",that.input.val() === 'true' ? 1 : 0);
				that.switchComponent.setAttribute("type","side");
				that.switchComponent.setAttribute("onchange","eval('" + that.name + ".onchange(this.value);');");
			}
			
			Drinks.appendChild(that.id, that.switchComponent);
		});
		
		if(this.cfg.behaviors && this.cfg.behaviors.click) {
			this.jq.bind("click", function(e) {
				that.cfg.behaviors.click.call(that.input,e);
	        });
		}
		
		
		
	},
	
	onchange: function(value){
		this.input.val(value === 1);
		if(this.cfg.behaviors && this.cfg.behaviors.change) {
			this.cfg.behaviors.change.call(this.input);
		}
	}
});
