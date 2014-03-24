/**************************************************************************************************************
* Copyright (c) 2012, Goincompany.com
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*     * Redistributions of source code must retain the above copyright
*       notice, this list of conditions and the following disclaimer.
*     * Redistributions in binary form must reproduce the above copyright
*       notice, this list of conditions and the following disclaimer in the
*       documentation and/or other materials provided with the distribution.
*     * Neither the name of the DRINKS Toolkit nor the
*       names of its contributors may be used to endorse or promote products
*       derived from this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY Goincompany.com ``AS IS'' AND ANY
* EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL Goincompany.com BE LIABLE FOR ANY
* DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 To get sources and documentation, please visit:

		http://www.goincompany.com/drinks.php

***************************************************************************************************************/
function Knob(element){

	Knob.inherits(MoveInput);
	MoveInput.call(this, element);

	this.radius=parseInt(element.getAttribute("radius")) || 100;
	this.angle = 0;
	var x=0;
	var y=0;
	var pen = this.pen;
	this.rad = this.radius/100;

	this.setHeight(this.radius*2+this.radius/2+120*this.rad);
	this.setWidth(this.radius*2+this.radius/2+120*this.rad);

	this.decx = this.width/2;
	this.decy = this.height/2;
	this.x_root=1;
	this.y_root=1;

	var main_grd, grd;

	var createGradients = function(){
		main_grd = pen.createLinearGradient(-180*this.rad, -180*this.rad, this.radius, 0);
		main_grd.addColorStop(0, '#bbb');
		main_grd.addColorStop(0.5, '#eee');
		main_grd.addColorStop(1, '#bbb');
		grd = pen.createRadialGradient(50*this.rad, 50*this.rad, 25*this.rad, 50*this.rad, 50*this.rad, this.radius);
		grd.addColorStop(0, '#aaa');
		grd.addColorStop(0.5, '#ddd');
		grd.addColorStop(1, '#aaa');
	}.bind(this);
	createGradients();

	this.__defineSetter__("radius", function(radius){
		this._radius = radius;
		this.rad = radius/100;
		this.setHeight(radius*2+radius/2+120*this.rad);
		this.setWidth(radius*2+radius/2+120*this.rad);
		this.decx = this.width/2;
		this.decy = this.height/2;
		if(this.radius)
			createGradients();
	});

	this.__defineGetter__("radius", function(){
		return this._radius;
	 });
	this.radius=parseInt(element.getAttribute("radius")) || 100;

	this.drawKnob = function(){
		pen.beginPath();
		pen.fillStyle=main_grd;
		pen.strokeStyle = main_grd;
		pen.lineWidth = 7*this.rad;
		pen.save();
		pen.shadowOffsetX =1*this.rad;
		pen.shadowOffsetY = 1*this.rad;
		pen.shadowColor = "#999";
		pen.shadowBlur = 2*this.rad;
		pen.lineWidth = 15*this.rad;
		pen.translate(this.width/2, this.height/2);
		pen.arc(0, 0, this.radius, (Math.PI/180)*0, (Math.PI/180)*360, false);
		pen.stroke();
		pen.fill();
		pen.closePath();
		pen.restore();
	}

	this.drawValue = function(){
		if(!isNaN(x) && !isNaN(y)){
			pen.save();
			pen.translate(this.width/2, this.height/2);
			pen.translate(x, y);
			pen.beginPath();
			pen.lineWidth = 5*this.rad;
			pen.shadowBlur = 2*this.rad;
			pen.fillStyle=grd;
			pen.arc(0, 0, 15*this.rad, (Math.PI/180)*0, (Math.PI/180)*360, false);
			pen.stroke();
			pen.fill();
			pen.closePath();
			pen.restore();
		}
	}

	this.setCoordsByAngle = function(ang){
		x = (75*this.rad)*Math.cos(ang*Math.PI/180);
		y = (75*this.rad)*Math.sin(ang*Math.PI/180);
		this.x_root = x;
		this.y_root = y;
		this.angle=360-ang;

	}

	this.getCoords = function(){
		if((this.x_root>0 && this.y_root>0) || (this.x_root>0 && this.y_root<0)){
			x = (75*this.rad)*Math.cos(Math.atan(this.y_root/this.x_root));
			y = (75*this.rad)*Math.sin(Math.atan(this.y_root/this.x_root));
		}
		if((this.x_root<0 && this.y_root>0)||(this.x_root<0 && this.y_root<0)){
			x = -(75*this.rad)*Math.cos(Math.atan(this.y_root/this.x_root));
			y = -(75*this.rad)*Math.sin(Math.atan(this.y_root/this.x_root));
		}

		if((this.x_root>=0 && this.y_root<=0) || (this.x_root<=0 && this.y_root<=0))
			this.angle = Math.acos(x /(75*this.rad)) * 180.0 / Math.PI;

		if(this.x_root<0 && this.y_root>0)
			this.angle = 180 + (Math.asin( y /(75*this.rad)) * 180.0 / Math.PI);

		if(this.x_root>0 && this.y_root>0)
			this.angle = 360 - (Math.asin( y /(75*this.rad)) * 180.0 / Math.PI);
	}


	this.knobCommonOperations = function(){
		this.moveInputCommonOperations();
	}.bind(this);
}

