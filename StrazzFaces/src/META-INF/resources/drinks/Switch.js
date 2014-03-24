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

function ToggleButton(element){
	ToggleButton.inherits(BinaryInput);
	BinaryInput.call(this, element);

	this.toggleButtonCommonOperations = function(){
		this.binaryInputCommonOperations();
	}
}

function RockerSwitch(element){
	RockerSwitch.inherits(ToggleButton);
	ToggleButton.call(this, element);

	var pen = this.pen;
	if(this.width==null)
		this.setWidth(150);
	if(this.height==null)
		this.setHeight(200);
	
	var grd, grd2, grd3, grd4, grd5, grd6, main_grd;
	this.wx = this.width/150;
	this.hx = this.height/200;
	var createGradients = function(){
		grd = pen.createLinearGradient(0, this.height/8, 0, 175*this.hx);
		grd.addColorStop(0, '#999');
		grd.addColorStop(1, '#666');
		grd2 = pen.createLinearGradient(0, -3*this.height/8, 0, 75*this.hx);
		grd2.addColorStop(0, '#666');
		grd2.addColorStop(1, '#999');
		grd3 = pen.createLinearGradient(0, -30*this.hx, 0, 0);
		grd3.addColorStop(0, '#bbb');
		grd3.addColorStop(1, '#888');
		grd4 = pen.createLinearGradient(0, -10*this.hx, 0, 20*this.hx);
		grd4.addColorStop(0, '#888');
		grd4.addColorStop(1, '#bbb');
		grd5 = pen.createLinearGradient(0, 0, 0, 80*this.hx);
		grd5.addColorStop(0, '#555');
		grd5.addColorStop(1, '#444');
		grd6 = pen.createLinearGradient(0, -2*this.height/5, 0, 0);
		grd6.addColorStop(0, '#555');
		grd6.addColorStop(1, '#999');
		main_grd = pen.createLinearGradient(-this.width/6, -this.height/2, -this.width/6, this.height/2);
		main_grd.addColorStop(0, '#bbb');
		main_grd.addColorStop(0.5, '#eee');
		main_grd.addColorStop(1, '#bbb');
	}.bind(this);
	createGradients();
	var color1;
	var color2;

	var updateColor = function(){
		if(this.value==0){
			color1=grd3;
			color2=grd2;
		}
		else{
			color1=grd4;
			color2=grd4;
		}	
	}.bind(this);
	updateColor();

	var clickHandler = function(event){
		var obj = getRelativeCoordinates(event, this.canvas);
		if(this.value==0 && isOverOn(obj)){
			this.on();
		}else if(this.value==1 && isOverOff(obj)){
			this.off();
		}
		updateColor();
	}.bind(this);

	var isOverOff = function(obj){
		if(obj.x>=this.width/3 && obj.x<=(2*this.width)/3 && obj.y>=this.height/8 && obj.y<=this.height/2)
			return true;
		else
			return false;
	}.bind(this);

	var isOverOn = function(obj){
		if(obj.x>=this.width/3 && obj.x<=(2*this.width)/3 && obj.y>=this.height/2 && obj.y<=175*this.hx)
			return true;
		else
			return false;
	}.bind(this);

	var isOver = function(obj){
		if(obj.x>=this.width/3 && obj.x<=(2*this.width)/3 && obj.y>=this.height/8 && obj.y<=175*this.hx)
			return true;
		else
			return false;
	}.bind(this);

	var mouseHandler = function(event){
		var obj = getRelativeCoordinates(event, this.canvas);
		if(isOver(obj))
			this.canvas.style.cursor = 'pointer';
		else
			this.canvas.style.cursor = 'auto';
	}.bind(this);

	if(this.canvas){
		this.canvas.addEventListener("mousedown", function(event){if(event.button==0)clickHandler(event);}, false);
		this.canvas.addEventListener("mousemove", function(event){mouseHandler(event);}, false);
	}

	this.drawBackground = function(){
		pen.beginPath();
		pen.moveTo(-this.width/6, -85*this.hx);
		pen.lineTo(this.width/6, -85*this.hx);
		pen.quadraticCurveTo(35*this.wx, -2*this.height/5, 35*this.wx, -3*this.height/8);
		pen.lineTo(35*this.wx, 75*this.hx);
		pen.quadraticCurveTo(35*this.wx, 85*this.hx, this.width/6, 85*this.hx);
		pen.lineTo(-this.width/6, 85*this.hx);
		pen.quadraticCurveTo(-35*this.wx, 80*this.hx, -35*this.wx,  75*this.hx);
		pen.lineTo(-35*this.wx, -3*this.height/8);
		pen.quadraticCurveTo(-35*this.wx, -2*this.height/5, -this.width/6, -85*this.hx);
		pen.closePath();
		pen.stroke();
		pen.fill();
	}

	this.drawButton = function(){
		pen.beginPath();
		pen.moveTo(5*this.wx, -3*this.height/8);
		pen.lineTo(15*this.wx, -3*this.height/8);
		pen.quadraticCurveTo(this.width/6, -3*this.height/8, this.width/6, -65*this.hx);
		pen.lineTo(this.width/6, 65*this.hx);
		pen.quadraticCurveTo(this.width/6, 75*this.hx, 15*this.wx, 75*this.hx);
		pen.lineTo(-15*this.wx, 75*this.hx);
		pen.quadraticCurveTo(-this.width/6,  75*this.hx, -this.width/6, 65*this.hx);
		pen.lineTo(-this.width/6, -65*this.hx);
		pen.quadraticCurveTo(-this.width/6, -3*this.height/8, -15*this.wx, -3*this.height/8);
		pen.closePath();
		pen.stroke();
		pen.fill();
	}

	this.drawUpShadow = function(){
		pen.beginPath();
		pen.moveTo(this.width/6, 0);
		pen.lineTo(27*this.wx, -70*this.hx);
		pen.quadraticCurveTo(27*this.wx, -2*this.height/5, 20*this.wx, -2*this.height/5);
		pen.lineTo(-18*this.wx, -2*this.height/5);
		pen.quadraticCurveTo(-27*this.wx, -3*this.height/8, -this.width/6, -65*this.hx);
		pen.quadraticCurveTo(-this.width/6, -3*this.height/8, -15*this.wx, -3*this.height/8);
		pen.lineTo(15*this.wx, -3*this.height/8);
		pen.quadraticCurveTo(this.width/6, -3*this.height/8, this.width/6, -65*this.hx);
		pen.lineTo(this.width/6, 0);
		pen.closePath();
		pen.fill();
	}
	
	this.drawDownShadow = function(){
		pen.beginPath();
		pen.moveTo(this.width/6, 0);
		pen.lineTo(27*this.wx, 65*this.hx);
		pen.quadraticCurveTo(27*this.wx, 75*this.hx, 20*this.wx, 80*this.hx);
		pen.lineTo(-20*this.wx, 80*this.hx);
		pen.quadraticCurveTo(-this.width/6, 75*this.hx, -this.width/6, 65*this.hx);
		pen.quadraticCurveTo(-this.width/6, 75*this.hx, -15*this.wx, 75*this.hx);
		pen.lineTo(15*this.wx, 75*this.hx);
		pen.quadraticCurveTo(this.width/6, 75*this.hx, this.width/6, 65*this.hx);
		pen.lineTo(this.width/6, 0);
		pen.closePath();
		pen.fill();
	}
	
	this.drawOnOffSimbols = function(){
		pen.beginPath();
		pen.arc(0, -50*this.hx, this.width/15,  (Math.PI/180)*0, (Math.PI/180)*360);
		pen.closePath();
		pen.stroke();
		pen.strokeStyle=grd2;
		pen.beginPath();
		pen.moveTo(0, 30*this.hx);
		pen.lineTo(0, 50*this.hx);
		pen.stroke();
		pen.closePath();
	}

	this.__defineSetter__("width", function(width){
		this._width = width;
		this.canvas.width=width;
		this.container.style.width = width+'px';
		this.wx= width/150;
		if(this.height && this.width){
			createGradients();
			updateColor();
		}
	});
	
	this.__defineGetter__("width", function(){
		return this._width;
	 });

	this.__defineSetter__("height", function(height){
		this._height = height;
		this.canvas.height=height;
		this.container.style.height = height+'px';
		this.hx= height/200;
		if(this.height && this.width){
			createGradients();
			updateColor();
		}
	});
	
	this.__defineGetter__("height", function(){
		return this._height;
	 });
	
	this.width = this.canvas.width;
	this.height = this.canvas.height;

	this.render = function(){
		this.canvas.width = this.canvas.width;
		this.toggleButtonCommonOperations();
		pen.save();
		updateColor();
		pen.translate(this.width/2, this.height/2);
		pen.fillStyle=main_grd;		
		pen.strokeStyle=main_grd;
		pen.shadowOffsetX = 0;
		pen.shadowOffsetY = 1.5*this.wx;
		pen.shadowColor = '#999';
		pen.shadowBlur = 3*this.wx;
		this.drawBackground();
		pen.restore();
		pen.translate(this.width/2, this.height/2);
		if(!this.value){
			pen.strokeStyle=grd;
		}
		else{
			pen.strokeStyle=grd2;
		}
		pen.lineWidth=3;
		pen.fillStyle=color1;
		this.drawButton();
		pen.save();
		if(this.value){
			pen.fillStyle=grd6;
			pen.lineWidth=3;
			this.drawUpShadow();
		}
		if(!this.value){
			pen.fillStyle=grd5;
			pen.lineWidth=3;
			this.drawDownShadow();
		}
		pen.restore();
		pen.save();
		pen.lineWidth=3*this.wx;
		pen.shadowOffsetX = 0;
		pen.shadowOffsetY = 0;
		pen.shadowColor = "#222";
		pen.shadowBlur = 3*this.wx;
		pen.strokeStyle=grd;
		this.drawOnOffSimbols();
		pen.restore();
	}



}

