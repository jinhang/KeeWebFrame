/* button */

dhtmlXForm.prototype.validate = {}


dhtmlXForm.prototype.items.butt = {
	
	render: function(item, data) {
		item._type = "butt";
		item._enabled = true;
		item._cmd = data.command;
		item._name = data.name;
		
		var btn_class = data["btnclass"] || "butt";
		
		//item.className = String(item.className).replace("item_label_top","item_label_left").replace("item_label_right","item_label_left");
		
		if (!isNaN(data.width)) var w = Math.max(data.width-10,0);
		
		item.innerHTML = '<div>'+
					'<table cellspacing="0" cellpadding="0" border="0" align="left">'+
						'<tr>'+
							'<td>'+'<a id="'+data.name+'" class="'+btn_class+'"><span class="'+data.label+'">'+data.value+'</span></a>'+'</td>'+
						'</tr>'+
					'</table>'+
				"</div>";
		

		if (data.hidden == true) this.hide(item);
		if (data.disabled == true) this.userDisable(item);
		
		// item onselect start also needed once
		// will reconstructed!
		
		item.onselectstart = function(e){e=e||event;e.cancelBubble=true;e.returnValue=false;return false;}
		item.childNodes[0].onselectstart = function(e){e=e||event;e.cancelBubble=true;e.returnValue=false;return false;}
		


		item.childNodes[0].childNodes[0].onmousedown = function(){
			if (this._pressed) return;
			var t = this.parentNode.parentNode;
			if (!t._enabled) return;
			//this.className = "dhx_list_btn_pressed";
			this._allowClick = true;
			this._pressed = true;
			t = null;
		}
		
		item.childNodes[0].childNodes[0].onmouseup = function(){
			if (!this._pressed) return;
			var t = this.parentNode.parentNode;
			if (!t._enabled) return;
			t._busy = false;
			//this.className = (this._isOver?"dhx_list_btn_over":"");
			if (this._pressed && this._allowClick) t.callEvent("_onButtonClick", [t._name, t._cmd]);
			this._allowClick = false;
			this._pressed = false;
			t = null;
		}
		
		return this;
	},
	
	destruct: function(item) {
		
		this.doUnloadNestedLists(item);
		
		item.callEvent = null;
		item.checkEvent = null;
		item.getForm = null;
		
		item._autoCheck = null;
		item._type = null;
		item._enabled = null;
		item._cmd = null;
		item._name = null;
		
		item.onselectstart = null;
		
		item.childNodes[0].onselectstart = null;
		item.childNodes[0].onkeypress = null;
		item.childNodes[0].ontouchstart = null;
		item.childNodes[0].onblur = null;
		
		item.childNodes[0].childNodes[0].onmouseover = null;
		item.childNodes[0].childNodes[0].onmouseout = null;
		item.childNodes[0].childNodes[0].onmousedown = null;
		item.childNodes[0].childNodes[0].onmouseup = null;
		
		while (item.childNodes.length > 0) item.removeChild(item.childNodes[0]);
		
		item.parentNode.removeChild(item);
		item = null;
		
	},
	
	enable: function(item) {
		if (String(item.className).search("disabled") >= 0) item.className = String(item.className).replace(/disabled/gi,"");
		item._enabled = true;
		item.childNodes[0].tabIndex = 0;
		item.childNodes[0].removeAttribute("disabled");
	},
	
	disable: function(item) {
		if (String(item.className).search("disabled") < 0) item.className += " disabled";
		item._enabled = false;
		item.childNodes[0].tabIndex = -1;
		item.childNodes[0].setAttribute("disabled", "true");
	},
	
	isEnabled: function(item) {
		return item._enabled;
	},
	
	setText: function(item, text) {
		item.childNodes[0].childNodes[0].childNodes[0].childNodes[0].childNodes[1].childNodes[0].innerHTML = text;
	},

	getText: function(item) {
		return item.childNodes[0].childNodes[0].childNodes[0].childNodes[0].childNodes[1].childNodes[0].innerHTML;
	}
	
};

