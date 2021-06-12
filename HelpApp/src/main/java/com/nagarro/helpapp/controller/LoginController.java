package com.nagarro.helpapp.controller;

import java.util.Arrays;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.helpapp.demoentity.LoginInfo;
import com.nagarro.helpapp.demoentity.ProviderInfo;
import com.nagarro.helpapp.demoentity.ReceiverInfo;

@Controller
public class LoginController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/")
	public String login()
	{
		return "login.html";
	}
	@RequestMapping("login")
	public String loginpage()
	{
		return "login.html";
	}
	
	
	@RequestMapping("ProviderRegistration")
	public String goToProviderRegistration()
	{
		return "ProviderRegistration.html";
	}

	@RequestMapping("ReceiverRegistration")
	public String goToReceiverRegistration()
	{
		return "ReceiverRegistration.html";
	}
		
	@RequestMapping("validate")
	public String validateUser(ModelAndView model,HttpSession session,@RequestParam String userid,@RequestParam String password)
	{
		LoginInfo loginInfo = restTemplate.getForObject("http://localhost:9000/login/LoginInfo/"+userid, LoginInfo.class);
		System.out.println(loginInfo);
		if(loginInfo.getEmail().equals(userid) && loginInfo.getPassword().equals(password) && loginInfo.getType().equals("provider"))
		{
		 session.setAttribute("user", userid);
		 return "redirect:/providerView";
		}
		if(loginInfo.getEmail().equals(userid) && loginInfo.getPassword().equals(password) && loginInfo.getType().equals("receiver"))
		{
		session.setAttribute("user", userid);
		 return "redirect:/receiverView";
		}
		return "redirect:/login";
	}
	
	@RequestMapping(value = "logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/login";
	}
	
	@PostMapping("registerProvider")
	public ModelAndView registerProvider(HttpSession session,ProviderInfo info)
	{
		 HttpHeaders headers = new HttpHeaders();
	     headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	     HttpEntity<ProviderInfo> entity = new HttpEntity<ProviderInfo>(info,headers);
	     restTemplate.exchange("http://localhost:9002/provider/ProviderInfo", HttpMethod.POST, entity, String.class).getBody();
 
	     LoginInfo info1=new LoginInfo();
	     info1.setEmail(info.getEmail());
	     info1.setPassword(info.getPassword());
	     info1.setType("provider");
	     HttpEntity<LoginInfo> entity1=new HttpEntity<LoginInfo>(info1,headers);
	     restTemplate.exchange("http://localhost:9001/login/LoginInfo", HttpMethod.POST, entity1, String.class).getBody();
	     
	     
	     
	     ModelAndView mv=new ModelAndView("login");
	     session.setAttribute("msg", "User Registered");
	     return mv;
	}
	
	@PostMapping("registerReceiver")
	public ModelAndView registerReceiver(HttpSession session, ReceiverInfo info)
	{
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<ReceiverInfo> entity = new HttpEntity<ReceiverInfo>(info,headers);
	    restTemplate.exchange("http://localhost:9003/receiver/ReceiverInfo", HttpMethod.POST, entity, String.class).getBody();
	    
	    LoginInfo info1=new LoginInfo();
	    info1.setEmail(info.getEmail());
	    info1.setPassword(info.getPassword());
	    info1.setType("receiver");
	    HttpEntity<LoginInfo> entity1=new HttpEntity<LoginInfo>(info1,headers);
	    restTemplate.exchange("http://localhost:9001/login/LoginInfo", HttpMethod.POST, entity1, String.class).getBody();
	 
	    ModelAndView mv=new ModelAndView("login");
	    session.setAttribute("msg", "User Registered");
	    return mv;    
	}
	
}
