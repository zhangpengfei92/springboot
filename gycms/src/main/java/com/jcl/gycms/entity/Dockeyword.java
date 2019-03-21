package com.jcl.gycms.entity;

import java.util.Date;

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
@Table(name = "dockeyword")
public class Dockeyword {
	
	@Id
    private Integer id;
	
	
    private String keyword;
    
    
    private Date createtime;
    
    
}