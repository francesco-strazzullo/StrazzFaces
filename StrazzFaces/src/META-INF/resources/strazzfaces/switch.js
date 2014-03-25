PrimeFaces.widget.Switch = PrimeFaces.widget.BaseWidget.extend({
	init : function(cfg) {
		
		this._super(cfg);
		
		this.input = jQuery(this.jqId+"_hidden");
		this.name = cfg.widgetName;
		this.type = cfg.type;
		
		var that = this;
		
		jQuery(document).ready(function(){
			if(!that.switchComponent){
				that.switchComponent = Drinks.createElement('switch', {"id":that.id+'_switch'});
				that.switchComponent.setAttribute("onchange","eval('" + that.name + ".onchange(this.value);');");
				that.switchComponent.setAttribute("type",that.type);
			}
			
			Drinks.appendChild(that.id, that.switchComponent);
			
			var drinks = eval(that.id+'_switch');
			drinks.value = that.input.val() === 'true' ? 1 : 0;
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
