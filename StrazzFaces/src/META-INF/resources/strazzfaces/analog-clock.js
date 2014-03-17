PrimeFaces.widget.AnalogClock = PrimeFaces.widget.BaseWidget.extend({
			init : function(cfg) {

				this._super(cfg);

				this.startTime = cfg.time && cfg.mode !== 'client' ? moment(cfg.time) : moment();

				this.colorScheme = cfg.colorScheme || 'standard';
				
				this.dimensions = new PrimeFaces.widget.AnalogClock.Dimensions(this.cfg.width || this.jq.width());
				
				this.interval = setInterval((function(self) {
					return function() {
						self.update();
					}
				})(this), 1000);

				this.draw();
			},

			draw : function() {
				this.canvas = Raphael(this.id, this.dimensions.size, this.dimensions.size);
				this.clock = this.canvas.circle(this.dimensions.half, this.dimensions.half, this.dimensions.clock_width);
				this.clock.attr({
					"fill" : PrimeFaces.widget.AnalogClock.colorSchemes[this.colorScheme].face,
					"stroke" :PrimeFaces.widget.AnalogClock.colorSchemes[this.colorScheme].border,
					"stroke-width" : "5"
				})
				
				var hour_sign;
				
				for (i = 0; i < 12; i++) {
					
					var start_x = this.dimensions.half + Math.round(this.dimensions.hour_sign_min_size * Math.cos(30 * i	* Math.PI / 180));
					var start_y = this.dimensions.half + Math.round(this.dimensions.hour_sign_min_size * Math.sin(30 * i * Math.PI / 180));
					var end_x = this.dimensions.half + Math.round(this.dimensions.hour_sign_max_size * Math.cos(30 * i * Math.PI	/ 180));
					var end_y = this.dimensions.half + Math.round(this.dimensions.hour_sign_max_size * Math.sin(30 * i * Math.PI	/ 180));
					
					hour_sign = this.canvas.path("M" + start_x + " " + start_y	+ "L" + end_x + " " + end_y);
					hour_sign.attr({
						"stroke":PrimeFaces.widget.AnalogClock.colorSchemes[this.colorScheme].hour_signs,
						"stroke-width" : this.dimensions.hour_sign_stroke_width
					});
				}
				
				this.hour_hand = this.canvas.path("M" + this.dimensions.half + " " + this.dimensions.half	+ "L" + this.dimensions.half + " " + this.dimensions.hour_hand_start_position);
				this.hour_hand.attr({
					stroke : PrimeFaces.widget.AnalogClock.colorSchemes[this.colorScheme].hour_hand,
					"stroke-width" : this.dimensions.hour_hand_stroke_width
				});
				
				this.minute_hand = this.canvas.path("M" + this.dimensions.half + " " + this.dimensions.half	+ "L" + this.dimensions.half + " " + this.dimensions.minute_hand_start_position);
				this.minute_hand.attr({
					stroke : PrimeFaces.widget.AnalogClock.colorSchemes[this.colorScheme].minute_hand,
					"stroke-width" : this.dimensions.minute_hand_stroke_width
				});
				
				this.second_hand = this.canvas.path("M" + this.dimensions.half + " " + this.dimensions.half	+ "L" + this.dimensions.half + " " + this.dimensions.second_hand_start_position);
				this.second_hand.attr({
					stroke : PrimeFaces.widget.AnalogClock.colorSchemes[this.colorScheme].second_hand,
					"stroke-width" : this.dimensions.second_hand_stroke_width
				});
				
				var pin = this.canvas.circle(this.dimensions.half, this.dimensions.half, this.dimensions.pin_width);
				pin.attr("fill", PrimeFaces.widget.AnalogClock.colorSchemes[this.colorScheme].pin);

				this.update();
			},

			update : function() {
				var now = this.startTime;
				
				this.hour_hand.rotate(30 * now.hours() + (this.startTime.minutes() / 2.5), this.dimensions.half, this.dimensions.half);
				this.minute_hand.rotate(6 * this.startTime.minutes(), this.dimensions.half, this.dimensions.half);
				this.second_hand.rotate(6 * this.startTime.seconds(), this.dimensions.half, this.dimensions.half);

				this.startTime.add('s', 1);
			}

		});

PrimeFaces.widget.AnalogClock.colorSchemes = {
	standard : {
		face:'#f5f5f5',
		border:'#444444',
		hour_signs: '#000000',
		hour_hand: '#444444',
		minute_hand: '#444444',
		second_hand: '#444444',
		pin: '#000000'
		
	},
	all_black : {
		face:'#000000',
		border:'#000000',
		hour_signs: '#000000',
		hour_hand: '#FFFFFF',
		minute_hand: '#FFFFFF',
		second_hand: '#FFFFFF',
		pin: '#FFFFFF'
		
	}
}

PrimeFaces.widget.AnalogClock.Dimensions = function(size){
	this.size = size;
	
	this.half = Math.floor(size/2);
	
	this.clock_width = Math.floor(size * 47.5 / 100);
	this.hour_sign_min_size = Math.floor(size * 40 / 100);
	this.hour_sign_max_size = Math.floor(size * 45 / 100);
	this.hour_sign_stroke_width = Math.floor(size * 0.5 / 100) || 1;
	
	this.hour_hand_start_position = Math.floor(size/4);
	this.hour_hand_stroke_width = Math.floor(size * 3 / 100) || 1;
	
	this.minute_hand_start_position = Math.floor(size/5);
	this.minute_hand_stroke_width = Math.floor(size * 2 / 100) || 1;
	
	this.second_hand_start_position = Math.floor(size * 12.5 / 100);
	this.second_hand_stroke_width = Math.floor(size * 1 / 100) || 1;
	
	this.pin_width = Math.floor(size * 2.5 / 100);
}