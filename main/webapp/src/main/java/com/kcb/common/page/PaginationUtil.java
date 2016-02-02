package com.kcb.common.page;

/**
 * @author jyl
 * @version 创建时间：2012-4-12 下午10:39:06 类说明
 */
public class PaginationUtil {

	private static int numLimit = 6;
	/**
	 * 分页,显示格式为"[首页] [上一页] [下一页] [尾页]"
	 * @param page
	 * @param url
	 * @param style
	 * @return
	 */
	public static String getPagination(Page page,String url,String style) {

		String pagination="";
		//当前页码是最后一页
		if(page.getTotalPage()==0){
			pagination = "[当前没有任何记录]";
		}else if (page.getCurrentPage() == page.getTotalPage()) {
			// 如果total = 1，则无需分页，显示"[第1页] [共1页]"
			if (1==page.getTotalPage()) {
				pagination = "[共"+page.getTotalCount()+"条记录,共 " + 1 + " 页]";
			} else {
				// 到达最后一页,显示"[首页][上一页][尾页]"
				pagination +="共"+page.getTotalCount()+"条记录,共"+page.getTotalPage()+"页,当前第"+page.getCurrentPage()+"页";
				pagination += "<a href='";
				pagination += url;
				if(url.indexOf("?")==-1)pagination += "?current=1&total=" + page.getTotalCount();
				else pagination += "&current=1&total=" + page.getTotalCount();
				pagination += "'>[首页]</a> <a href='";
				pagination += url;
				if(url.indexOf("?")==-1)pagination += "?current=" + (page.getCurrentPage()-1) + "&total=" + page.getTotalCount();
				else
					pagination += "&current=" + (page.getCurrentPage()-1) + "&total=" + page.getTotalCount();
				pagination += "'>[上一页]</a>[下一页][尾页]";
			}
		} else {
			// 当前页是第一页
			if (1==page.getCurrentPage()) {
				// 第一页，显示"[首页] [下一页] [末页]"
				pagination +="共"+page.getTotalCount()+"条记录,共"+page.getTotalPage()+"页,当前第"+page.getCurrentPage()+"页";
				pagination += "[首页] </a>[上一页]<a href='";
				pagination += url;
				if(url.indexOf("?")==-1)pagination += "?current=" + (page.getCurrentPage()+1) + "&total=" + page.getTotalCount();
				else pagination += "&current=" + (page.getCurrentPage()+1) + "&total=" + page.getTotalCount();
				pagination += "'> [下一页]</a> <a href='";
				pagination += url;
				if(url.indexOf("?")==-1)pagination += "?current=" + page.getTotalPage() + "&total=" + page.getTotalCount();
				else pagination += "&current=" + page.getTotalPage() + "&total=" + page.getTotalCount();
				pagination += "'> [尾页]</a>";
			} else {
				// 不是第一页，显示"[首页] [上一页] [下一页] [末页]"
				pagination +="共"+page.getTotalCount()+"条记录,共"+page.getTotalPage()+"页,当前第"+page.getCurrentPage()+"页";
				pagination += "<a href='";
				pagination += url;
				if(url.indexOf("?")==-1)pagination += "?current=1&total=" + page.getTotalCount();
				else pagination += "&current=1&total=" + page.getTotalCount();
				pagination += "'>[首页]</a> <a href='";
				pagination += url;
				if(url.indexOf("?")==-1)pagination += "?current=" + (page.getCurrentPage()-1) + "&total=" + page.getTotalCount();
				else pagination += "&current=" + (page.getCurrentPage()-1) + "&total=" + page.getTotalCount();
				pagination += "'>[上一页]</a><a href='";
				pagination += url;
				if(url.indexOf("?")==-1)pagination += "?current=" + (page.getCurrentPage()+1) + "&total=" + page.getTotalCount();
				else pagination += "&current=" + (page.getCurrentPage()+1) + "&total=" + page.getTotalCount();
				pagination += "'>[下一页]</a> <a href='";
				pagination += url;
				if(url.indexOf("?")==-1)pagination += "?current=" + page.getTotalPage() + "&total=" + page.getTotalCount();
				else pagination += "&current=" + page.getTotalPage() + "&total=" + page.getTotalCount();
				pagination += "'>[尾页]</a>";
			}
		}
        return pagination;
	}
	
