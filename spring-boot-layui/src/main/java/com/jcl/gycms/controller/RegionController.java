/**
 * Copyright (C) 2009 武汉金策略信息科技有限公司
 *
 * 版权所有。
 *
 * 类名　　  : @IntegralController.java
 * 功能概要  : 
 * 做成日期  : @2019年2月27日
 * 修改日期  :
 */
package com.jcl.gycms.controller;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.github.pagehelper.PageHelper;
import com.jcl.gycms.entity.Region;
import com.jcl.gycms.entity.Ruleinfo;
import com.jcl.gycms.service.RegionService;
import com.jcl.gycms.service.RuleinfoService;
import com.jcl.gycms.uitl.excle.ExcelUtils;
import com.jcl.gycms.uitl.page.PaginationContext;
import com.jcl.gycms.uitl.page.cPageInfo;
import com.jcl.gycms.uitl.result.CodeMsg;
import com.jcl.gycms.uitl.result.Result;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/** 
 * @author zpf
 * @version 1.0
 */
@RequestMapping("/region")
@Controller
public class RegionController {
	 private static Logger logger = LoggerFactory.getLogger(RegionController.class);
	
	@Value(value = "${gycms.imgUrl}")
    private String imgUrl;

    @Value(value = "${gycms.showUrl}")
    private String showUrl;
	
	@Autowired
	private RegionService regionservice;
	
	@Autowired
	private RuleinfoService ruleinfoService;
	
	@RequestMapping("/skip")
    public String Skip() {
        return "region/list";
    }
	
	@RequestMapping("/list")
    @ResponseBody
    public cPageInfo<Region> list(Region region, String startDate, String endDate) {
		Example example=new Example(Region.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(region.getRegionname())){
            criteria.andLike("regionname","%"+region.getRegionname()+"%");
        }
        if (!StringUtils.isBlank(startDate)&&!StringUtils.isBlank(endDate)) {
        	criteria.andBetween("createtime", startDate, endDate);
        }else if(!StringUtils.isBlank(startDate)&&StringUtils.isBlank(endDate)){
        	criteria.andBetween("createtime", startDate, new Date());
        }else if(StringUtils.isBlank(startDate)&&!StringUtils.isBlank(endDate)){
        	criteria.andBetween("createtime", "1970-1-1 8:0:0",endDate);
        }
        example.setOrderByClause("createtime desc");
        PageHelper.startPage(PaginationContext.getPageNum(), PaginationContext.getPageSize());
        List<Region> list = regionservice.selectByExample(example);
        return new cPageInfo<Region>(list);      
    }
	
	

	@RequestMapping("/alert/{path}")
    public String Alert(@PathVariable(value = "path") String path, Model model, String id) {
        if("updata".equals(path)||"importuser".equals(path)||"importdata".equals(path)||"rulelist".equals(path)){        
			Region region = regionservice.selectByKey(Integer.parseInt(id));
            model.addAttribute("region",region);
        }
       if("updata".equals(path)) {
    	   path="add" ;
       }
        return "region/alert/region-" + path;
    }
	
