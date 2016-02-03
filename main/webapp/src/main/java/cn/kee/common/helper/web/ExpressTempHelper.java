package cn.kee.common.helper.web;

import static cn.kee.model.tables.Expresstemplate.EXPRESSTEMPLATE;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.kee.common.util.UUIDTool;
import com.kee.model.tables.pojos.Expresstemplate;
import com.kee.model.tables.records.ExpresstemplateRecord;
import com.wfs.engine.Context;

public class ExpressTempHelper {
	
	private static final Logger logger = Logger.getLogger(ExpressTempHelper.class);
	
	public static Expresstemplate getExpressTempById(Context context,String id){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Expresstemplate expresste = create.fetchOne(EXPRESSTEMPLATE, EXPRESSTEMPLATE.ID.eq(id)).into(Expresstemplate.class);
		return expresste;
	}
	
	public static Boolean checkExpressTempName(Context context,Expresstemplate expresstemp){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Boolean flag = true;
		try{
			Integer num =create.selectCount().from(EXPRESSTEMPLATE)
					.where(EXPRESSTEMPLATE.NAME.eq(expresstemp.getName())
							.and(EXPRESSTEMPLATE.FORWARDCOMPANYID.eq(expresstemp.getForwardcompanyid())))
							.fetchOneInto(Integer.class);
			if(num>=1){
				flag=false;
			}
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		}		
		return flag;
	}
	
	
	public static Boolean saveExpressTemp(Context context,Expresstemplate expresstemp){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Boolean flag = true;
		ExpresstemplateRecord record = create.newRecord(EXPRESSTEMPLATE);
		try{
			record.from(expresstemp);
			record.setId(UUIDTool.getUUID());			
			record.store();
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		}
		return flag;		
	}

	public static Boolean updateExpressTemp(Context context,Expresstemplate expresstemp){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Boolean flag = true;
		ExpresstemplateRecord record = create.newRecord(EXPRESSTEMPLATE);
		try{
			create.update(EXPRESSTEMPLATE).set(EXPRESSTEMPLATE.NAME,expresstemp.getName())
			.set(EXPRESSTEMPLATE.TEMPLATEVALUE,expresstemp.getTemplatevalue())
			.set(EXPRESSTEMPLATE.CLIENTID,expresstemp.getClientid())
			.set(EXPRESSTEMPLATE.INITASTR,expresstemp.getInitastr())
			.set(EXPRESSTEMPLATE.PAGEWIDTH,expresstemp.getPagewidth())
			.set(EXPRESSTEMPLATE.PAGEHEIGHT,expresstemp.getPageheight())
			.set(EXPRESSTEMPLATE.FORWARDCOMPANYID,expresstemp.getForwardcompanyid())
			.set(EXPRESSTEMPLATE.PRINTER,expresstemp.getPrinter())
			.set(EXPRESSTEMPLATE.ISDEFAULT,expresstemp.getIsdefault())
			.set(EXPRESSTEMPLATE.TEMPLATEIMGURL,expresstemp.getTemplateimgurl())
			.set(EXPRESSTEMPLATE.NEEDSENDCODE,expresstemp.getNeedsendcode())
			.set(EXPRESSTEMPLATE.ISOPEN,expresstemp.getIsopen())
			.where(EXPRESSTEMPLATE.ID.eq(expresstemp.getId())).execute();
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		}
		return flag;		
	}

}
