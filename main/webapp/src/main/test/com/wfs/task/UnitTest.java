package com.wfs.task;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.kcb.zm.interfaces.UploadOdData;
import com.wfs.common.jooq.CodeEnum.ScanType;
import com.wfs.model.tables.pojos.Logisticsdata;
public class UnitTest {
	
	@Test
	public void testJson() throws Exception{
		UploadOdData uodd= new UploadOdData();
		Logisticsdata ld = new Logisticsdata();
		List<Logisticsdata> lds = new ArrayList();
		ld.setNum("1");
		ld.setScanname("测试323");
		ld.setSendcode("368091944430");
		ld.setScantype("OK");
		ld.setStationcode("1821");
		ld.setScantime("2015-07-29 15:41:30");
		ld.setStaffcode("3220001053");
		ld.setLnstation("0057");
		ld.setWeight("");
		ld.setPacakgenum("1");
		ld.setBatchnum("");
		ld.setPronum("");
		ld.setMemo("签收人");
		ld.setDsnum("");
		ld.setImage("");
		lds.add(ld);
		Msg<List<String>> msg = UploadOdData.OdData(lds, ScanType.OD.key);
		System.out.print(msg.getResult());
	}

}
