package com.itstyle.mail.common.model;

import jdk.nashorn.internal.ir.LoopNode;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;


/**
 * EmailList封装类
 * 创建者 杜金川
 * 创建时间 2019年7月25日
 *
 */
public class EmailList implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String note;
    private Timestamp create_time;
    private HashMap<String, String> kvMap;// 自定义参数


    public EmailList(){
        super();
    }

    public EmailList(Long id,String name, String email, String phone, String note, Timestamp create_time, HashMap<String,String> kvMap){
        super();
        this.id=id;
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.note=note;
        this.create_time=create_time;
        this.kvMap=kvMap;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public Timestamp getCreate_time(){
        return create_time;
    }
    public void setCreate_time(Timestamp create_time){
        this.create_time=create_time;
    }
    public HashMap<String, String> getKvMap() {
        return kvMap;
    }
    public void setKvMap(HashMap<String, String> kvMap) {
        this.kvMap = kvMap;
    }
}
