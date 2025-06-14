package com.bway.springproject.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bway.springproject.model.Student;
import com.bway.springproject.model.Teacher;
import com.bway.springproject.repository.StudentRepository;
import com.bway.springproject.repository.TeacherRepository;
import com.bway.springproject.service.StrudenService;

import jakarta.mail.Session;
import jakarta.servlet.http.HttpSession;

@Controller
 

public class UserContoller {
	@Autowired
	 StrudenService stdSvc;
	
 @Autowired
	StrudenService stdSer;
 @Autowired
 StudentRepository stRepo;
 @Autowired
 TeacherRepository tchRepo1;
	 @GetMapping("/")
	public String gotoindex() {
		return("index");
	}
	 @GetMapping("/Slogin")
	 public String gotologin() {
		 return "StudentLoginForm";
	 }
	 @GetMapping("/Ssignup")
	 public String gotosignup() {
		 return "StudentSignupForm";
	 }
	 @PostMapping("/signup")
	 public String aftesignup(@ModelAttribute Student std,@RequestParam("image") MultipartFile photoPath,Model model) throws IOException {
		 std.setPassword(DigestUtils.md5DigestAsHex(std.getPassword().getBytes()));
		 if(!photoPath.isEmpty()) {
			   String imgName=photoPath.getOriginalFilename();
			   String imgExp=photoPath.getOriginalFilename().split("\\.")[1];
			   long fsize=photoPath.getSize()/1024;
			   if(fsize>200) {
				   model.addAttribute("message","mas file size 200kb");
			   }
			   
				 std.setPhotoPath("/images/" + imgName);
			   Files.copy(photoPath.getInputStream(), Path.of("src/main/resources/static/images/" + photoPath.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			   System.out.println(photoPath);
			   model.addAttribute("message","upload sucess");
		   }  
	
		 stdSer.studentSignup(std);
		 
		 return "redirect:/Slogin";
	 }
	   
	   
	 
	 
	
	 @PostMapping("/login")
	 public String afterlogin(@ModelAttribute Student std,Model model,HttpSession session){
		 
		 std.setPassword(DigestUtils.md5DigestAsHex(std.getPassword().getBytes()));
		 Student st = stdSvc.userlogin(std.getUsername(), std.getPassword());

		    if (st!= null) { // Check if authentication is successful
		        session.setAttribute("activeuser", st); // Store the student in the session
		        session.setMaxInactiveInterval(300); // Set session timeout to 300 seconds
		        return "StudentHome"; // Redirect to StudentHome on success
		    } else {

		    // Handle login failure
		    model.addAttribute("msg", "Invalid username or password");
		    return "StudentLoginForm"; }// Redirect to login form on failure
	 }
	 @GetMapping("/download")
	 public String predownload(Model model) {
	     List<Teacher> teachers = tchRepo1.findAll();
	     model.addAttribute("teachers", teachers);
	     return "DownloadPage";
	 }
	 @GetMapping("/studentlogout")
	 public String logout() {
		 return"StudentLoginForm";
	 }
	
}
 