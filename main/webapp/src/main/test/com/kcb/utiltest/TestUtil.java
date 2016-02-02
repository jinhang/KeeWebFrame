package com.kcb.utiltest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.kcb.app.assign.MapApi;
import com.kcb.app.model.Points;
import com.wfs.task.Msg;

public class TestUtil {
	
	@Test
	public void testUtil() throws Exception{
		List<Points> test = new ArrayList<Points>();
		Points p= new Points();
		p.setUserid("1");
		p.setPoints1lat("30.218019");
		p.setPoints1lng("120.218067");
		p.setPoints2lat("30.219946");
		p.setPoints2lng("120.220677");
		p.setPoints3lat("30.21666");
		p.setPoints3lng("120.223563");
		p.setPoints4lat("30.214742");
		p.setPoints4lng("120.220561");
		test.add(p);
		
		Points p1= new Points();
		p1.setUserid("2");
		p1.setPoints1lat("30.210389");
		p1.setPoints1lng("120.218067");
		p1.setPoints2lat("31.222188");
		p1.setPoints2lng("118.28689");
		p1.setPoints3lat("30.219946");
		p1.setPoints3lng("120.220677");
		p1.setPoints4lat("30.194798");
		p1.setPoints4lng("120.220791");
		test.add(p1);
		
//		Msg<String> r = MapApi.getOrderStaffid("银丰大厦", test);
//		System.out.println(r.getValue());
	}

}
