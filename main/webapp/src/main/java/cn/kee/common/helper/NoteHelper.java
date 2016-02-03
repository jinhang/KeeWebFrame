package cn.kee.common.helper;

import static cn.kee.model.tables.Addresslibrary.ADDRESSLIBRARY;
import static cn.kee.model.tables.Center.CENTER;
import static cn.kee.model.tables.Note.NOTE;

import java.util.List;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.kee.common.util.UUIDTool;
import com.kee.model.tables.pojos.Addresslibrary;
import com.kee.model.tables.pojos.Note;
import com.kee.model.tables.records.NoteRecord;
import com.kee.task.Msg;
import com.wfs.engine.Context;

public class NoteHelper {
	
	private static final Logger logger = Logger.getLogger(NoteHelper.class);
	
	
	public static Msg<List<Note>> getNotes(Context context,String staffid,Integer first,Integer limit){
		DSLContext dSLContext = context.getJc().getDefaultClient().getContext();
		Msg<List<Note>> msg = new Msg<List<Note>>();
		List<Note> al=dSLContext.select().from(NOTE)
				.where(NOTE.USERID.eq(staffid)).orderBy(NOTE.NAME.asc())
				.limit(limit).offset(first).fetchInto(Note.class);
		if(al.size()>=1){
			msg.setResult(true);
			msg.setValue(al);
		}else{
			msg.setResult(false);
		}
		return msg;
	}
	
	public static Msg<String> addNote(Context context,Note note){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		NoteRecord record = create.newRecord(NOTE);
		try{
			Integer num = create.selectCount().from(NOTE)
					.where(NOTE.USERID.eq(note.getUserid())).fetchOneInto(Integer.class);
			note.setName("模板"+String.valueOf(num+1));
			record.from(note);
			record.setNoteid(UUIDTool.getUUID());			
			record.store();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setValue(record.getNoteid());
		msg.setResult(true);
		return msg;
	}
	
	public static Msg<String> updateNote(Context context,Note note){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		try{
			create.update(NOTE)
			.set(NOTE.NAME,note.getName())
			.set(NOTE.CONTEXT,note.getContext())
			.where(NOTE.NOTEID.eq(note.getNoteid())).execute();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setResult(true);
		return msg;
	}
	
	public static Boolean CheckUnique(Context context,String userid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Boolean flag = false;		
		try{
			Integer num = create.selectCount().from(NOTE)
					.where(NOTE.USERID.eq(userid)).fetchOneInto(Integer.class);
			if(num==0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		}		
		return flag;
	}
	
	public static Msg<String> delNote(Context context,Note note){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		try{
			create.delete(NOTE).where(NOTE.NOTEID.eq(note.getNoteid())).execute();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setResult(true);
		return msg;
	}

}