function DigitalKnob(element){
	DigitalKnob.inherits(DigitalInput);
	DigitalInput.call(this, element);
	DigitalKnob.inherits(Knob);
	Knob.call(this, element);

	this.options=new Array();
	var alpha_options=new Array();
	var pen = this.pen;
	this.selectedIndex = 0;
	var selected=0;	
	var values = new Array();

	this.move_func = function(){
		this.flag = true;
	};

	this.click_func = function(){
		this.flag = true;
	};

	for(var i=0; i<element.childNodes.length; i++){
		if(element.childNodes[i].nodeName.toLowerCase()=="option"){
			this.options.push({value: element.childNodes[i].getAttribute("value"), inner:element.childNodes[i].innerHTML});
			values[element.childNodes[i].getAttribute("value")]=this.options.length-1;
			if(element.childNodes[i].getAttribute("selected")!=null){
				selected = this.options.length-1;
			}
		}
	}

	var inc;
	var drawGrid = function(){
		var alpha=150;
		inc = 260/this.options.length;
		pen.fillStyle="#333";
		pen.strokeStyle="#333";
		var cont=0;
		for(var i in this.options){
			var len = this.options[i].inner.length;
			var font_ratio = len>5?len:5;		
			pen.font='italic'+' '+'bolder'+' '+(16*this.rad)*5/font_ratio+'px'+' '+'sans';
			pen.save();
			pen.translate(this.width/2, this.height/2);
			pen.moveTo(Math.cos(alpha*Math.PI/180)*(this.radius+20*this.rad), Math.sin(alpha*Math.PI/180)*(this.radius+20*this.rad));
			pen.lineTo(Math.cos(alpha*Math.PI/180)*(this.radius+30*this.rad), Math.sin(alpha*Math.PI/180)*(this.radius+30*this.rad));
			var sx = 1;
			var sy = 0;
			var sign=1;
			if(alpha>310){
				sx=0;
				sy=0;
			}
			if(alpha<=180){
				sx=2;
			}
			
			pen.translate(Math.cos(alpha*Math.PI/180)*(this.radius+40*this.rad)-pen.measureText(this.options[i].inner).width/2*sx, Math.sin(alpha*Math.PI/180)*(this.radius+40*this.rad)+pen.measureText(this.options[i].inner).width/2*sy);
			pen.fillText(this.options[i].inner, 0, 0);
			alpha_options[i] = alpha;		
			alpha+=inc;		
			values[this.options[i].value]=cont++;	
			pen.stroke();
			pen.restore();
		}
		alpha_options.sort();		
	}.bind(this);
	drawGrid();

	this.__defineSetter__("value", function(value){
		if(!this.f){
			this.f=true;
			if(values[value] || values[value]==0){
				this._value = value;
				var alp = values[value];
				this._angle = alpha_options[alp];
				this.setCoordsByAngle(this._angle);
				this._selectedIndex = alp;
			}
			this.f=false;
		}
	});	

	this.__defineGetter__("value", function(){
			return this._value;
	 });

	this.__defineSetter__("angle", function(angle){
		if(!this.f){
			this.f=true;
			this._angle = angle;
			var alp = Math.round(((360-angle)-alpha_options[0])/(inc)); 
			if(alp<0)
				alp = Math.round(((360+(360-angle))-alpha_options[0])/((alpha_options[alpha_options.length-1]-alpha_options[0])/(alpha_options.length-1)));
			if(((360-angle)<150 && (360-angle)>45) || ((360-angle)<150 && (angle)>150+alpha_options.length*inc))		
				this.setCoordsByAngle(alpha_options[0]);
			else
				this.setCoordsByAngle(alpha_options[alp]);
			this.f=false;
			if(alp>=0 && alp<alpha_options.length){
				this.value = this.options[alp].value;
				this._selectedIndex = alp;
			}
		}
	});
	
	this.__defineGetter__("angle", function(){
			return this._angle;
	 });
	

	this.__defineSetter__("selectedIndex", function(selectedIndex){
		this._selectedIndex = selectedIndex;	
		this.value = this.options[selectedIndex].value;
	});

	this.__defineGetter__("selectedIndex", function(){
			return this._selectedIndex;
	 });

	this.selectedIndex = selected;

	this.value = this.prec_value = element.getAttribute("value") || this.options[this.selectedIndex].value; 

	this.render = function(){
		this.canvas.width = this.canvas.width;
		this.knobCommonOperations();
		this.digitalInputCommonOperations();
		drawGrid();
		this.drawKnob();
		if(this.flag)
			this.getCoords();
		this.value=this.value;
		this.flag=false;
		this.drawValue();
		
	}
	
}

