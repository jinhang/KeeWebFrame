package com.kcb.app.json;

import static com.wfs.model.tables.User.USER;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.kcb.app.json.model.ResultModel1;
import com.wfs.common.helper.UserHelper;
import com.wfs.engine.Context;
import com.wfs.model.tables.pojos.User;
import com.wfs.task.Msg;

public class JsonHelper {
	
	private static final Logger logger = Logger.getLogger(JsonHelper.class);
	
	public static String ResultModel1ToJsonString(ResultModel1 r1) throws Exception {
		return JSONObject.fromObject(r1).toString(); 
	}

}
