PrimeFaces.widget.GChart = PrimeFaces.widget.BaseWidget.extend({
	init : function(cfg) {

		var that = this;

		this._super(cfg);

		this.data = cfg.data ? JSON.parse(cfg.data) : [];
		this.type = cfg.type;
		this.height = cfg.height;
		this.width = cfg.width;
		this.title = cfg.title;
		this.input = jQuery(this.jqId+"_hidden");
		
		google.load('visualization', '1.0', {
			'packages' : [ 'corechart' ]
		});
		
		jQuery(document).ready(function(){
			google.setOnLoadCallback(function() {
				that.draw();
			});

			if (google.visualization) {
				that.draw();
			}

		});
		
	},

	draw : function() {

		// Create the data table.
		var dataTable = google.visualization.arrayToDataTable(this.data);

		var that = this;
		
		var options = {};

		options.title = this.title;
		options.width = parseInt(this.width,10);
		options.height = parseInt(this.height,10);
		
		this.wrapper = new google.visualization.ChartWrapper({
			chartType : this.type,
			dataTable : dataTable,
			options : options,
			containerId : this.id
		});
		
		if(this.cfg.behaviors && this.cfg.behaviors.select) {
			google.visualization.events.addListener(this.wrapper, 'select', function(e){
				console.log("Click");
				jQuery(that.jqId+"_hidden").val(JSON.stringify(that.wrapper.getChart().getSelection()));
				that.cfg.behaviors.select.call(jQuery(that.jqId+"_hidden"));
			});
		}

		this.wrapper.draw();

	}

});