function ArcSwitch(element){
	
	ArcSwitch.inherits(ToggleButton);
	ToggleButton.call(this, element);

	var pen = this.pen;
	if(this.width==null)
		this.setWidth(70);
	if(this.height==null)
		this.setHeight(70);
	var color; 
	var shadow;
	var border;

	var grd, grd1;
	var createGradients = function(){
		grd = pen.createLinearGradient(0, -this.height/2, 0, this.height/2);
		grd.addColorStop(0, '#eee');
		grd.addColorStop(1, '#999');
		grd1 = pen.createLinearGradient(0, -this.height/2, 0, 3*this.height/4);
		grd1.addColorStop(0, '#eee');
		grd1.addColorStop(1, '#bbb');
	}.bind(this);
	createGradients();

	var updateColor = function(){
		if(this.value==0){
			color = grd1;
			shadow = 1;			
			border = "#bbb";			
		}
		else{
			color = grd;
			shadow = 0;
			border = "#444";			
		}	
	}.bind(this);
	updateColor();


	var clickHandler = function(event){
		this.toggle();
		updateColor();
	}.bind(this);

	if(this.canvas){
		this.canvas.addEventListener("mousedown", function(event){if(event.button==0)clickHandler(event);}, false);
		this.canvas.style.cursor="pointer";
	}

	this.__defineSetter__("width", function(width){
		this._width = width;
		this.canvas.width=width;
		this.container.style.width = width+'px';
		if(this.height && this.width){
			createGradients();
			updateColor();
		}
	});
	
	this.__defineGetter__("width", function(){
		return this._width;
	 });

	this.__defineSetter__("height", function(height){
		this._height = height;
		this.canvas.height=height;
		this.container.style.height = height+'px';
		if(this.height && this.width){
			createGradients();
			updateColor();
		}
	});
	
	this.__defineGetter__("height", function(){
		return this._height;
	 });
	
	this.width = this.canvas.width;
	this.height = this.canvas.height;

	this.drawButton = function(){
		pen.beginPath();
		pen.moveTo(-3*this.width/8, -this.height/8);
		pen.quadraticCurveTo(0, -this.height/3, 3*this.width/8, -this.height/8);
		pen.quadraticCurveTo(this.width/2+10*this.width/200, 0, 3*this.width/8, this.height/8);
		pen.quadraticCurveTo(0, this.height/3, -3*this.width/8, this.height/8);
		pen.quadraticCurveTo(-this.width/2-10*this.width/200, 0, -3*this.width/8, -this.height/8);
		pen.closePath();
		pen.stroke();
		pen.fill();
	}

	this.render = function(){
		this.canvas.width = this.canvas.width;
		this.toggleButtonCommonOperations();
		pen.save();	
		updateColor();
		pen.fillStyle=color;
		pen.strokeStyle=border;
		pen.translate(this.width/2, this.height/2);
		pen.shadowOffsetX = shadow;
		pen.shadowOffsetY = shadow;
		pen.shadowColor = '#777';
		pen.shadowBlur = 5;
		this.drawButton();
		pen.restore();	
	}

}

