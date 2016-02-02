package com.kcb.common.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class PatternMatcherTest {

	
	
	

	
	
	@Test
	public  void mainx() {
		// TODO Auto-generated method stub

		   // 要匹配的字符串  
        String source = "不发申通发EMS";
        // 将上面要匹配的字符串转换成小写  
       // source = source.toLowerCase();  
        // 匹配的字符串的正则表达式  
       // String reg_charset = "<span[^>]*?title=\'([0-9]*[\\s|\\S]*[\u4E00-\u9FA5]*)\'[\\s|\\S]*class=\'[a-z]*[\\s|\\S]*[a-z]*[0-9]*\'";    
        String reg_charset1 = "不[\\s]*发[\\s]*[\\S]{2,4}[\\s]*";    
        String reg_charset2 = "不[\\s]*要[\\s]*发[\\s]*[\\S]{2,4}[\\s]*";    
        String reg_charset3 = "不[\\s]*用[\\s]*[\\S]{2,4}[\\s]*";    
        String reg_charset4 = "不[\\s]*要[\\s]*用[\\s]*[\\S]{2,4}[\\s]*";  
        String reg_charset6 = "拒[\\s]*发[\\s]*[\\S]{2,4}[\\s]*";  
        String reg_charset7 = "拒[\\s]*绝[\\s]*用[\\s]*[\\S]{2,4}[\\s]*";    
        String reg_charset8 = "拒[\\s]*用[\\s]*[\\S]{2,4}[\\s]*";  
        String reg_charset9 = "拒[\\s]*发[\\s]*[\\S]{2,4}[\\s]*发[\\s]*[\\S]{2,4}";    
        String reg_charset10 = "拒[\\s]*绝[\\s]*发[\\s]*[\\S]{2,4}[\\s]*发[\\s]*[\\S]{2,4}";    
        String reg_charset11 = "拒[\\s]*用[\\s]*[\\S]{2,4}[\\s]*用[\\s]*[\\S]{2,4}";    
        String reg_charset12 = "拒[\\s]*绝[\\s]*用[\\s]*[\\S]{2,4}[\\s]*用[\\s]*[\\S]{2,4}";    
        String reg_charset13 = "不[\\s]*用[\\s]*[\\S]{2,4}[\\s]*用[\\s]*[\\S]{2,4}";    
        String reg_charset14 = "不[\\s]*发[\\s]*[\\S]{2,4}[\\s]*发[\\s]*[\\S]{2,4}";    
        
        String regex="([\u4e00-\u9fa5]+)";
        
        regex="(/[^\\u4E00-\\u9FA5\\w]/g)";
        String testMatcher="奇才奇才要  枯 33 442 E要要";
        Pattern p = Pattern.compile(regex);  
        Matcher m = p.matcher(testMatcher);  
        while (m.find()) {  
         System.out.println(m.group(0));
        }
	}
	@Test
	public void CodeGeneratorTest(){
		String ms = System.currentTimeMillis()+"";
		System.out.println(ms);
		ms = ms.substring(ms.length() - 1);
		System.out.println(ms);
	}

}