	/**
	 * 
	 * @param page
	 * @param url
	 * @param style
	 * @return
	 */
	public static String getPaginationStyleTwo(Page page,String url,String style){
		String pagination="";
		if(page.getCurrentPage()>page.getTotalPage()){
			page.setCurrentPage(1);
		}
		if(page.getTotalPage()==0){
			pagination = "暂无记录";
		}else if(page.getTotalPage()==1){
			pagination+="<a class=\"active\" href=\"javascript:;\"><b>1</b></a>";
		}else{
			if(url.indexOf("?")==-1)
			{
				//首页
				if(page.getCurrentPage()==1){
					pagination = "<a class=\"backpage grey\">上一页</a>";
					pagination+="<a class=\"active\" href=\"javascript:;\"><b>1</b></a>";
				}else{
					pagination = "<a class=\"backpage\" href='"+url+"?current="+(page.getCurrentPage()-1)+"&total="+page.getTotalCount()+"'>上一页</a>";
					pagination += "<a href='"+url+"?current=1&total="+page.getTotalCount()+"'><b>1</b></a>";
				}
				
				//处理前面的部分
				int start =  page.getCurrentPage()-numLimit/2;
				if(start>1){
					pagination += "<a class=\"shenlue\" href='"+url+"?current="+start+"&total="+page.getTotalCount()+"'>...</a>";
					start = start+1;
				}else{
					start = 2;
				}
				
				//处理后面的分页部分
				int end = page.getCurrentPage()+numLimit/2-1;
				if(end>=page.getTotalPage()){
					end = page.getTotalPage()-1;
				}
				
				//处理中间的分页部分
				for(int i=start;i<=end;i++){
					if(page.getCurrentPage()==i){
						pagination+="<a class=\"active\" href=\"javascript:;\"><b>"+i+"</b></a>";
					}else{
						pagination+="<a href='"+url+"?current="+i+"&total="+page.getTotalCount()+"'><b>"+i+"</b></a>";
					}
				}
				
				//处理后面的分页部分
				if((page.getCurrentPage()+numLimit/2)<page.getTotalPage()){
					pagination += "<a class=\"shenlue\" href='"+url+"?current="+(page.getCurrentPage()+numLimit/2)+"&total="+page.getTotalCount()+"'>...</a>";
				}
				
				//末页
				if(page.getCurrentPage()==page.getTotalPage()){
					pagination+="<a class=\"active\" href=\"javascript:;\"><b>"+page.getTotalPage()+"</b></a>";
				    pagination+="<a class=\"nextpage greys\">下一页</a>";
				}else{
					pagination+= "<a href='"+url+"?current="+page.getTotalPage()+"&total="+page.getTotalCount()+"'><b>"+page.getTotalPage()+"</b></a>";
					pagination+="<a class=\"nextpage\" href='"+url+"?current="+(page.getCurrentPage()+1)+"&total="+page.getTotalCount()+"'>下一页</a>";
				}
				
				pagination+="转到第<input type=\"text\" id=\"cp\" class=\"pageinput\">页<input type=\"button\" value=\"跳转\"  class=\"pagebtn\" onclick='javascript:location.href=\""+url+"?current=\"+$(\"#cp\").val()"+"+\"&total="+page.getTotalCount()+"\"'/>";
			}
			else
			{
				//首页
				if(page.getCurrentPage()==1){
					pagination = "<a class=\"backpage grey\">上一页</a>";
					pagination+="<a class=\"active\" href=\"javascript:;\"><b>1</b></a>";
				}else{
					pagination = "<a class=\"backpage\" href='"+url+"&current="+(page.getCurrentPage()-1)+"&total="+page.getTotalCount()+"'>上一页</a>";
					pagination += "<a href='"+url+"&current=1&total="+page.getTotalCount()+"'><b>1</b></a>";
				}
				
				//处理前面的部分
				int start =  page.getCurrentPage()-numLimit/2;
				if(start>1){
					pagination += "<a class=\"shenlue\" href='"+url+"&current="+start+"&total="+page.getTotalCount()+"'>...</a>";
					start = start+1;
				}else{
					start = 2;
				}
				
				//处理后面的分页部分
				int end = page.getCurrentPage()+numLimit/2-1;
				if(end>=page.getTotalPage()){
					end = page.getTotalPage()-1;
				}
				
				//处理中间的分页部分
				for(int i=start;i<=end;i++){
					if(page.getCurrentPage()==i){
						pagination+="<a class=\"active\" href=\"javascript:;\"><b>"+i+"</b></a>";
					}else{
						pagination+="<a href='"+url+"&current="+i+"&total="+page.getTotalCount()+"'><b>"+i+"</b></a>";
					}
				}
				
				//处理后面的分页部分
				if((page.getCurrentPage()+numLimit/2)<page.getTotalPage()){
					pagination += "<a class=\"shenlue\" href='"+url+"&current="+(page.getCurrentPage()+numLimit/2)+"&total="+page.getTotalCount()+"'>...</a>";
				}
				
				//末页
				if(page.getCurrentPage()==page.getTotalPage()){
					pagination+="<a class=\"active\" href=\"javascript:;\"><b>"+page.getTotalPage()+"</b></a>";
				    pagination+="<a class=\"nextpage greys\">下一页</a>";
				}else{
					pagination+= "<a href='"+url+"&current="+page.getTotalPage()+"&total="+page.getTotalCount()+"'><b>"+page.getTotalPage()+"</b></a>";
					pagination+="<a class=\"nextpage\" href='"+url+"&current="+(page.getCurrentPage()+1)+"&total="+page.getTotalCount()+"'>下一页</a>";
				}
				
				pagination+="转到第<input type=\"text\" id=\"cp\" class=\"pageinput\">页<input type=\"button\" value=\"跳转\"  class=\"pagebtn\" onclick='javascript:location.href=\""+url+"&current=\"+$(\"#cp\").val()"+"+\"&total="+page.getTotalCount()+"\"'/>";
			}
		}
		return pagination;
	}
	
	
	/**
	 * 分页,显示格式为"[首页] [上一页] [下一页] [尾页]"
	 * @param page
	 * @param url
	 * @param style
	 * @return
	 */
	public static String getPagination3(Page page,String url,String style) {

		String pagination="";
		//当前页码是最后一页
		if(page.getTotalPage()==0){
			pagination = "没有任何记录";
		}else if (page.getCurrentPage() == page.getTotalPage()) {
			// 如果total = 1，则无需分页，显示"[第1页] [共1页]"
			if (1==page.getTotalPage()) {
				pagination = "第"+page.getCurrentPage()+"页, 共" + 1 + "页";
			} else {
				// 到达最后一页,显示"[首页][上一页][尾页]"
				pagination +="第"+page.getCurrentPage()+"页, 共"+page.getTotalPage()+"页";
				pagination += "<a href='";
				pagination += url;
				if(url.indexOf("?")==-1)pagination += "?first=0&limit=" + page.getEveryPage()+"&total="+page.getTotalCount();
				else pagination += "&first=0&limit=" + page.getEveryPage()+"&total="+page.getTotalCount();
				pagination += "'> [首页]</a> <a href='";
				pagination += url;
				if(url.indexOf("?")==-1)pagination += "?first=" + ((page.getCurrentPage()-2)*page.getEveryPage()==0?0:(page.getCurrentPage()-2)*page.getEveryPage()) + "&limit=" + page.getEveryPage()+"&total="+page.getTotalCount();
				else
					pagination += "&first=" + ((page.getCurrentPage()-2)*page.getEveryPage()==0?0:(page.getCurrentPage()-2)*page.getEveryPage()) + "&limit=" + page.getEveryPage()+"&total="+page.getTotalCount();
				pagination += "'>[上一页]</a> [下一页] [尾页]";
			}
		} else {
			// 当前页是第一页
			if (1==page.getCurrentPage()) {
				// 第一页，显示"[首页] [下一页] [末页]"
				pagination +="第"+page.getCurrentPage()+"页, 共"+page.getTotalPage()+"页";
				pagination += " [首页] [上一页]<a href='";
				pagination += url;
				if(url.indexOf("?")==-1)pagination += "?first=" + (page.getCurrentPage()*page.getEveryPage()) + "&limit=" + page.getEveryPage()+"&total="+page.getTotalCount();
				else pagination += "&first=" + (page.getCurrentPage()*page.getEveryPage()) + "&limit=" + page.getEveryPage()+"&total="+page.getTotalCount();
				pagination += "'> [下一页]</a> <a href='";
				pagination += url;
				if(url.indexOf("?")==-1)pagination += "?first=" + ((page.getTotalPage()-1)*page.getEveryPage()) + "&limit=" + page.getEveryPage()+"&total="+page.getTotalCount();
				else pagination += "&first=" + ((page.getTotalPage()-1)*page.getEveryPage()) + "&limit=" + page.getEveryPage()+"&total="+page.getTotalCount();
				pagination += "'> [尾页]</a>";
			} else {
				// 不是第一页，显示"[首页] [上一页] [下一页] [末页]"
				pagination +="第"+page.getCurrentPage()+"页, 共"+page.getTotalPage()+"页";
				pagination += " <a href='";
				pagination += url;
				if(url.indexOf("?")==-1)pagination += "?first=0&limit=" + page.getEveryPage()+"&total="+page.getTotalCount();
				else pagination += "&first=0&limit=" + page.getEveryPage()+"&total="+page.getTotalCount();
				pagination += "'>[首页]</a> <a href='";
				pagination += url;
				if(url.indexOf("?")==-1)pagination += "?first=" + ((page.getCurrentPage()-2)*page.getEveryPage()==0?0:(page.getCurrentPage()-2)*page.getEveryPage()) + "&limit=" + page.getEveryPage()+"&total="+page.getTotalCount();
				else pagination += "&first=" + ((page.getCurrentPage()-2)*page.getEveryPage()==0?0:(page.getCurrentPage()-2)*page.getEveryPage()) + "&limit=" + page.getEveryPage()+"&total="+page.getTotalCount();
				pagination += "'>[上一页]</a><a href='";
				pagination += url;
				if(url.indexOf("?")==-1)pagination += "?first=" + (page.getCurrentPage()*page.getEveryPage()) + "&limit=" + page.getEveryPage()+"&total="+page.getTotalCount();
				else pagination += "&first=" + (page.getCurrentPage()*page.getEveryPage()) + "&limit=" + page.getEveryPage()+"&total="+page.getTotalCount();
				pagination += "'> [下一页]</a> <a href='";
				pagination += url;
				if(url.indexOf("?")==-1)pagination += "?first=" + ((page.getTotalPage()-1)*page.getEveryPage()) + "&limit=" + page.getEveryPage()+"&total="+page.getTotalCount();
				else pagination += "&first=" + ((page.getTotalPage()-1)*page.getEveryPage()) + "&limit=" + page.getEveryPage()+"&total="+page.getTotalCount();
				pagination += "'> [尾页]</a>";
			}
		}
        return pagination;
	}
}
