dhtmlXForm.prototype.items.butt = {
	    // methods will added automaticaly:
	    // show, hide, isHidden, isExist, getType
	    // 1st param should be item
	    // constructor, required
	    render: function(item, data) {
	        // item - div of parent container
	        // data - init json
	        item._type = "butt";
	 
	        /* your custom code started here */
	 
	       item.appendChild(document.createElement("DIV"));
	        
	        item.lastChild.innerHTML = '<a id="'+data.name+'" class="butt"><span class="'+data.label+'">'+data.value+'</span></a>';
	        //alert(item.lastChild.innerHTML);
	        this._custom_inner_func(item);
	 
	        // you can insert not text only, any input, any code
	        /* your custom code ended here */
	 
	        return this;
	    },
	    
		//destructor, required (if you will use unload)
		destruct: function(item) {
		
		    /* your custom code started here */
		
		    this._custom_inner_func2(item);
		    item.innerHTML = "";
		
		    /* your custom code ended here */
		    },
		
		// enable item, mandatory
		enable: function(item) {
		
		    /* your custom code started here */
		
		    item.lastChild.style.color = "black";
		    item._is_enabled = true;
		
		    /* your custom code ended here */
		    },
		
		// disable item, mandatory
		disable: function(item) {
		
		    /* your custom code started here */
		
		    item.lastChild.style.color = "gray";
		    item._is_enabled = false;
		
		    /* your custom code ended here */
		
		    },
		
		// your custom functionality
		_custom_inner_func: function(item) {
		    item.lastChild.onclick = function() {
		        if (this.parentNode._is_enabled)
		            ;
		        }
		},
		
		_custom_inner_func2: function(item) {
		    item.lastChild.onclick = null;
		},
		
		// this methos will public
		setText: function(item, text) {
		    // it already exists in form
		    item.lastChild.innerHTML = text;
		
		    // demo of triggering events
		    // this will call user handler and pass item name and new text
		    item.callEvent("onTextChanged", [item._idd, text]);
		},
		
		// this methos will also public
		setBoldText: function(item, text) {
		    // but it not exists in form, so link to it needed, see below
		    item.lastChild.innerHTML = "<b>" + text + "</b>";
		
		    // demo of triggering events
		    // this will call user handler and pass item name and new text and true as bolded flag
		    item.callEvent("onTextChanged", [item._idd, text, true]);
		},
		
		// you you need validation and you need set/get value for you form, you need:
		// setValue and getValue, below basic code, you can add yout custom code also
		setValue: function(item, val) {
		    item._value = val;
		},
		
		getValue: function(item) {
		    return item._value;
		}

};



//link from form to item
dhtmlXForm.prototype.setBoldTextForMyItem = function(name, text) {
// this will call "setBoldText" with text param
	this.doWithItem(name, "setBoldText", text);
};

//support for set/get form data
//in our sample default set/get value used, but you can also use your custom methods
dhtmlXForm.prototype.setFormData_myItem = function(name, value) {
	return this.doWithItem(name, "setValue", value);
};

dhtmlXForm.prototype.getFormData_myItem = function(name) {
	return this.doWithItem(name, "getValue");
};	
