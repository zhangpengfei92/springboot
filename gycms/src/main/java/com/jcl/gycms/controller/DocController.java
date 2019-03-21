/**
 * Copyright (C) 2009 武汉金策略信息科技有限公司
 *
 * 版权所有。
 *
 * 类名　　  : @DocController.java
 * 功能概要  : 
 * 做成日期  : @2019年3月5日
 * 修改日期  :
 */
package com.jcl.gycms.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.jcl.gycms.entity.Doccolumn;
import com.jcl.gycms.entity.Doccontent;
import com.jcl.gycms.entity.Msg;
import com.jcl.gycms.entity.Userinfo;
import com.jcl.gycms.service.DoccolumnService;
import com.jcl.gycms.service.DoccontentService;
import com.jcl.gycms.uitl.page.PaginationContext;
import com.jcl.gycms.uitl.page.cPageInfo;
import com.jcl.gycms.uitl.result.CodeMsg;
import com.jcl.gycms.uitl.result.Result;
import com.jcl.gycms.uitl.result.ResultUed;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

;

/** 
 * @author zpf
 * @version 1.0
 */
@Controller
@RequestMapping("/doc")
public class DocController {
	
	@Value(value = "${gycms.imgUrl}")
    private String imgUrl;

    @Value(value = "${gycms.showUrl}")
    private String showUrl;
		
	private static Logger logger = LoggerFactory.getLogger(DocController.class);
	
	@Autowired
	private  DoccontentService doccontentService;
	
	@Autowired
	private DoccolumnService doccolumnService;
	
	@RequestMapping("/skip/{id}")
    public String Skip(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("id", id);
        return "doc/list";
    }
	
	@RequestMapping("/list/{id}")
    @ResponseBody
    public cPageInfo<Doccontent> list(@PathVariable("id") Integer id, String startDate, String endDate,String title) {				
		Example example=new Example(Doccontent.class);
		Criteria criteria = example.createCriteria();
		if (!StringUtils.isNotBlank(title)){
			criteria.andLike("title", title);
        }
		if(StringUtils.isBlank(endDate)||StringUtils.isNotBlank(startDate)){
			criteria.andBetween("createtime", startDate, new Date());
        }else if(StringUtils.isNotBlank(endDate)||StringUtils.isBlank(startDate)){        
        	criteria.andBetween("createtime", "1970-1-1 8:0:0", endDate);
        }	
		criteria.andEqualTo("columnid", id);
		PageHelper.startPage(PaginationContext.getPageNum(), PaginationContext.getPageSize());
		example.setOrderByClause("createtime desc");
		List<Doccontent> list = doccontentService.selectByExample(example);		
		return new cPageInfo<Doccontent>(list);       
    }
	
	  @RequestMapping("/alert/{path}/{id}")
	    public String Alert(@PathVariable(value = "path") String path, Model model,@PathVariable("id") String id) {
	        if("edit".equals(path)){        
	        	Doccolumn doccolumn = doccolumnService.selectByKey(Integer.parseInt(id));
	        	model.addAttribute("doccolumnid", doccolumn.getId());
	        }
	        
	        if("update".equals(path)){
	        	
	        	Doccontent doc = doccontentService.selectByKey(Integer.parseInt(id));
	        	model.addAttribute("doc", doc);
	        	model.addAttribute("doccolumnid", doc.getColumnid());
	        	path="edit";
	        }
	        
	        return "doc/alert/doc-" + path;
	    }
	
