PrimeFaces.widget.AnalogClock = PrimeFaces.widget.BaseWidget.extend({
			init : function(cfg) {

				this._super(cfg);

				this.startTime = cfg.time && cfg.mode !== 'client' ? moment(cfg.time) : moment();

				this.colorTheme = cfg.colorTheme || 'aristo';
				
				this.themeObject = cfg.themeObject ? JSON.parse(cfg.themeObject) : PrimeFaces.widget.AnalogClock.colorThemes[this.colorTheme];
				
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
					"fill" : this.themeObject.face,
					"stroke" :this.themeObject.border,
					"stroke-width" : "5"
				})
				
				this.draw_hour_signs();
				
				this.draw_hands();
				
				var pin = this.canvas.circle(this.dimensions.half, this.dimensions.half, this.dimensions.pin_width);
				pin.attr("fill", this.themeObject.pin);

				this.update();
			},
			
			draw_hour_signs: function(){
				var hour_sign;
				
				for (i = 0; i < 12; i++) {
					
					var start_x = this.dimensions.half + Math.round(this.dimensions.hour_sign_min_size * Math.cos(30 * i	* Math.PI / 180));
					var start_y = this.dimensions.half + Math.round(this.dimensions.hour_sign_min_size * Math.sin(30 * i * Math.PI / 180));
					var end_x = this.dimensions.half + Math.round(this.dimensions.hour_sign_max_size * Math.cos(30 * i * Math.PI	/ 180));
					var end_y = this.dimensions.half + Math.round(this.dimensions.hour_sign_max_size * Math.sin(30 * i * Math.PI	/ 180));
					
					hour_sign = this.canvas.path("M" + start_x + " " + start_y	+ "L" + end_x + " " + end_y);
					hour_sign.attr({
						"stroke":this.themeObject.hourSigns,
						"stroke-width" : this.dimensions.hour_sign_stroke_width
					});
				}
			},
			
			draw_hands: function(){
				
				this.hour_hand = this.canvas.path("M" + this.dimensions.half + " " + this.dimensions.half	+ "L" + this.dimensions.half + " " + this.dimensions.hour_hand_start_position);
				this.hour_hand.attr({
					stroke : this.themeObject.hourHand,
					"stroke-width" : this.dimensions.hour_hand_stroke_width
				});
				
				this.minute_hand = this.canvas.path("M" + this.dimensions.half + " " + this.dimensions.half	+ "L" + this.dimensions.half + " " + this.dimensions.minute_hand_start_position);
				this.minute_hand.attr({
					stroke : this.themeObject.minuteHand,
					"stroke-width" : this.dimensions.minute_hand_stroke_width
				});
				
				this.second_hand = this.canvas.path("M" + this.dimensions.half + " " + this.dimensions.half	+ "L" + this.dimensions.half + " " + this.dimensions.second_hand_start_position);
				this.second_hand.attr({
					stroke : this.themeObject.secondHand,
					"stroke-width" : this.dimensions.second_hand_stroke_width
				});
			},

			update : function() {
				var now = this.startTime;
				
				this.hour_hand.rotate(30 * now.hours() + (this.startTime.minutes() / 2.5), this.dimensions.half, this.dimensions.half);
				this.minute_hand.rotate(6 * this.startTime.minutes(), this.dimensions.half, this.dimensions.half);
				this.second_hand.rotate(6 * this.startTime.seconds(), this.dimensions.half, this.dimensions.half);

				this.startTime.add('s', 1);
			}

		});

PrimeFaces.widget.AnalogClock.colorThemes = {
	aristo : {
		face:'#f5f5f5',
		border:'#444444',
		hourSigns: '#000000',
		hourHand: '#444444',
		minuteHand: '#444444',
		secondHand: '#444444',
		pin: '#000000'
		
	},
	black : {
		face:'#000000',
		border:'#000000',
		hourSigns: '#000000',
		hourHand: '#FFFFFF',
		minuteHand: '#FFFFFF',
		secondHand: '#FFFFFF',
		pin: '#FFFFFF'
		
	},
	blue : {
		face:'#1B1BB3',
		border:'#7373D9',
		hourSigns: '#090974',
		hourHand: '#FFFFFF',
		minuteHand: '#FFFFFF',
		secondHand: '#FFFFFF',
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