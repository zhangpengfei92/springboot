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
@Table(name = "integrals")
public class Integrals {
	
	@Id
    private Integer id;//积分签到

    private String username;//用户名
    
    @Column(name="integralType")
    private String integraltype;//签到或者是消费 0:签到 1:消费
    
    @Column(name="integralDate")
    private String integraldate;//签到日期
    
    @Column(name="integralNum")
    private Integer integralnum;//产生的积分
    
    @Column(name="createTime")
    private Date createtime;//创建时间
    
    @Column(name="delFlag")
    private Integer delflag;//删除标志  （0：未删除 1：已删除）

    private String description;//积分说明
    
    @Column(name="isCheck")
    private String ischeck;//审核状态    (0:  未审核  1：审核通过   2：审核不通过)
    
    @Column(name="systemUser")
    private String systemuser;//审核人

}