    @RequestMapping("/add")
    @ResponseBody
    public Result addDoc(Doccontent doccontent){
    	if(StringUtils.isBlank(doccontent.getTitle())){
    		
    		return Result.error(CodeMsg.INFO(400, "公告标题不能为空")); 
    		
    	  }else if(StringUtils.isBlank(doccontent.getSummary())) {
    		
    		return Result.error(CodeMsg.INFO(400, "公告标题不能为空"));
 
    	  }else if(StringUtils.isBlank(doccontent.getContent())){
    		
    		return Result.error(CodeMsg.INFO(400, "公告内容不能为空"));
    		
    	  }
    	if(doccontent.getId()==null) {
    		doccontent.setCreatetime(new Date());
        	doccontent.setIspublish("0");
        	doccontent.setUserid(1);    	
        	int num = doccontentService.saveSelective(doccontent);
        	if(num>0) {
        		return Result.success();
        	}else {
        		return Result.error(CodeMsg.INFO(400, "新增公告内容成功"));
        	}
    	}else if(doccontent.getId()>0){
    		int num = doccontentService.updateByKeySelective(doccontent);
        	if(num>0) {
        		return Result.success();
        	}else {
        		return Result.error(CodeMsg.INFO(400, "修改公告内容成功"));
        	}
    	}
		return Result.error(CodeMsg.INFO(400, "状态错误"));
    		
    }
    
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable("id") Integer id){
    	int num = doccontentService.delete(id);
    	if(num>0) {
    		return Result.success();
    	}else {
    		return Result.error(CodeMsg.INFO(400, "删除公告内容成功"));
    	}    	
    }
    
    @RequestMapping("/ispublic/{num}/{id}")
    @ResponseBody
    public Result ispublic(@PathVariable("num") Integer num,@PathVariable("id") Integer id){
    	Doccontent doc = doccontentService.selectByKey(id);
    	String msg="";
    	if(num==1){
    		doc.setIspublish("1");
    		msg="发布公告成功";
    	}else if(num==0) {
    		doc.setIspublish("0");
    		msg="取消发布成功";
    	}
    	
    	doccontentService.updateByKeySelective(doc);
    	if(num>0) {
    		return Result.success();
    	}else {
    		return Result.error(CodeMsg.INFO(400, msg));
    	}    	
    }
    
    @RequestMapping("/addssss")
    @ResponseBody
    public Result addDocs(Msg msg){
    	
    	if(StringUtils.isBlank(msg.getTitel())){
    		
    		return Result.error(CodeMsg.INFO(400, "发送消息内容不能为空"));
    		
    	  }else if(StringUtils.isBlank(msg.getUserid().toString())) {
    		
    		return Result.error(CodeMsg.INFO(400, "发送用户对象不能为空"));  
    		  
    	}    	
    	msg.setSendpeople("-1");
    	msg.setStatecode("0");
    	msg.setSendtime(new Date());
    	int num = 0;
    	if(num>0) {
    		return Result.success();
    	}else {
    		return Result.error(CodeMsg.INFO(400, "发送消息失败"));
    	}	   	
    }
    @RequestMapping("/config")
    public  String  config(String action) {    
    	if(action.equals("config")) {
    		return "redirect:/static/js/ueditor/jsp/config.json";
    	}else if(action.equals("upload")){
    		return "forward:/doc/upload";
    	}
    	return "";
    }
     
    
  //上传图片
  	@RequestMapping("/upload")
  	@ResponseBody
  	public  ResultUed upload(@RequestParam("upfile") MultipartFile mf) {
  			HashMap<String, Object> res =  new HashMap<String, Object>();
  				 
  			try {
  				if(!mf.isEmpty()) {						  						  						  						  
  				    //上传文件名
  				    String filename = mf.getOriginalFilename();				            
  				    //将上传文件保存到一个目标文件当中				     
  				    String newfile=getNewNameByDate()+filename.substring(filename.lastIndexOf("."));		               
  				    File file = new File(imgUrl,newfile);
  				    mf.transferTo(file);
  				    String fileurl=showUrl+newfile;
  				    res.put("src", fileurl);
  				    return ResultUed.getInstance().uploadSuccess(fileurl);
  				   		    		
  			} else {
  				return  ResultUed.getInstance().uploadError();
  			}					 
  			} catch (Exception e) {
  				return  ResultUed.getInstance().uploadError();
  			}			
  	}
  	
  	
	public static String getNewNameByDate(){
		SimpleDateFormat format
			= new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return format.format(new Date());
	}
}
