package com.jcl.gycms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@ToString
@Table(name = "region")
public class Region {
	/*赛场ID*/
	@Id	
    private Integer id;
	
	/*赛场名称*/
	@Column(name="regionName")
    private String regionname;
	
	/*初始资金*/
    private Double initmoney;

    /*状态   0：暂未开赛 1：进行中 2：已结束*/
    private String status;
	
    /*类型 1:证券 2：高校 3：对接高校*/
    private String type;
	
    /*开始注册时间*/
	@Column(name="startRegister")
    private Date startregister;
	
	/*结束注册时间*/
	@Column(name="endRegister")
    private Date endregister;
	
	/*开始比赛时间*/
	@Column(name="startTime")
    private Date starttime;
	
	/*结束比赛时间*/
	@Column(name="endTime")
    private Date endtime;
	
	/*比赛总人数*/
	@Column(name="totalPeople")
    private Integer totalpeople;
	
	/*创建时间*/
    private Date createtime;
    
    /*创建人*/
    @Column(name="createBy")
    private Integer createby;

    /*简介*/
    private String description;
    
    /*是否公开 1:公开 2：不公开*/
    private String ispublic;
    
    /*验证信息*/
    @Column(name="rightRule")
    private String rightrule;
    
    /*比赛头像*/
    @Column(name="headPicture")
    private String headpicture;
    
    /*交易观点 1:有 2：无*/
    @Column(name="isIdea")
    private String isidea;
    
    /*是否默认选中 1:是 2：否*/
    @Column(name="isIdeaCheck")
    private String isideacheck;
    
    /*分析报告 1:需要 2：不需要*/
    @Column(name="isAnalysis")
    private String isanalysis;
    
    /*比赛类型 1:A股 2：融资融券*/
    private String regiontype;

}