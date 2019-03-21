package com.jcl.gycms.entity;

import java.io.Serializable;
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
@Table(name = "msg_message")
public class Msg implements Serializable{
	@Id
    private Integer id;//主键ID
	
    private String titel;//消息内容
    
    @Column(name="sendPeople")
    private String sendpeople;//发送人数
    
    @Column(name="sendTime")
    private Date sendtime;//发送时间
    
    @Column(name="stateCode")
    private String statecode;//阅读状态0：未阅读，1：已阅读

    private Integer userid;//用户ID
    
}