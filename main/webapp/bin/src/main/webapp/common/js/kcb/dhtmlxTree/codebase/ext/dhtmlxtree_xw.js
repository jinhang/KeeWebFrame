//v.3.0 build 110713

/*
Copyright DHTMLX LTD. http://www.dhtmlx.com
To use this component please contact sales@dhtmlx.com to obtain license
*/
dhtmlXTreeObject.prototype._serEnts=[["&","&amp;"],["<","&lt;"],[">","&gt;"]];dhtmlXTreeObject.prototype.registerXMLEntity=function(a,b){this._serEnts[this._serEnts.length]=[a,b,RegExp(a,"g")]};
dhtmlXTreeObject.prototype.setSerializationLevel=function(a,b,c,d,e){this._xuserData=convertStringToBoolean(a);this._xfullXML=convertStringToBoolean(b);this._dtd=e;this._xescapeEntities=convertStringToBoolean(c);if(convertStringToBoolean(d))this._apreUC="<![CDATA[",this._apstUC="]]\>";for(var f=0;f<this._serEnts.length;f++)this._serEnts[f][2]=RegExp(this._serEnts[f][0],"g")};
dhtmlXTreeObject.prototype.serializeTree=function(){this.stopEdit&&this.stopEdit();this._apreUC=this._apreUC||"";this._apstUC=this._apstUC||"";var a='<?xml version="1.0"?>';this._dtd&&(a+='<!DOCTYPE tree SYSTEM "'+this._dtd+'">');a+='<tree id="'+this.rootId+'">';if(this._xuserData&&this._idpull[this.rootId]._userdatalist)for(var b=this._idpull[this.rootId]._userdatalist.split(","),c=0;c<b.length;c++)a+='<userdata name="'+b[c]+'">'+this._apreUC+this._idpull[this.rootId].userData["t_"+b[c]]+this._apstUC+
"</userdata>";for(c=0;c<this.htmlNode.childsCount;c++)a+=this._serializeItem(this.htmlNode.childNodes[c]);a+="</tree>";return a};
dhtmlXTreeObject.prototype._serializeItem=function(a){if(a.unParsed)if(document.all)return a.unParsed.d.xml;else{var b=new XMLSerializer;return b.serializeToString(a.unParsed.d)}var c="",d=this._selected.length?this._selected[0].id:'"',e=a.span.innerHTML;if(this._xescapeEntities)for(var f=0;f<this._serEnts.length;f++)e=e.replace(this._serEnts[f][2],this._serEnts[f][1]);c=this._xfullXML?'<item id="'+a.id+'" '+(this._getOpenState(a)==1?' open="1" ':"")+(d==a.id?' select="1"':"")+' text="'+e+'" im0="'+
a.images[0]+'" im1="'+a.images[1]+'" im2="'+a.images[2]+'" '+(a.acolor?'aCol="'+a.acolor+'" ':"")+(a.scolor?'sCol="'+a.scolor+'" ':"")+(a.checkstate==1?'checked="1" ':a.checkstate==2?'checked="-1"':"")+(a.closeable?'closeable="1" ':"")+(this.XMLsource&&a.XMLload==0?' child="1" ':"")+">":'<item id="'+a.id+'" '+(this._getOpenState(a)==1?' open="1" ':"")+(d==a.id?' select="1"':"")+' text="'+e+'"'+(this.XMLsource&&a.XMLload==0?' child="1" ':"")+">";if(this._xuserData&&a._userdatalist)for(var g=a._userdatalist.split(","),
f=0;f<g.length;f++)c+='<userdata name="'+g[f]+'">'+this._apreUC+a.userData["t_"+g[f]]+this._apstUC+"</userdata>";for(f=0;f<a.childsCount;f++)c+=this._serializeItem(a.childNodes[f]);c+="</item>";return c};dhtmlXTreeObject.prototype.saveSelectedItem=function(a,b){a=a||"";this.setCookie("treeStateSelected"+a,this.getSelectedItemId(),b)};dhtmlXTreeObject.prototype.restoreSelectedItem=function(a){var a=a||"",b=this.getCookie("treeStateSelected"+a);this.selectItem(b,!1)};
dhtmlXTreeObject.prototype.enableAutoSavingSelected=function(a,b){if((this.assMode=convertStringToBoolean(a))&&!this.oldOnSelect)this.oldOnSelect=this.onRowSelect,this.onRowSelect=function(a,b,e){b||(b=this);b.parentObject.treeNod.oldOnSelect(a,b,e);b.parentObject.treeNod.assMode&&b.parentObject.treeNod.saveSelectedItem(b.parentObject.treeNod.assCookieName)};this.assCookieName=b};
dhtmlXTreeObject.prototype.saveState=function(a,b){var c=this._escape(this.serializeTree()),d=4E3;if(c.length>d){if(navigator.appName.indexOf("Microsoft")!=-1)return!1;this.setCookie("treeStatex"+a,Math.ceil(c.length/d));for(var e=0;e<Math.ceil(c.length/d);e++)this.setCookie("treeStatex"+a+"x"+e,c.substr(e*d,d),b)}else this.setCookie("treeStatex"+a,c,b);c=this.getCookie("treeStatex"+a);return!c?(this.setCookie("treeStatex"+a,"",b),!1):!0};
dhtmlXTreeObject.prototype.loadState=function(a){var b=this.getCookie("treeStatex"+a);if(!b)return!1;if(b.length){if(b.toString().length<4){for(var c="",d=0;d<b;d++)c+=this.getCookie("treeStatex"+a+"x"+d);b=c}this.loadXMLString(this.utfesc=="utf8"?decodeURI(b):unescape(b))}return!0};dhtmlXTreeObject.prototype.setCookie=function(a,b,c){var d=a+"="+b+(c?"; "+c:"");document.cookie=d};
dhtmlXTreeObject.prototype.getCookie=function(a){var b=a+"=";if(document.cookie.length>0){var c=document.cookie.indexOf(b);if(c!=-1){c+=b.length;var d=document.cookie.indexOf(";",c);if(d==-1)d=document.cookie.length;return document.cookie.substring(c,d)}}};dhtmlXTreeObject.prototype.saveOpenStates=function(a,b){for(var c=[],d=0;d<this.htmlNode.childsCount;d++)c=c.concat(this._collectOpenStates(this.htmlNode.childNodes[d]));c=c.join(this.dlmtr);this.setCookie("treeOpenStatex"+a,c,b)};
dhtmlXTreeObject.prototype.loadOpenStates=function(a){for(var b=0;b<this.htmlNode.childsCount;b++)this._xcloseAll(this.htmlNode.childNodes[b]);this.allTree.childNodes[0].border="1";this.allTree.childNodes[0].border="0";var c=getCookie("treeOpenStatex"+a);if(c)for(var d=c.split(this.dlmtr),b=0;b<d.length;b++){var e=this._globalIdStorageFind(d[b]);if(e)if(this.XMLsource&&!e.XMLload&&e.id!=this.rootId){this._delayedLoad(e,"loadOpenStates('"+a+"')");return}else this.openItem(d[b])}this.callEvent("onAllOpenDynamic",
[])};dhtmlXTreeObject.prototype._delayedLoad=function(a,b){this.afterLoadMethod=b;this.onLoadReserve=this.onXLE;this.onXLE=this._delayedLoadStep2;this._loadDynXML(a.id)};dhtmlXTreeObject.prototype._delayedLoadStep2=function(a){a.onXLE=a.onLoadReserve;window.setTimeout(function(){eval("tree."+a.afterLoadMethod)},100);if(a.onXLE)a.onXLE(a);a.callEvent("onXLE",[a])};
dhtmlXTreeObject.prototype._collectOpenStates=function(a){var b=[];if(this._getOpenState(a)==1){b.push(a.id);for(var c=0;c<a.childsCount;c++)b=b.concat(this._collectOpenStates(a.childNodes[c]))}return b};function setCookie(a,b){document.cookie=a+"="+b}function getCookie(a){var b=a+"=";if(document.cookie.length>0){var c=document.cookie.indexOf(b);if(c!=-1){c+=b.length;var d=document.cookie.indexOf(";",c);if(d==-1)d=document.cookie.length;return document.cookie.substring(c,d)}}}
dhtmlXTreeObject.prototype.openAllItemsDynamic=function(a){this.ClosedElem=[];this.G_node=null;var b=this._globalIdStorageFind(a||this.rootId);b.id!=this.rootId&&tree.getOpenState(b.id)!=0&&this.openItem(a);this._openAllNodeChilds(b,0);if(this.ClosedElem.length>0)this.onLoadReserve=this.onXLE,this.onXLE=this._loadAndOpen,this._loadAndOpen(this)};
dhtmlXTreeObject.prototype._openAllNodeChilds=function(a){(a.XMLload==0||a.unParsed)&&this.ClosedElem.push(a);for(var b=0;b<a.childsCount;b++)this._getOpenState(a.childNodes[b])<0&&this._HideShow(a.childNodes[b],2),a.childNodes[b].childsCount>0&&this._openAllNodeChilds(a.childNodes[b]),(a.childNodes[b].XMLload==0||a.childNodes[b].unParsed)&&this.ClosedElem.push(a.childNodes[b])};
dhtmlXTreeObject.prototype._loadAndOpen=function(a){if(a.G_node)a._openItem(a.G_node),a._openAllNodeChilds(a.G_node),a.G_node=null;if(a.ClosedElem.length>0)a.G_node=a.ClosedElem.shift();if(a.G_node)a.G_node.unParsed?a.reParse(a.G_node):window.setTimeout(function(){a._loadDynXML(a.G_node.id)},100);else{a.onXLE=a.onLoadReserve;if(a.onXLE)a.onXLE(a);a.callEvent("onAllOpenDynamic",[a])}};
dhtmlXTreeObject.prototype.openItemsDynamic=function(a,b){if(this.onXLE!=this._stepOpen)this._opnItmsDnmcFlg=convertStringToBoolean(b),this.onLoadReserve=this.onXLE,this.onXLE=this._stepOpen,this.ClosedElem=a.split(",").reverse(),this._stepOpen(this)};
dhtmlXTreeObject.prototype._stepOpen=function(a){if(a.ClosedElem.length){a.G_node=a.ClosedElem.pop();var b=a._globalIdStorageFind(a.G_node);b.XMLload===0?a.openItem(a.G_node):(a.openItem(a.G_node),a._stepOpen(a))}else a.onXLE=a.onLoadReserve,a._opnItmsDnmcFlg&&a.selectItem(a.G_node,!0),a.onXLE&&arguments[1]&&a.onXLE.apply(a,arguments),a.callEvent("onOpenDynamicEnd",[])};

//v.3.0 build 110713

/*
Copyright DHTMLX LTD. http://www.dhtmlx.com
To use this component please contact sales@dhtmlx.com to obtain license
*/