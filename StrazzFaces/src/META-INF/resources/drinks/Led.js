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
function Led(element){	

	Led.inherits(BinaryOutput);
	BinaryOutput.call(this, element);

	this.__defineSetter__("color", function(color){
		this._color = color;
		if(this.height && this.width && this.createGradients)
			this.createGradients();
	});

	this.__defineGetter__("color", function(){
		return this._color;
	 });

	this.color = element.getAttribute("color") || "lawngreen";

	this.ledCommonOperations = function(){
		this.binaryOutputCommonOperations();
	}

}

function RoundLed(element){	

	RoundLed.inherits(Led);
	Led.call(this, element);
	this.radius=parseInt(element.getAttribute("radius")) || 10;
	var pen = this.pen;
	var rad = this.radius/100;
	this.setHeight(this.radius*5/2+20*rad);
	this.setWidth(this.radius*5/2+20*rad);
	var grd_on, grd_off;
	this.createGradients = function(){
		grd_on = pen.createRadialGradient(-10*rad,-25*rad, 1, 3*rad, 7.5*rad, this.radius);
		grd_on.addColorStop(0, '#aaa');
		grd_on.addColorStop(0.9, this.color)
		grd_off = pen.createRadialGradient(-10*rad,-25*rad, 1, 3*rad, 7.5*rad, this.radius);
		grd_off.addColorStop(0, '#aaa');
		grd_off.addColorStop(0.9, '#525367');
	}
	this.createGradients();

	var drawLed = function(){
		pen.beginPath();
		pen.shadowOffsetX = 0;
		pen.shadowOffsetY = -1*this.radius/10;
		pen.shadowColor = "#999";
		pen.shadowBlur = 2*rad;
		pen.strokeStyle=grd_off;
		if(this.value==1)
			pen.fillStyle=grd_on;
		else
			pen.fillStyle=grd_off;
		pen.arc(0, 0, this.radius, (Math.PI/180)*0, (Math.PI/180)*360, false);
		pen.fill();
	}.bind(this);

	this.__defineSetter__("radius", function(radius){
		this._radius = radius;
		this.setHeight(this.radius*5/2+20*rad);
		this.setWidth(this.radius*5/2+20*rad);
		if(this.height && this.width)
			this.createGradients();
	});

	this.__defineGetter__("radius", function(){
		return this._radius;
	 });
	this.radius = parseInt(element.getAttribute("radius")) || 10;

	this.render = function(){
		this.canvas.width=this.canvas.width;
		this.ledCommonOperations();
		pen.save();
		pen.translate((this.radius*5/2+20*rad)/2, (this.radius*5/2+20*rad)/2);
		drawLed();
		pen.restore();
	}
}

function RectLed(element){	

	RectLed.inherits(Led);
	Led.call(this, element);
	var pen = this.pen;
	if(this.width==null)
		this.setWidth(100);
	if(this.height==null)
		this.setHeight(60);	
	var w = this.width/100;
	var h = this.height/60;
	var grd_on, grd_off, grd_bord;

	this.createGradients = function(){
		grd_off = pen.createLinearGradient(-this.width/2, -this.height/2, this.width/2, this.height/2);
		grd_off.addColorStop(0, '#ccc');
		grd_off.addColorStop(0.2, '#bbb');
		grd_off.addColorStop(0.4, '#ccc');
		grd_off.addColorStop(0.6, '#aaa');
		grd_off.addColorStop(1, '#525367');
		grd_on = pen.createLinearGradient(-this.width/2, -this.height/2, this.width/2, this.height/2);
		grd_on.addColorStop(0, this.color);
		grd_on.addColorStop(0.1, '#eee');
		grd_on.addColorStop(0.9, this.color);
		grd_bord = pen.createLinearGradient(-this.width, -this.height, this.width, this.height);
		grd_bord.addColorStop(0, '#999');
		grd_bord.addColorStop(0.9, 'transparent');
	}
	this.createGradients();

	var drawLed = function(){
		if(this.value)
			pen.fillStyle=grd_on;
		else	
			pen.fillStyle=grd_off;
		pen.strokeStyle=grd_bord;
		pen.lineWidth=3*w;
		pen.shadowOffsetX = 0;
		pen.shadowOffsetY = 0;
		pen.shadowColor = "#777";
		pen.shadowBlur = 2*(h/w);
		pen.roundRect(-this.width/2+5*w, -this.height/2+5*h, this.width-10*w, this.height-10*h, 2*w);
		pen.stroke();
		pen.fill();
	}.bind(this);

	this.__defineSetter__("width", function(width){
		this._width = width;
		this.canvas.width=width;
		this.container.style.width = width+'px';
		w = width/100;
		if(this.height && this.width)
			this.createGradients();
	});

	this.__defineGetter__("width", function(){
		return this._width;
	 });

	this.__defineSetter__("height", function(height){
		this._height = height;
		this.canvas.height=height;
		this.container.style.height = height+'px';
		h = height/60;
		if(this.height && this.width)
			this.createGradients();
	});
	
	this.__defineGetter__("height", function(){
		return this._height;
	 });

	this.width = this.canvas.width;
	this.height = this.canvas.height;

	this.render = function(){
		this.canvas.width=this.canvas.width;
		this.ledCommonOperations();
		pen.save();
		pen.translate(this.width/2, this.height/2);
		drawLed();
		pen.restore();
	}

}

