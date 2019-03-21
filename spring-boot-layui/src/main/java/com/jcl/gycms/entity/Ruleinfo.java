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
@Table(name = "Ruleinfo")
public class Ruleinfo {
	
	@Id
    private Integer id;
	
	@Column(name="ruleNum")
    private String rulenum;
	
	@Column(name="ruleName")
    private String rulename;
	
	@Column(name="regionId")
    private Integer regionid;
	
	@Column(name="ruleNum_1")
    private String rulenum1;
	
	@Column(name="ruleNum_2")
    private String rulenum2;
	
	@Column(name="ruleNum_3")
    private String rulenum3;

}