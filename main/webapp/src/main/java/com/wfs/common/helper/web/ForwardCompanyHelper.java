package com.wfs.common.helper.web;

import static com.wfs.model.tables.Forwardcompany.FORWARDCOMPANY;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.wfs.engine.Context;
import com.wfs.task.Msg;
import com.wfs.model.tables.pojos.Forwardcompany;

public class ForwardCompanyHelper {
	
	private static final Logger logger = Logger.getLogger(ForwardCompanyHelper.class);
	
	public static Msg<List<Map<String, Object>>> getExpress(Context context){
		Msg<List<Map<String, Object>>> rmsg = new Msg<List<Map<String, Object>>>();
		DSLContext create = context.getJc().getDefaultClient().getContext();
		List<Map<String, Object>> lvalue = create.select(FORWARDCOMPANY.ID,FORWARDCOMPANY.SHORTNAME,FORWARDCOMPANY.CODE,
				FORWARDCOMPANY.NAME,FORWARDCOMPANY.TYPE,FORWARDCOMPANY.MEMO,FORWARDCOMPANY.SORTNO).from(FORWARDCOMPANY).fetch().intoMaps();
		Integer total = create.selectCount().from(FORWARDCOMPANY).fetchOneInto(Integer.class);
		rmsg.setState(total);
		rmsg.setResult(true);
		rmsg.setValue(lvalue);
		return rmsg;		
	}
	
	public static Msg<List<Forwardcompany>> getFor(Context context){
		Msg<List<Forwardcompany>> rmsg = new Msg<List<Forwardcompany>>();
		DSLContext create = context.getJc().getDefaultClient().getContext();
		List<Forwardcompany> lvalue = create.select().from(FORWARDCOMPANY).fetchInto(Forwardcompany.class);
		rmsg.setResult(true);
		rmsg.setValue(lvalue);
		return rmsg;		
	}

}
