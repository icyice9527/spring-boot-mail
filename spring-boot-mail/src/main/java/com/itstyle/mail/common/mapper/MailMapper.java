package com.itstyle.mail.common.mapper;

import com.itstyle.mail.common.model.Email;
import com.itstyle.mail.common.model.EmailList;
import com.itstyle.mail.entity.MailList;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MailMapper {

    @Delete("delete from oa_email where oa_email.id=#{id}")
    int deleteMail(Long id);

    @Insert("insert into oa_email(receive_email,subject,content,template) values (#{email},#{subject}," +
            "#{content},#{template})")
    int insertEmail(Email mail);

    @Select("select * from mail_list")
    List<MailList> queryMailList();

    @Select("select * from mail_list where id=#{id}")
    EmailList queryMailListById(Long id);

    @Insert("insert into mail_list(name,email,phone,note) values (#{name},#{email},#{phone},#{note})")
    int insertMailList(EmailList mailList);

    @Update("update mail_list set name=#{mailList.name},email=#{mailList.email},phone=#{mailList.phone}," +
            "note=#{mailList.note} where id=#{mailList.id}")
    int updateMailList(@Param("mailList") EmailList mailList);

    @Delete("delete from mail_list where id=#{id}")
    int deleteMailList(Long id);
}