function SideSwitch(element){

	SideSwitch.inherits(ToggleButton);
	ToggleButton.call(this, element);
	SideSwitch.inherits(MoveInput);
	MoveInput.call(this, element);

	var pen = this.pen;
	if(this.width==null)
		this.setWidth(100);
	if(this.height==null)
		this.setHeight(50);
	this.hx = this.height/50;
	this.wx = this.width/100;

	var grd, grd2, grd3, grd4;
	var createGradients = function(){
		grd = pen.createLinearGradient(this.width/4, 0, this.width/4, this.height);
		grd.addColorStop(0, '#ddd');
		grd.addColorStop(1, '#666');
		grd2 = pen.createLinearGradient(this.width/2, 0, this.width/2, this.height);
		grd2.addColorStop(0, '#666');
		grd2.addColorStop(1, '#ddd');
		grd3 = pen.createLinearGradient(0, 0, this.width-10*this.wx, this.height+30*this.hx);
		grd3.addColorStop(0, '#333');
		grd3.addColorStop(1, '#fff');
		grd4 = pen.createLinearGradient(0, -5*this.hx, 0, 10*this.hx);
		grd4.addColorStop(0, '#666');
		grd4.addColorStop(1, '#ddd');
	}.bind(this);
	createGradients();

	var switchValue = function(){
		if(this.x_root<this.width/2){
			this.x_root=2*this.wx;
			this.off();
		}
		if(this.x_root>=this.width/2){
			this.x_root=this.width-4*this.wx;
			this.on();
		}
	}.bind(this);
	
	this.__defineSetter__("width", function(width){
		this._width = width;
		this.canvas.width=width;
		this.container.style.width = width+'px';
		this.wx = width/100;
		if(this.height && this.width)
			createGradients();
	
	});
	
	this.__defineGetter__("width", function(){
		return this._width;
	 });

	this.__defineSetter__("value", function(value){
		this._value = value;
		if(value==1)
			this.x_root = this.width-4*this.wx-this.width/4;
		else
			this.x_root = 2*this.wx+this.width/4;
	});
	
	this.__defineGetter__("value", function(){
		return this._value;
	 });

	this.__defineSetter__("height", function(height){
		this._height = height;
		this.canvas.height=height;
		this.container.style.height = height+'px';
		this.hx = height/50;
		if(this.height && this.width)
			createGradients();
	});
	
	this.__defineGetter__("height", function(){
		return this._height;
	 });
	
	this.width = this.canvas.width;
	this.height = this.canvas.height;

	this.up_func = switchValue;
	this.value = element.getAttribute("value")!=null?parseFloat(element.getAttribute("value")):(parseFloat(element.getAttribute("min_range")) || 0);
	this.render = function(){
		this.canvas.width = this.canvas.width;
		this.moveInputCommonOperations();
		this.toggleButtonCommonOperations();
		pen.save();
		pen.translate(this.width/2, this.height/2);
		pen.restore();
		pen.beginPath();
		pen.lineWidth=3*this.wx;
		pen.strokeStyle=grd3;
		pen.roundRect(2*this.wx, 2*this.hx, this.width-4*this.wx, this.height-4*this.hx, 10*this.wx);
		pen.fillStyle=grd2;
		pen.stroke();
		pen.fill();
		pen.lineWidth=1*this.wx;
		pen.save();
		pen.translate(-this.width/4, 0);
		if(this.x_root<this.width/4)
			this.x_root=2*this.wx+this.width/4;
		if(this.x_root>3*this.width/4)
			this.x_root=this.width-4*this.wx-this.width/4;
		pen.fillStyle=grd;
		pen.roundRect(this.x_root, 1*this.hx, this.width/2, this.height-3*this.hx, 5*this.wx);
		pen.shadowOffsetX = 0;
		pen.shadowOffsetY = 0;
		pen.shadowColor = '#777';
		pen.shadowBlur = 5*this.wx;
		pen.strokeStyle="#333";
		pen.stroke();
		pen.fill();
		pen.restore();
		pen.save();
		pen.translate(this.x_root, this.height/2);
		pen.fillStyle=grd4;
		pen.shadowOffsetX = 0;
		pen.shadowOffsetY = 0;
		pen.shadowColor = "#555";
		pen.shadowBlur = 1*this.wx;
		pen.beginPath();
		pen.moveTo(-2*this.wx, -5*this.hx);
		pen.lineTo(-12*this.wx, 0);
		pen.lineTo(-2*this.wx, 5*this.hx);
		pen.closePath();
		pen.stroke();
		pen.fill();
		pen.beginPath();
		pen.moveTo(2*this.wx, -5*this.hx);
		pen.lineTo(12*this.wx, 0);
		pen.lineTo(2*this.wx, 5*this.hx);
		pen.closePath();
		pen.stroke();
		pen.fill();
		pen.restore();
	}
}

