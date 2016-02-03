package com.kee.app.json;

import static cn.kee.model.tables.User.USER;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.kee.app.json.model.ResultModel1;
import com.kee.model.tables.pojos.User;
import com.kee.task.Msg;
import com.wfs.engine.Context;

import cn.kee.common.helper.UserHelper;

public class JsonHelper {
	
	private static final Logger logger = Logger.getLogger(JsonHelper.class);
	
	public static String ResultModel1ToJsonString(ResultModel1 r1) throws Exception {
		return JSONObject.fromObject(r1).toString(); 
	}

}
