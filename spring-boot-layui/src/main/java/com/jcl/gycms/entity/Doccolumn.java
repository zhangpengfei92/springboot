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
@Table(name = "doccolumn")
public class Doccolumn {
	
	@Id
    private Integer id;

    private String name;

    private Integer typeid;
    
    @Column(name="showName")
    private String showname;
        
    private String sort;

    private Integer packageid;

    private String acronym;

    private String description;
   
}