function TriangleLed(element){	

	TriangleLed.inherits(Led);
	Led.call(this, element);
	var pen = this.pen;
	if(this.width==null)
		this.setWidth(60);
	if(this.height==null)
		this.setHeight(60);	
	var w = this.width/60;
	var h = this.height/60;

	var grd_on, grd_off, grd_bord;

	this.createGradients = function(){
		grd_off = pen.createLinearGradient(-this.width/2, -this.height/2, this.width/2, this.height/2);
		grd_off.addColorStop(0, '#ccc');
		grd_off.addColorStop(0.2, '#bbb');
		grd_off.addColorStop(0.4, '#ccc');
		grd_off.addColorStop(0.6, '#aaa');
		grd_off.addColorStop(1, '#525367');
		grd_on = pen.createLinearGradient(-this.width/2, -this.height/2, this.width/2, this.height/2);
		grd_on.addColorStop(0, this.color);
		grd_on.addColorStop(0.1, '#eee');
		grd_on.addColorStop(0.9, this.color);
		grd_bord = pen.createLinearGradient(-this.width, -this.height, this.width, this.height);
		grd_bord.addColorStop(0, '#999');
		grd_bord.addColorStop(0.9, 'transparent');
	}
	this.createGradients();

	var drawLed = function(){
		if(this.value)
			pen.fillStyle=grd_on;
		else	
			pen.fillStyle=grd_off;
		pen.strokeStyle=grd_bord;
		pen.lineWidth=3*w;
		pen.shadowOffsetX = 0;
		pen.shadowOffsetY = 0;
		pen.shadowColor = "#777";
		pen.shadowBlur = 2*(h/w);
		pen.beginPath();
		pen.moveTo(0, -this.height/2+5*h);
		pen.lineTo(this.width/2-5*w, this.height/2-5*h);
		pen.lineTo(-this.width/2+5*w, this.height/2-5*h);
		pen.closePath();
		pen.stroke();
		pen.fill();
	}.bind(this);

	this.__defineSetter__("width", function(width){
		this._width = width;
		this.canvas.width=width;
		this.container.style.width = width+'px';
		w = width/60;
		if(this.height && this.width)
			this.createGradients();
	});

	this.__defineGetter__("width", function(){
		return this._width;
	 });

	this.__defineSetter__("height", function(height){
		this._height = height;
		this.canvas.height=height;
		this.container.style.height = height+'px';
		h = height/60;
		if(this.height && this.width)
			this.createGradients();
	});
	
	this.__defineGetter__("height", function(){
		return this._height;
	 });

	this.width = this.canvas.width;
	this.height = this.canvas.height;

	this.render = function(){
		this.canvas.width=this.canvas.width;
		this.ledCommonOperations();
		pen.save();
		pen.translate(this.width/2, this.height/2);
		drawLed();
		pen.restore();
	}

}