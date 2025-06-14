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

import com.bway.springproject.model.Note;
import com.bway.springproject.model.Teacher;
import com.bway.springproject.service.NoteService;
import com.bway.springproject.service.TeacherService;

import jakarta.servlet.http.HttpSession;

@Controller

public class TeacherController {
	@Autowired
	TeacherService techSer;
	@Autowired
	NoteService ntserv;
	
	
	@GetMapping("/tlogin")
	
	public String teacherlogin() {
		return"TeacherLoginForm";
	}
	@GetMapping("/tsignup")
	public String teacherSignup() {
		return "TeacherSignup";
	}
	 @PostMapping("/tsignup")
	 public String postteachersignup(@ModelAttribute Teacher tch, Model model) {
		 
		 tch.setPassword(DigestUtils.md5DigestAsHex(tch.getPassword().getBytes()));
		 
		 techSer.teacherSignup(tch);
		 
		 return "redirect:/tlogin";
	 }
	 @PostMapping("/tlogin")
	 
	 public String postlogin(@ModelAttribute Teacher tch ,Model model,HttpSession session) {
		 tch.setPassword(DigestUtils.md5DigestAsHex(tch.getPassword().getBytes()));
		 Teacher tc = techSer.teachlogin(tch.getUsername(), tch.getPassword(),tch.getCode());

		    if (tc!= null) { // Check if authentication is successful
		        session.setAttribute("activeus", tc); // Store the student in the session
		        session.setMaxInactiveInterval(300); // Set session timeout to 300 seconds
		        return "TeacherHomePage"; // Redirect to StudentHome on success
		    } else {

		    // Handle login failure
		    model.addAttribute("msg", "Invalid username or password");
		    return "TeacherLoginForm"; }// Redirect to login form on failure
	
	 }
	 @GetMapping("/upload")
	 public String preupload() {
		 return "Uploadpage";
	 }
	 
	 @PostMapping("/upload")
	 public String postupload(@ModelAttribute Note nt,@RequestParam("docx") MultipartFile filepath, Model model,HttpSession session) throws IOException {
		 
		 Teacher teacher = (Teacher) session.getAttribute("activeus");

		    // 2. Validate
		    if (teacher == null) {
		        throw new RuntimeException("No teacher found in session");
		    }
		 if(!filepath.isEmpty()) {
			   String docxName=filepath.getOriginalFilename();
			   String fileExp=filepath.getOriginalFilename().split("\\.")[1];
			    			   
				 nt.setFilepath("/Notes/" + docxName);
			   Files.copy(filepath.getInputStream(), Path.of("src/main/resources/static/Notes/" + filepath.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			   System.out.println(filepath);
			   model.addAttribute("message","upload sucess");// variable pathauna display garne thau ma 
		   }  
		 
		 
		 nt.setTeacher(teacher);
		 
	ntserv.NoteSignup(nt);
		 return "Uploadpage";
	 }
	 
	 @GetMapping("/update")
	 public String preupdate(HttpSession session, Model model) {
		 
		  Teacher teacher = (Teacher) session.getAttribute("activeus");  // Retrieve the logged-in teacher from session

	 
	            // Fetch the notes uploaded by this teacher using their ID
	            List<Note> notes = ntserv.findNotes(teacher.getId());
	            model.addAttribute("notes", notes);  // Pass the notes to the view
	       	 return "UpdatePage";  // The name of the view (HTML page)
	        
	    
	
	 }
	 @PostMapping("/delete")
	 public String postdelete(@RequestParam("id") int id) {
	     ntserv.deletefile(id);  // delete the note using the provided id
	     return "redirect:/update"; // or the correct redirect URL after deletion
	 }
	 
	@GetMapping("/teacherlogout")
	public String logout() {
		return "TeacherLoginForm";
	}
	 

}