function AnalogKnob(element){
	AnalogKnob.inherits(AnalogInput);
	AnalogInput.call(this, element);
	AnalogKnob.inherits(Knob);
	Knob.call(this, element);

	this.analogKnobCommonOperations = function(){
		this.analogInputCommonOperations();
		this.knobCommonOperations();
	}
	
}

function EndedKnob(element){
	EndedKnob.inherits(AnalogKnob);
	AnalogKnob.call(this, element);
	
	var pen = this.pen;
	var precision = this.precision;
	this.divisions = element.getAttribute("divisions") || 100;

	var drawGrid = function(){
		var alpha = 130, ang_step=130;
		precision = this.precision;
		for(var i = 0; i <= this.divisions; i++){
			var inset;
			var num = Math.round((this.min_range+(this.max_range-this.min_range)*i/this.divisions)*precision)/precision; 
			if(i % 5 == 0 || this.divisions/10<1){
				if(i % 10 == 0 || this.divisions/10<1){
					var len = this.max_range.toString().length>=this.min_range.toString().length?this.max_range.toString().length:this.min_range.toString().length;
					if(precision>1)
						len = precision.toString().length+1;
					var font_ratio = len>5?len:5;
					inset = 0.1 * this.radius;
					pen.lineWidth=3*this.rad;
					pen.font='italic'+' '+'bolder'+' '+(16*this.rad)*5/font_ratio+'px'+' '+'sans';
					pen.fillStyle='#000';
					var sx = 1;
					var sy = 0;
					if(alpha>310){
						sx=0;
						sy=0;
					}
					if(alpha<=210){
						sx=1.5;
					}
					pen.save();
					pen.translate(Math.cos(alpha*Math.PI/180)*(this.radius+45*this.rad)-pen.measureText(num).width/2*sx, Math.sin(alpha*Math.PI/180)*(this.radius+45*this.rad)+pen.measureText(num).width/2*sy);
					pen.fillText(num, 0, 0);
					pen.restore();
					if(this.divisions/10<1)
						alpha+=280/(this.divisions);
					else
						alpha+=280/(this.divisions/10);
					} else {
						inset = 0.08 * this.radius;
			       			pen.lineWidth=0.8;
					}
				} else {
					inset = 0.05 * this.radius;
			       		pen.lineWidth=0.5;
		       		}

				pen.strokeStyle='black';			
				pen.beginPath();
				pen.moveTo(((this.radius+20*this.rad) + inset) * Math.cos((360-ang_step)*Math.PI / 180), -(((this.radius+20*this.rad) + inset) * Math.sin((360-ang_step)*Math.PI / 180)));
				pen.lineTo((this.radius+20*this.rad) * Math.cos((360-ang_step)*Math.PI / 180), -((this.radius+20*this.rad) * Math.sin((360-ang_step)*Math.PI / 180)));
				ang_step += 280/this.divisions;
		 	      	pen.stroke();
				pen.closePath();       	
			}
	}.bind(this);
	this.setCoordsByAngle(130);

	this.__defineSetter__("value", function(value){
		if(!this.f){
			this.f=true;
			this._value = value;
			precision = this.precision;
			this._angle = Math.round((((value - this.min_range)/((this.max_range-this.min_range)/280))+130)*precision)/precision;
			this.setCoordsByAngle(this._angle);
			this.f=false;
		}
	});	

	this.__defineGetter__("value", function(){
			return this._value;
	 });

	this.__defineSetter__("angle", function(angle){
		if(!this.f){
			this.f=true;
			this._angle = angle;
			if(angle<315 && angle>225)
				this.setCoordsByAngle(130);
			var ang = 360-angle;
			if(((360-angle)-130)<0){
				ang = 360+(360-angle);
			}
			precision = this.precision;
			this._value=Math.round((this.min_range+((ang-130)*(this.max_range-this.min_range))/(280))*precision)/precision;
			this.f=false;
		}
	});
	
	this.__defineGetter__("angle", function(){
			return this._angle;
	 });

	this.value = this.prec_value = element.getAttribute("value") || this.min_range; 
	this.render = function(){
		this.canvas.width = this.canvas.width;
		this.drawKnob();
		pen.save();
		pen.translate(this.width/2, this.height/2);
		if(!this.hide_grid)
			drawGrid();
		pen.restore();
		this.getCoords();
		this.drawValue();
		this.analogKnobCommonOperations();	
	}


}


function LoopKnob(element){
	LoopKnob.inherits(AnalogKnob);
	AnalogKnob.call(this, element);

	var pen = this.pen;
	var old_angle=0;
	this.step = parseFloat(element.getAttribute("step")) || 1;

	this.render = function(){
		this.canvas.width = this.canvas.width;
		this.drawKnob();
		this.getCoords();
		var sign = (this.step.toString().length-1) - this.step.toString().indexOf('.');
		if (old_angle - this.angle > -300)
			 if((old_angle < this.angle) || (this.angle - old_angle < -300)){
					if((this.value-this.step)>=this.min_range && (this.value-this.step) <= this.max_range)
						this.value=Math.round((this.value - this.step)*10*sign)/(10*sign);
			}
			else if((old_angle > this.angle)  || (old_angle - this.angle < -300)){
					if((this.value+this.step)>=this.min_range && (this.value+this.step) <= this.max_range)
						this.value=Math.round((this.value + this.step)*10*sign)/(10*sign);
			}
		
		if(old_angle != this.angle) old_angle = this.angle;

		this.drawValue();
		this.analogKnobCommonOperations();		

	}

}
