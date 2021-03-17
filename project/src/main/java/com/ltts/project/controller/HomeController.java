package com.ltts.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ltts.project.Dao.MemberDao;
import com.ltts.project.model.Member;

@RestController
public class HomeController {
	
	@Autowired
	MemberDao md;
	
	@RequestMapping("/hi")
	public String firstMethod() {
		return "Hello SpringBoot";
	}
	
	@RequestMapping(" ")
	public ModelAndView secondMethod() {
		return new ModelAndView("index");
	}
	@RequestMapping("/registration")
	public ModelAndView registerUser() {
		return new ModelAndView("registration");
	}
	
	@RequestMapping(value="adduser", method=RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest req, Model model) {
		ModelAndView mv=null;
		String name=req.getParameter("uname");
		String pass=req.getParameter("pass");
		String email=req.getParameter("email");
		String mobile=req.getParameter("mobile");
		
	//	ApplicationContext ac=new ClassPathXmlApplicationContext();
		Member m=new Member(name,pass,email,mobile);
		System.out.println("***** INSIDE CONTROLLER ****"+m);
		boolean b=md.InsertMember(m);
		if(b==false) {
			mv=new ModelAndView("success");
			model.addAttribute("msg", "Successfully Inserted.. ");
		}
		else {
			mv=new ModelAndView("error");
			model.addAttribute("msg", "Error due to Connection");
			
		}
		/*
		 * try { b=md.InsertMember(m); mv=new ModelAndView("success"); } catch(Exception
		 * e) {
		 * 
		 * mv=new ModelAndView("error"); }
		 */
		
		
		return mv;
	}

}
