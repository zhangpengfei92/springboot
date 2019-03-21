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
@Table(name = "activitycontent")
public class Activitycontent {
	
	@Id
    private Integer id;

    private Integer columnid;
    
    @Column(name="photoPath")
    private String photopath;
    
    @Column(name="linkPath")
    private String linkpath;
    
    private Integer packageid;
    
    private String status;

    private Date createtime;

    private Integer userid;

    private String description;
   
}