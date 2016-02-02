package com.kcb.app.interfaces.service;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import com.wfs.model.tables.pojos.Note;
import com.wfs.task.Msg;

public interface IMessageService {
	
	@WebMethod
    public Msg<String> saveMessage(@WebParam(name="staffid")String staffid,@WebParam(name="telphone")String telphone,
    		@WebParam(name="num")String num,@WebParam(name="sendcode")String sendcode,@WebParam(name="time")String time,
    		@WebParam(name="type")String type,@WebParam(name="sendtype")String sendtype,@WebParam(name="reserveinfo")String reserveinfo,
    		@WebParam(name="istiming")Integer istiming);
	
	@WebMethod
    public Msg<String> opNotes(@WebParam(name="noteid")String noteid,@WebParam(name="name")String name,
    		@WebParam(name="context")String context,@WebParam(name="optype")Integer optype,
    		@WebParam(name="staffid")String staffid);
	
	
	@WebMethod
    public List<Note> getNotes(@WebParam(name="staffid")String staffid,
    		@WebParam(name="first")Integer first,@WebParam(name="limit")Integer limit);

}