	@RequestMapping("/addregion")
	@ResponseBody
	public Result addregion(Region region){
    	if(StringUtils.isBlank(region.getRegionname())){
    		
    		return Result.error(CodeMsg.INFO(400, "比赛的名称不能为空"));
    		
    	 }else if(StringUtils.isBlank(region.getHeadpicture())){
     		
     		return Result.error(CodeMsg.INFO(400, "比赛图标不能为空"));
     		
     	 }else if(StringUtils.isBlank(region.getInitmoney().toString())&&region.getInitmoney()>0){
      		
      		return Result.error(CodeMsg.INFO(400, "比赛初始资金不能为空"));
      		
      	 }else if(StringUtils.isBlank(region.getStartregister().toString())){
      		
      		return Result.error(CodeMsg.INFO(400, "比赛开始注册时间不能为空"));
      		
      	 }else if(StringUtils.isBlank(region.getEndregister().toString())){
      		
      		return Result.error(CodeMsg.INFO(400, "比赛结束注册时间不能为空"));
      		
      	 }else if(StringUtils.isBlank(region.getStarttime().toString())){
      		
      		return Result.error(CodeMsg.INFO(400, "比赛开始时间不能为空"));
      		
      	 }else if(StringUtils.isBlank(region.getEndtime().toString())){
      		
      		return Result.error(CodeMsg.INFO(400, "比赛结束时间不能为空"));
      		
      	 }else if(StringUtils.isBlank(region.getTotalpeople().toString())&&region.getTotalpeople()>0){
      		
      		return Result.error(CodeMsg.INFO(400, "比赛人数不能为空"));
      		
      	 }else if(StringUtils.isBlank(region.getDescription())){
      		
      		return Result.error(CodeMsg.INFO(400, "比赛介绍不能为空"));
      		
      	 }
    	region.setCreatetime(new Date());
    	region.setCreateby(1);
    	int num = regionservice.saveSelective(region);
    	if(num>0) {
    		return Result.success();
    	}else {
    		return Result.error(CodeMsg.INFO(400, "保存比赛信息失败"));
    	}	   	
    }
	//上传图片
	@RequestMapping("/upload")
	@ResponseBody
	public  Result upload(@RequestParam("file") MultipartFile mf) {
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
				    return Result.uploadsuccess(res);
				   		    		
			} else {
				return Result.error(CodeMsg.INFO(400, "上传图片为空"));
			}					 
			} catch (Exception e) {
				return Result.error(CodeMsg.INFO(400, "上传图片失败"));
			}			
	}
	
	//上传图片
	@RequestMapping("/importdata/{regionid}")
	@ResponseBody
	public  Result importdata(@RequestParam("file") MultipartFile uploadFile,@PathVariable(value="regionid") String regionid) {
		synchronized (Object.class) {
			try {
				int success =0;
				int fail=0;
				InputStream is = uploadFile.getInputStream();	
				List<List<String>> list = ExcelUtils.getInstance().readExcel2List(is);
				list.remove(0);
				for (List<String> list2 : list) {				
					Ruleinfo ruleinfo = new Ruleinfo();
					ruleinfo.setRulename(list2.get(3));
					ruleinfo.setRulenum(list2.get(0));
					ruleinfo.setRulenum1(list2.get(1));
					ruleinfo.setRulenum2(list2.get(2));
					ruleinfo.setRegionid(Integer.parseInt(regionid));
					Ruleinfo one = ruleinfoService.selectOne(ruleinfo);
					if(one==null) {
						int num = ruleinfoService.saveSelective(ruleinfo);
						if(num>0) {
							success++;
					    }
					}else{
						fail++;
					}	
				}
				if(success==list.size()) {
					return Result.success();
				  }else if(success>list.size()){
					return Result.error(CodeMsg.INFO(400, "重复导入"+(success-list.size())+"条数据，请检查"));
				  }else if(fail>0&&fail<list.size()){				  
					  return Result.error(CodeMsg.INFO(300, "其中有"+fail+"条数据已存在，未重新导入"));
				  }else if(fail==list.size()){				  
					  return Result.error(CodeMsg.INFO(400, "导入数据有共"+list.size()+"条,"+fail+"条数据都已存在，无法重新导入"));
				  }				
				} catch (Exception e) {
					e.printStackTrace();
					return Result.error(CodeMsg.INFO(400, e+"-导入数据异常"));			
				}
				return Result.error(CodeMsg.INFO(400, "导入数据失败"));
		}
		}
	
	@RequestMapping("/rulelist/{id}")
    @ResponseBody
    public cPageInfo<Ruleinfo> relulist(@PathVariable String id,String rulename) {
		Example example=new Example(Ruleinfo.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(rulename)){
            criteria.andLike("rulename","%"+rulename+"%");
		}
		criteria.andEqualTo("regionid", id);
        example.setOrderByClause("id desc");
        PageHelper.startPage(PaginationContext.getPageNum(), PaginationContext.getPageSize());
        List<Ruleinfo> list = ruleinfoService.selectByExample(example);
        return new cPageInfo<Ruleinfo>(list);      
    }
	
	
	public static String getNewNameByDate(){
		SimpleDateFormat format
			= new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return format.format(new Date());
	}
}
