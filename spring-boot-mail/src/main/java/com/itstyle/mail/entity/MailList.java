package com.itstyle.mail.entity;

import com.itstyle.mail.common.model.EmailList;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "mail_list")
public class MailList implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String note;

    private Timestamp create_time;

    public MailList(){
        super();
    }

    public MailList(EmailList mailList){
        this.name=mailList.getName();
        this.email=mailList.getEmail();
        this.phone=mailList.getPhone();
        this.note=mailList.getNote();
//        this.create_time=mailList.getCreate_time();
        this.create_time=new Timestamp(new Date().getTime());
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setName(String name){
        this.name=name;
    }
    @Column(name = "name",nullable = false,length = 20)
    public String getName(){
        return name;
    }

    public void setEmail(String email){
        this.email=email;
    }
    @Column(name = "email",nullable = false,length = 500)
    public String getEmail(){
        return email;
    }

    public void setPhone(String phone){
        this.phone=phone;
    }
    @Column(name = "phone",length = 20)
    public String getPhone(){
        return phone;
    }

    public void setNote(String note){
        this.note=note;
    }
    @Column(name = "note")
    public String getNote(){
        return note;
    }

    public void setCreate_time(Timestamp create_time){
        this.create_time=create_time;
    }
    @Column(name = "create_time",nullable = false,length = 19)
    public Timestamp getCreate_time(){
        return create_time;
    }
}
