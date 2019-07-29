package com.itstyle.mail.web;
//import com.alibaba.dubbo.config.annotation.Reference;
import com.itstyle.mail.common.mapper.MailMapper;
import com.itstyle.mail.common.model.EmailList;
import com.itstyle.mail.entity.MailList;
import com.itstyle.mail.entity.OaEmail;
import com.itstyle.mail.repository.MailRepository;
import io.swagger.annotations.Api;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.itstyle.mail.common.model.Email;
import com.itstyle.mail.common.model.Result;
import com.itstyle.mail.service.IMailService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Api(tags ="邮件管理")
@Controller
@RequestMapping("/mail")
public class mailController {
	
//	@Reference(check = false)
    @Autowired
	private IMailService mailService;

	@Autowired
	private MailRepository mailRepository;

	@Autowired
	MailMapper mailMapper;


	@PostMapping("list")
	@ResponseBody
	public Result list(Email mail) {
		return mailService.listMail(mail);
	}

//	@RequestMapping("edit")
//	public String editInfo(Model model,Email mail){
//		List<OaEmail> emails=mailRepository.findAll();
//		model.addAttribute("emails",emails);
//		return "mail/edit";
//	}


	@PostMapping("/allList")
	@ResponseBody
	public Result mailList(EmailList mailList){
		return mailService.listMailList(mailList);
	}

	@PostMapping("/oneList/{id}")
	@ResponseBody
	public Result mailOneList(@PathVariable Long id){
		return mailService.oneList(id);
	}

	@GetMapping("delete/{mailId}")
	public String deleteMail(@PathVariable("mailId") Long mailId){
		mailMapper.deleteMail(mailId);
		return "mail/index";
	}


	@PostMapping("send")
	@ResponseBody
	public String sendMail(Email mail){
		try {
			mailMapper.insertEmail(mail);
			mailService.sendMail(mail);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}

	@GetMapping("send/{email}")
    public String sendMailList(@PathVariable("email") String email,Model model){
		email=email+".com";
	    model.addAttribute("email",email);
	    return "mail/sendMail";
    }

//    @GetMapping("/create")
//	public String createList(Model model){
//		model.addAttribute("mailList",new EmailList());
//		model.addAttribute("action","create");
//		return "mail/mailList";
//	}

	@PostMapping("/create")
	public String create(EmailList mailList){
		try{
			mailMapper.insertMailList(mailList);
		}catch (Exception e){
			e.printStackTrace();
			return "error";
		}
		return "mail/mailList";
	}

	@PostMapping("/update")
	@ResponseBody
	public String update(EmailList mailList){
		try{
			mailMapper.updateMailList(mailList);
		}catch (Exception e){
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}

	@GetMapping("/deleteList/{Id}")
	public String deleteList(@PathVariable("Id") Long id){
		mailMapper.deleteMailList(id);
		return "mail/mailList";
	}

}


