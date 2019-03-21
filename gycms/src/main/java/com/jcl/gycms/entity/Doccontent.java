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
@Table(name = "doccontent")
public class Doccontent {
	
	/*文章内容id*/
	@Id
    private Integer id;
	
	/*文章栏目id*/
    private Integer columnid;
    
    /*关联用户id 发布作者id*/
    private Integer userid;
    
    /*标题*/
    private String title;
    
    /*简介*/
    private String summary;
    
    /*文章内容id*/
    @Column(name="docParentId")
    private Integer docparentid;
    
    /*发表时间*/
    private Date createtime;
    
    /*是否发布 0：未发布 1： 已发布*/
    private String ispublish;
    
    /*短期策略*/
    @Column(name="shortTerm")
    private String shortterm;
    
    /*中期策略*/
    @Column(name="mediumTerm")
    private String mediumterm;
    
    /*股票名称1*/
    @Column(name="stockName_1")
    private String stockname1;
    
    /*股票名称2*/
    @Column(name="stockName_2")
    private String stockname2;
    
    /*股票代码1*/
    @Column(name="stockCode_1")
    private String stockcode1;
    
    /*股票代码2*/
    @Column(name="stockCode_2")
    private String stockcode2;
    
    /*扣除积分*/
    @Column(name="deductScore")
    private String deductscore;
    
    /*置顶*/
    @Column(name="isTop")
    private String istop;
    
    /*图片地址*/
    @Column(name="photoPath")
    private String photopath;
    
    private Integer type;
    
    /*比赛ID*/
    @Column(name="gameId")
    private Integer gameid;
    
    
    private Integer rootid;
    
    /*文章内容*/
    private String content;
    
    /*短线指标*/
    @Column(name="stubContent")
    private String stubcontent;
    
    /*长线指标*/
    @Column(name="longContent")
    private String longcontent;
    
    private Doccolumn doccolumn;
}