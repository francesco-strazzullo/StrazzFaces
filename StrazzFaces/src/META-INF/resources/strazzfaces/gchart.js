PrimeFaces.widget.GChart = PrimeFaces.widget.BaseWidget.extend({
	init : function(cfg) {

		var that = this;

		this._super(cfg);

		this.data = cfg.data ? JSON.parse(cfg.data) : [];
		this.type = cfg.type;
		this.height = cfg.height;
		this.width = cfg.width;
		this.title = cfg.title;
		
		google.load('visualization', '1.0', {
			'packages' : [ 'corechart' ]
		});
		google.setOnLoadCallback(function() {
			that.draw();
		});

		if (google.visualization) {
			that.draw();
		}

	},

	draw : function() {

		// Create the data table.
		var dataTable = google.visualization.arrayToDataTable(this.data);

		var options = {};

		options.title = this.title;
		options.width = parseInt(this.width,10);
		options.height = parseInt(this.height,10);
		/*
		 * var options = {'title':'How Much Pizza I Ate Last Night',
		 * 'width':400, 'height':300};
		 */

		var wrapper = new google.visualization.ChartWrapper({
			chartType : this.type,
			dataTable : dataTable,
			options : options,
			containerId : this.id
		});

		wrapper.draw();

	}

});