package com.jcl.gycms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.sql.JDBCType;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@ToString
@Table(name = "userinfo")
public class Userinfo implements Serializable {
	
	/*用户主键ID*/
	@Id
	private Integer id;

    private String username;//用户名

    private String password;//密码

    private Integer sex;//性别

    private Date expiredate;

    private Integer admin;

    private Integer minver;

    private Date lasttime;//最后登录时间

    private String info;

    private String online;

    private String ip;

    private String netcard;

    private Date starttime;

    private Date endtime;

    private Integer lastmonth;

    private Integer huming;

    private String name;

    private String telephone;//电话号码

    private Double paymoney;

    private String ucname;//昵称

    private Date paytime;

    private String seller;

    private String funcinfo;

    private Integer usertype;//用户类型 1：普通用户 2：营业部用户、投顾用户
    
    @Column(name="updatedAt")
    private Date updatedat;//注册时间

    private String flag;

    private String syncinfo;
    
    @Column(name="clientFrom")
    private String clientfrom;

    private String openid;//营业部编号

    private String registfrom;

    private Integer hasmobile;
    
    @Column(name="headPicture")
    private String headpicture;//用户图像

    private Integer integration;//用户积分
    
    @Column(name="Year")
    private Integer year;
    
    @Column(name="Month")
    private Integer month;
    
    @Column(name="Day")
    private Integer day;
    
    @Column(name="City")
    private String city;
    
    @Column(name="Town")
    private String town;
    
    @Column(name="Email")
    private String email;
    
    @Column(name="Phone")
    private String phone;
    
    @Column(name="QQ")
    private String qq;
    
    @Column(name="Education")
    private String education;
    
    @Column(name="Profession")
    private String profession;
    
    @Column(name="Professioninfo")
    private String professioninfo;
    
    @Column(name="Capitalposition")
    private String capitalposition;
    
    @Column(name="Operatingstyle")
    private String operatingstyle;
    
    @Column(name="Pickingstyle")
    private String pickingstyle;
    
    @Column(name="Investingexperience")
    private String investingexperience;
    
    @Column(name="Investingproducts")
    private String investingproducts;
    
    @Column(name="ruleName")
    private String rulename;
    
    @Column(name="ruleNum")
    private String rulenum;
    
    @Column(name="ruleNum_1")
    private String rulenum1;
    
    @Column(name="ruleNum_2")
    private String rulenum2;
    
    @Column(name="ruleNum_3")
    private String rulenum3;

}