/* button */

dhtmlXForm.prototype.validate = {}


dhtmlXForm.prototype.items.butt2 = {
	
	render: function(item, data) {
		item._type = "butt2";
		item._enabled = true;
		item._cmd = data.command;
		item._name = data.name;
		
		var btn_class = data["btnclass"] || "butt2";
		
		//item.className = String(item.className).replace("item_label_top","item_label_left").replace("item_label_right","item_label_left");
		
		if (!isNaN(data.width)) var w = Math.max(data.width-10,0);
		
		item.innerHTML = '<div>'+
					'<table cellspacing="0" cellpadding="0" border="0" align="left">'+
						'<tr>'+
							'<td>'+'<a id="'+data.name+'" class="'+btn_class+'"><span class="'+data.label+'">'+data.value+'</span></a>'+'</td>'+
						'</tr>'+
					'</table>'+
				"</div>";
		

		if (data.hidden == true) this.hide(item);
		if (data.disabled == true) this.userDisable(item);
		
		// item onselect start also needed once
		// will reconstructed!
		
		item.onselectstart = function(e){e=e||event;e.cancelBubble=true;e.returnValue=false;return false;}
		item.childNodes[0].onselectstart = function(e){e=e||event;e.cancelBubble=true;e.returnValue=false;return false;}
		


		item.childNodes[0].childNodes[0].onmousedown = function(){
			if (this._pressed) return;
			var t = this.parentNode.parentNode;
			if (!t._enabled) return;
			//this.className = "dhx_list_btn_pressed";
			this._allowClick = true;
			this._pressed = true;
			t = null;
		}
		
		item.childNodes[0].childNodes[0].onmouseup = function(){
			if (!this._pressed) return;
			var t = this.parentNode.parentNode;
			if (!t._enabled) return;
			t._busy = false;
			//this.className = (this._isOver?"dhx_list_btn_over":"");
			if (this._pressed && this._allowClick) t.callEvent("_onButtonClick", [t._name, t._cmd]);
			this._allowClick = false;
			this._pressed = false;
			t = null;
		}
		
		return this;
	},
	
	destruct: function(item) {
		
		this.doUnloadNestedLists(item);
		
		item.callEvent = null;
		item.checkEvent = null;
		item.getForm = null;
		
		item._autoCheck = null;
		item._type = null;
		item._enabled = null;
		item._cmd = null;
		item._name = null;
		
		item.onselectstart = null;
		
		item.childNodes[0].onselectstart = null;
		item.childNodes[0].onkeypress = null;
		item.childNodes[0].ontouchstart = null;
		item.childNodes[0].onblur = null;
		
		item.childNodes[0].childNodes[0].onmouseover = null;
		item.childNodes[0].childNodes[0].onmouseout = null;
		item.childNodes[0].childNodes[0].onmousedown = null;
		item.childNodes[0].childNodes[0].onmouseup = null;
		
		while (item.childNodes.length > 0) item.removeChild(item.childNodes[0]);
		
		item.parentNode.removeChild(item);
		item = null;
		
	},
	
	enable: function(item) {
		if (String(item.className).search("disabled") >= 0) item.className = String(item.className).replace(/disabled/gi,"");
		item._enabled = true;
		item.childNodes[0].tabIndex = 0;
		item.childNodes[0].removeAttribute("disabled");
	},
	
	disable: function(item) {
		if (String(item.className).search("disabled") < 0) item.className += " disabled";
		item._enabled = false;
		item.childNodes[0].tabIndex = -1;
		item.childNodes[0].setAttribute("disabled", "true");
	},
	
	isEnabled: function(item) {
		return item._enabled;
	},
	
	setText: function(item, text) {
		item.childNodes[0].childNodes[0].childNodes[0].childNodes[0].childNodes[1].childNodes[0].innerHTML = text;
	},

	getText: function(item) {
		return item.childNodes[0].childNodes[0].childNodes[0].childNodes[0].childNodes[1].childNodes[0].innerHTML;
	}
	
};