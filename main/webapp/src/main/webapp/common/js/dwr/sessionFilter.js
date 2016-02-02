dwr.engine.transport.xhr.stateChange = function(batch) {
	

    var toEval;

    if (batch.completed) {
      dwr.engine._debug("Error: _stateChange() with batch.completed");
      return;
    }

    // Try to get the response HTTP status if applicable
    var req = batch.req;
    var status = 0;
    try {
      if (req.readyState >= 2) {
        status = req.status; // causes Mozilla to except on page moves
      }
    }
    catch(ignore) {}

    // If we couldn't get the status we bail out, unless the request is
    // complete, which means error (handled further below)
    if (status == 0 && req.readyState < 4) {
      return;
    }

    // If the status is 200, we are now online. 
    // Future improvement per Mike W. - A solution where we only use the callbacks/handlers of the poll call to trigger 
    // the retry handling would be ideal.  We would need something like a new internal callback that reports 
    // progress back to the caller, and the design should be compatible with getting it to work with iframes as well.   
    if (status == 200 && !dwr.engine._pollOnline) {
      dwr.engine._handlePollStatusChange(true);    
    }  

    // The rest of this function only deals with request completion
    if (req.readyState != 4) {
      return;
    }

    if (dwr.engine._unloading && !dwr.engine.isJaxerServer) {
      dwr.engine._debug("Ignoring reply from server as page is unloading.");
      return;
    }

    try {
      var reply = req.responseText;
      reply = dwr.engine._replyRewriteHandler(reply);

      if (status != 200) {
      
            if ( status == 1000 || status == 9999) {
          	  if(status == 1000){
          		  $func.alert("未登录或登录超时，请重新登录");
          	  }else{
          		  $func.alert("该用户已经在其他地方登录");  
          	  }
             try{
          	   parent.window.location.href = "login.jsp";
             }catch (e) {
          	   window.location.href = "login.jsp";
             }
             return;
            }
            dwr.engine._handleError(batch, { name:"dwr.engine.http." + status,     message:req.statusText });
      
      
      }
      else if (reply == null || reply == "") {
        dwr.engine._handleError(batch, { name:"dwr.engine.missingData", message:"No data received from server" });
      }
      else {                     
        var contentType = req.getResponseHeader("Content-Type");
        if (dwr.engine.isJaxerServer) {
          // HACK! Jaxer does something b0rken with Content-Type
          contentType = "text/javascript";
        }
        if (!contentType.match(/^text\/plain/) && !contentType.match(/^text\/javascript/)) {
          if (contentType.match(/^text\/html/) && typeof batch.textHtmlHandler == "function") {
            batch.textHtmlHandler({ status:status, responseText:reply, contentType:contentType });
          }
          else {
            dwr.engine._handleWarning(batch, { name:"dwr.engine.invalidMimeType", message:"Invalid content type: '" + contentType + "'" });
          }
        }
        else {
         // Comet replies might have already partially executed
         if (batch.isPoll && batch.map.partialResponse == dwr.engine._partialResponseYes) {
            dwr.engine.transport.xhr.processCometResponse(reply, batch);
          }
          else {
            if (reply.search("//#DWR") == -1) {
              dwr.engine._handleWarning(batch, { name:"dwr.engine.invalidReply", message:"Invalid reply from server" });
            }
            else {
              toEval = reply;
            }
          }
        }
      }
    }
    catch (ex) {
      dwr.engine._handleWarning(batch, ex);
    }

    // Outside of the try/catch so errors propagate normally:
    dwr.engine._receivedBatch = batch;
    if (toEval != null) toEval = toEval.replace(dwr.engine._scriptTagProtection, "");
    dwr.engine._eval(toEval);
    dwr.engine._receivedBatch = null;
    dwr.engine.transport.complete(batch);
	
	
};
	
	
	
