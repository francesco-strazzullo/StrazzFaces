window.AnalogClock = function(cfg) {
	this.componentId = cfg.id;
	this.startTime = cfg.time && cfg.mode !== 'client' ? moment(cfg.time) : moment();

	this.draw = function(){
		this.canvas = Raphael(this.componentId, 200, 200);
		this.clock = this.canvas.circle(100, 100, 95);
		this.clock.attr({
			"fill" : "#f5f5f5",
			"stroke" : "#444444",
			"stroke-width" : "5"
		})
		var hour_sign;
		for (i = 0; i < 12; i++) {
			var start_x = 100 + Math.round(80 * Math.cos(30 * i * Math.PI / 180));
			var start_y = 100 + Math.round(80 * Math.sin(30 * i * Math.PI / 180));
			var end_x = 100 + Math.round(90 * Math.cos(30 * i * Math.PI / 180));
			var end_y = 100 + Math.round(90 * Math.sin(30 * i * Math.PI / 180));
			hour_sign = this.canvas.path("M" + start_x + " " + start_y + "L" + end_x
					+ " " + end_y);
		}
		this.hour_hand = this.canvas.path("M100 100L100 50");
		this.hour_hand.attr({
			stroke : "#444444",
			"stroke-width" : 6
		});
		this.minute_hand = this.canvas.path("M100 100L100 40");
		this.minute_hand.attr({
			stroke : "#444444",
			"stroke-width" : 4
		});
		this.second_hand = this.canvas.path("M100 110L100 25");
		this.second_hand.attr({
			stroke : "#444444",
			"stroke-width" : 2
		});
		var pin = this.canvas.circle(100, 100, 5);
		pin.attr("fill", "#000000");
		
		this.update();
	}
	
	this.update = function() {
		var now = this.startTime;
		var hours = now.hours();
		var minutes = now.minutes();
		var seconds = now.seconds();
		this.hour_hand.rotate(30 * hours + (minutes / 2.5), 100, 100);
		this.minute_hand.rotate(6 * minutes, 100, 100);
		this.second_hand.rotate(6 * seconds, 100, 100);

		this.startTime.add('s',1);
	}
	
	this.interval = setInterval((function(self) {
		return function() {
			self.update();
		}
	})(this), 1000);
}