function CircleSwitch(element){
	CircleSwitch.inherits(ToggleButton);
	ToggleButton.call(this, element);
	SideSwitch.inherits(MoveInput);
	MoveInput.call(this, element);

	var pen = this.pen;
	if(this.width==null)
		this.setWidth(100);
	if(this.height==null)
		this.setHeight(50);

	this.wx = this.width/100;
	this.hx = this.height/50;

	var grd, grd2, grd3, grd4;	
	var createGradients = function(){
		grd = pen.createLinearGradient(this.width/4, 0, this.width/4, this.height);
		grd.addColorStop(0, '#ddd');
		grd.addColorStop(1, '#666');
		grd2 = pen.createLinearGradient(this.width/2, 0, this.width/2, this.height);
		grd2.addColorStop(0, '#666');
		grd2.addColorStop(1, '#ddd');
		grd3 = pen.createLinearGradient(0, 0, this.width-10*this.wx, this.height+30*this.hx);
		grd3.addColorStop(0, '#333');
		grd3.addColorStop(1, '#fff');
		grd4 = pen.createLinearGradient(0, -5*this.hx, 0, 10*this.hx);
		grd4.addColorStop(0, '#666');
		grd4.addColorStop(1, '#ddd');
	}.bind(this);
	createGradients();

	var switchValue = function(){
		if(this.x_root<this.width/2){
			this.x_root=20*this.wx;
			this.off();
		}
		if(this.x_root>=this.width/2){
			this.x_root=this.width-20*this.wx;
			this.on();
		}
	}.bind(this);

	this.__defineSetter__("width", function(width){
		this._width = width;
		this.canvas.width=width;
		this.container.style.width = width+'px';
		this.wx = width/100;
		if(this.height && this.width)
			createGradients();
	
	});
	
	this.__defineGetter__("width", function(){
		return this._width;
	 });

	this.__defineSetter__("value", function(value){
		this._value = value;
		if(value==1)
			this.x_root=this.width-20*this.wx;
		else
			this.x_root=20*this.wx;
	});
	
	this.__defineGetter__("value", function(){
		return this._value;
	 });

	this.__defineSetter__("height", function(height){
		this._height = height;
		this.canvas.height=height;
		this.container.style.height = height+'px';
		this.hx = height/50;
		if(this.height && this.width)
			createGradients();
	});
	
	this.__defineGetter__("height", function(){
		return this._height;
	 });
	
	this.width = this.canvas.width;
	this.height = this.canvas.height;
	this.value = element.getAttribute("value") || 0;
	this.up_func = switchValue;

	this.render = function(){
		this.canvas.width=this.canvas.width;
		this.moveInputCommonOperations();
		this.toggleButtonCommonOperations();
		pen.save();
		pen.translate(this.width/2, this.height/2);
		pen.restore();
		pen.save();
		pen.strokeStyle=grd3;
		pen.fillStyle=grd2;
		pen.roundRect(5*this.wx, this.height/2-15*this.hx, this.width-10*this.wx, 30*this.hx, 10*this.wx);
		pen.stroke();
		pen.fill();
		pen.beginPath();
		if(this.x_root<20*this.wx)
			this.x_root=20*this.wx;
		if(this.x_root>this.width-20*this.wx)
			this.x_root=this.width-20*this.wx;
		pen.shadowOffsetX = 1*this.wx;
		pen.shadowOffsetY = 1*this.hx;
		pen.shadowColor = '#777';
		pen.shadowBlur = 3*this.wx;

		pen.arc(this.x_root, this.height/2, 18*this.wx, 0, Math.PI*2, false);
		pen.lineWidth=2*this.wx;
		pen.strokeStyle="silver";
		pen.stroke();
		pen.clip();
		process(this.canvas, angularGradient, pen, this.x_root);
		pen.restore();
	}
}

