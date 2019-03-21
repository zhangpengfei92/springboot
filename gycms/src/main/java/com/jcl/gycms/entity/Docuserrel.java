package com.jcl.gycms.entity;

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
@Table(name = "doctype")
public class Docuserrel {
	
	@Id
    private Integer id;

    private Integer docid;

    private Integer userid;
  
}