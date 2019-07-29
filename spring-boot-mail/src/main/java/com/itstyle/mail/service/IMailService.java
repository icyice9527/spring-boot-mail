package com.itstyle.mail.service;

//import com.alibaba.dubbo.config.annotation.Service;

import com.itstyle.mail.common.model.Email;
import com.itstyle.mail.common.model.EmailList;
import com.itstyle.mail.common.model.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IMailService {
	 /**
	  * 纯文本
	  * @Author  科帮网
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年7月20日
	  * 更新日志
	  * 2017年7月20日  科帮网 首次创建
	  */

	 void send(Email mail) throws Exception;
//	 void send(@Param("receiveEmail") String receiveEmail,@Param("subject") String subject,
//			   @Param("content") String content,@Param("template") String template) throws Exception;

	/**
	 * 纯文本
	 * @Author  杜金川
	 * @param mail
	 * @throws Exception  void
	 * @Date	2019年7月24日
	 * 更新日志
	 * 2019年7月24日  杜金川 首次创建
	 */

//	 void sendAttachmentsMail(@Param("mail") Email mail)throws Exception;

	/**
	 * 纯文本
	 * @Author  杜金川
	 * @param mail
	 * @throws Exception  void
	 * @Date	2019年7月25日
	 * 更新日志
	 * 2019年7月25日  杜金川 首次创建
	 */
	 void sendMail(Email mail);

	/**
	 * 纯文本
	 * @Author  杜金川
	 * @param mail
	 * @throws Exception  void
	 * @Date	2019年7月25日
	 * 更新日志
	 * 2019年7月25日  杜金川 首次创建
	 */
	void checkMail(Email mail);

	 /**
	  * 富文本
	  * @Author  科帮网
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年7月20日
	  * 更新日志
	  * 2017年7月20日  科帮网 首次创建
	  *
	  */
	  void sendHtml(Email mail) throws Exception;
	 /**
	  * 模版发送 freemarker
	  * @Author  科帮网
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年7月20日
	  * 更新日志
	  * 2017年7月20日  科帮网 首次创建
	  *
	  */
	  void sendFreemarker(Email mail) throws Exception;
	 /**
	  * 模版发送 thymeleaf(弃用、需要配合模板)
	  * @Author  科帮网
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年7月20日
	  * 更新日志
	  * 2017年7月20日  科帮网 首次创建
	  *
	  */
	 void sendThymeleaf(Email mail) throws Exception;
	 /**
	  * 队列
	  * @Author  科帮网
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年8月4日
	  * 更新日志
	  * 2017年8月4日  科帮网 首次创建
	  *
	  */
	 void sendQueue(Email mail) throws Exception;
	 /**
	  * Redis 队列
	  * @Author  科帮网
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年8月13日
	  * 更新日志
	  * 2017年8月13日  科帮网 首次创建
	  *
	  */
	 void sendRedisQueue(Email mail) throws Exception;

	 Result listMailList(EmailList mailList);

	 Result oneList(Long id);
	 
	 Result listMail(Email mail);
}