function RectSwitch(element){
	RectSwitch.inherits(ToggleButton);
	ToggleButton.call(this, element);

	var pen = this.pen;
	if(this.width==null)
		this.setWidth(100);
	if(this.height==null)
		this.setHeight(50);

	var grd, grd1;
	var color;
 
	this.wx = this.width/100;
	this.hx = this.height/50;

	var createGradients = function(){
		grd = pen.createLinearGradient(0, -this.height/2, 0, this.height/2);
		grd.addColorStop(0, '#eee');
		grd.addColorStop(1, '#999');
		grd1 = pen.createLinearGradient(0, -this.height/2, 0, 3*this.height/4);
		grd1.addColorStop(0, '#eee');
		grd1.addColorStop(1, '#bbb');
	}.bind(this);
	createGradients();

	this.text = element.getAttribute("text") || "";
	
	var updateColor = function(){
		if(this.value==0){
			color = grd1;
			shadow = 1;			
			border = "#bbb";			
		}
		else{
			color = grd;
			shadow = 0;
			border = "#444";			
		}	
	}.bind(this);
	updateColor();

	var clickHandler = function(event){
		this.toggle();
		updateColor();
	}.bind(this);

	var overHandler = function(event){
		this.canvas.style.cursor = "pointer";
	}.bind(this);

	if(this.canvas){
		this.canvas.addEventListener("mousedown", function(event){if(event.button==0)clickHandler(event);}, false);
		this.canvas.addEventListener("mouseover", function(event){overHandler(event);}, false);
	}

	this.__defineSetter__("width", function(width){
		this._width = width;
		this.canvas.width=width;
		this.container.style.width = width+'px';
		this.wx = width/100;
		this.logfactw = Math.round(Math.log(Math.E*this.wx));
		if(this.height && this.width){
			createGradients();
			updateColor();
		}
	});
	
	this.__defineGetter__("width", function(){
		return this._width;
	 });

	this.__defineSetter__("height", function(height){
		this._height = height;
		this.canvas.height=height;
		this.container.style.height = height+'px';
		this.hx = height/50;
		if(this.height && this.width){
			createGradients();
			updateColor();
		}
	});
	
	this.__defineGetter__("height", function(){
		return this._height;
	 });
	
	this.width = this.canvas.width;
	this.height = this.canvas.height;

	this.logfactw = Math.round(Math.log(Math.E*this.wx));
	this.render = function(){
		this.canvas.width = this.canvas.width;
		this.toggleButtonCommonOperations();
		pen.save();
		pen.translate(this.width/2, this.height/2);
		updateColor();
		pen.fillStyle=color;
		pen.shadowOffsetX = shadow;
		pen.shadowOffsetY = shadow;
		pen.shadowColor = '#777';
		pen.shadowBlur = 5*this.logfactw;
		pen.roundRect(-this.width/2+5*this.wx, -this.height/2+5*this.hx, this.width-10*this.wx, this.height-10*this.hx, 5*this.logfactw);
		pen.strokeStyle=border;
		pen.stroke();
		pen.fill();
		pen.font='italic'+' '+'bolder'+' '+(this.width/this.text.length)%this.height+'px'+' '+'sans';
		pen.fillStyle=grd1;
		pen.shadowOffsetX = 0;
		pen.shadowOffsetY = 0;
		pen.shadowColor = "#000";
		pen.shadowBlur = 1*this.wx;
		pen.fillText(this.text, -pen.measureText(this.text).width/2, ((this.width/this.text.length)%this.height)/4);
		pen.restore();
	}
}

