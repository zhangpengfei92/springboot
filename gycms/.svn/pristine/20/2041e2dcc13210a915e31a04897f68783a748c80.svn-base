package com.jcl.gycms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@ToString
@Table(name = "sys_user")
public class SysUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;

    private String username;

    private String password;

    private Integer status;

    private Date modifytime;
    
    @Column(name = "userStaus")
    private Integer userstaus;

    private Integer regin;
    /**
     * @return userid
     */
	public Integer getUserid() {
		return userid;
	}
	/**
     * @param userid
     */
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
    
    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return modifytime
     */
    public Date getModifytime() {
        return modifytime;
    }

    /**
     * @param modifytime
     */
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }
    /**
     * 
     * @return userstaus
     */
    
	public Integer getUserstaus() {
		return userstaus;
	}
	
	/**
	 * 
	 * @param userstaus
	 */
	public void setUserstaus(Integer userstaus) {
		this.userstaus = userstaus;
	}
	
	/**
	 * 
	 * @return regin
	 */
	public Integer getRegin() {
		return regin;
	}
	
	/**
	 * 
	 * @param regin
	 */
	public void setRegin(Integer regin) {
		this.regin = regin;
	}


}