function ToggleSwitch(element){

	ToggleSwitch.inherits(ToggleButton);
	ToggleButton.call(this, element);

	var pen = this.pen;
	if(this.width==null)
		this.setWidth(60);
	if(this.height==null)
		this.setHeight(60);
	this.hx = this.height/60;
	this.wx = this.width/60;		
	var main_grd, grd, grd2, grd3, grd4, grd5;
	var createGradients = function(){
		main_grd = pen.createLinearGradient(-this.width/6, -this.height/4, -this.width/6, this.height/4);
		main_grd.addColorStop(0, '#bbb');
		main_grd.addColorStop(0.5, '#eee');
		main_grd.addColorStop(1, '#bbb');
		grd = pen.createLinearGradient(-10*this.wx, 0, 15*this.wx, 0);
		grd.addColorStop(0, '#eee');
		grd.addColorStop(0.5, '#999');
		grd.addColorStop(1, '#444');
		grd2 = pen.createRadialGradient(-5*this.wx, -5*this.hx, 5*this.wx, 0, 0, 15*this.hx);
		grd2.addColorStop(0, '#ccc');
		grd2.addColorStop(0.9, '#777');
		grd3 = pen.createLinearGradient(-10*this.wx, 0, 15*this.wx, 0);
		grd3.addColorStop(0, '#eee');
		grd3.addColorStop(0.5, '#999');
		grd3.addColorStop(1, '#444');
		grd4 = pen.createRadialGradient(-5*this.wx, 3*this.hx, 5*this.hx, 0, 0, 15*this.hx);
		grd4.addColorStop(0, '#ccc');
		grd4.addColorStop(1, '#999');
		grd5 = pen.createLinearGradient(-10*this.wx, 0, 15*this.wx, 0);
		grd5.addColorStop(0, '#bbb');
		grd5.addColorStop(0.3, '#eee');
		grd5.addColorStop(1, '#222');
	}.bind(this);
	createGradients();

	var clickHandler = function(event){
		this.toggle();
	}.bind(this);

	var overHandler = function(event){
		this.canvas.style.cursor = 'pointer';	
	}.bind(this);

	if(this.canvas){
		this.canvas.addEventListener("mousedown", function(event){if(event.button==0)clickHandler(event);}, false);
		this.canvas.addEventListener("mouseover", function(event){overHandler(event);}, false);
	}

	var c1, c2, c3;
	var load = function(){
		c1 = document.createElement("canvas");
		c1.width = this.width;
		c1.height = this.height;
		var ctx1 = c1.getContext("2d");
		ctx1.translate(this.width/2, this.height/2);
		ctx1.fillStyle=grd;
		ctx1.strokeStyle="#777";
		ctx1.beginPath();
		ctx1.arc(0, 0, 5*this.hx, 0, Math.PI, true);
		ctx1.lineTo(-7*this.wx, Math.sin(Math.PI/180*135)*(20*this.hx)+5*this.hx);
		ctx1.arc(0, Math.sin(Math.PI/180*135)*(20*this.hx)+5*this.hx, 7*this.hx, 180*Math.PI/180, 0*Math.PI/180, true);
		ctx1.lineTo(7*this.wx, Math.sin(Math.PI/180*135)*(20*this.hx)+5*this.hx);
		ctx1.stroke();
		ctx1.closePath();
		ctx1.fill();
		ctx1.save();
		ctx1.translate(0, (parseFloat(Math.sin(Math.PI/180*135)*(20*this.hx))+parseFloat(5*this.hx)))
		ctx1.beginPath();
		ctx1.strokeStyle=grd3;
		ctx1.fillStyle=grd2;
		ctx1.arc(0, 0, 7*this.hx, 2*Math.PI, 0*Math.PI/180, true);
		ctx1.fill();
		ctx1.closePath();
		ctx1.restore();
		c2 = document.createElement("canvas");
		c2.width = this.width;
		c2.height = this.height;
		var ctx2 = c2.getContext("2d");
		ctx2.translate(this.width/2, this.height/2);
		ctx2.fillStyle=grd;
		ctx2.strokeStyle="#777";
		ctx2.beginPath();
		ctx2.arc(0, 0, 5*this.hx, 0*Math.PI/180, 180*Math.PI/180, false);
		ctx2.lineTo(-7*this.wx, Math.sin(Math.PI/180*225)*(30*this.hx)+5*this.hx+Math.sin(Math.PI/180*150)*(7*this.hx));
		ctx2.arc(0, Math.sin(Math.PI/180*225)*(30*this.hx)+5*this.hx, 7*this.hx, 180*Math.PI/180, 0*Math.PI/180, false);
		ctx2.closePath();
		ctx2.stroke();   	
		ctx2.fill();
		ctx2.beginPath();
		ctx2.fillStyle=grd4;
		ctx2.save();
		ctx2.translate(0, Math.sin(Math.PI/180*225)*(30*this.hx)+5*this.hx)
		ctx2.arc(0, 0, 7*this.hx, 0, 2*Math.PI/180, true);
		ctx2.fill();
		ctx2.closePath();
		ctx2.restore();
		c3 = document.createElement("canvas");
		c3.width = this.width;
		c3.height = this.height;
		var ctx3 = c3.getContext("2d");
		ctx3.translate(this.width/2, this.height/2);
		ctx3.beginPath();
		ctx3.fillStyle=grd5;
		ctx3.shadowOffsetX = 0;
		ctx3.shadowOffsetY = 0;
		ctx3.shadowColor = '#333';
		ctx3.shadowBlur = 2*this.hx;
		ctx3.arc(0, 0, 10*this.hx, 0, 2*Math.PI, false);
		ctx3.closePath();
		ctx3.fill();	
	}.bind(this);

	this.__defineSetter__("width", function(width){
		this._width = width;
		this.canvas.width=width;
		this.container.style.width = width+'px';
		this.wx = width/60;
		if(this.height && this.width){
			createGradients();
			load();
		}
	});
	
	this.__defineGetter__("width", function(){
		return this._width;
	 });

	this.__defineSetter__("height", function(height){
		this._height = this.width;
		if(height>this.width){
			this.canvas.height=height;
			this.container.style.height = height+'px';
		}else{
			this.canvas.height=this._height;
			this.container.style.height = this._height+'px';
		}
		this.hx = this._height/60;
		if(this.height && this.width){
			createGradients();
			load();
		}
	});
	
	this.__defineGetter__("height", function(){
		return this._height;
	 });
	
	this.width = this.canvas.width;
	this.height = this.canvas.height;
	this.render = function(){
		this.canvas.width = this.canvas.width;
		this.toggleButtonCommonOperations();
		pen.save();
		
		pen.translate(this.width/2, this.height/2);
		pen.drawImage(c3, -this.width/2, -this.height/2);
		if(this.value==0){
			pen.drawImage(c1, -this.width/2, -this.height/2);
		}
		else{
			pen.drawImage(c2, -this.width/2, -this.height/2);
		}
		pen.restore();

	}
}

function ArcLed(element){
	ArcLed.inherits(Instrument);
	Instrument.call(this, element);
	
	var width = element.getAttribute("width") || 70;
	var height = element.getAttribute("height") || 70;
	var wx = width/70;
	var hx = height/70;

	this.setWidth(width);
	this.setHeight(height);

	var parent_id = element.id || "";
	var style = element.getAttribute("style") || "";	
	var onchange = element.getAttribute("onchange") || "";
	var rotate = element.getAttribute("rotate") || "270";
	var color = element.getAttribute("color") || "lawngreen";
	

	var swel = Drinks.createElement("switch");	
	swel.setAttribute("id", parent_id+'sw');
	swel.setAttribute("type", "arc");
	swel.setAttribute("style", style);
	swel.setAttribute("onchange", onchange);
	swel.setAttribute("rotate", "270");
	swel.setAttribute("width", width);
	swel.setAttribute("height", height);
	this.appendChild(swel);
	var led = Drinks.createElement("led");	
	led.setAttribute("id", parent_id+"led");
	led.setAttribute("color", color);
	led.setAttribute("type", "triangle");
	led.setAttribute("width", (20*width/70).toString());
	led.setAttribute("height", (20*height/70).toString());
	led.setAttribute("x", (45*wx).toString());
	led.setAttribute("y", (25*wx));
	led.setAttribute("rotate", "90");
	led.setAttribute("link", "true");
	this.inner[0].appendChild(led);
	
	this.on = function(){
		this.inner[0].value=1;
	}

	this.off = function(){
		this.inner[0].value=0;
	}

	this.render = function(){
		this.instrumentCommonOperations();
		
	}
}