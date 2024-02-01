package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.Entity.Note;
import com.Helper.FactoryProvider;

public class SaveNoteServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
       public SaveNoteServlets() {
        super();
        
    }

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			// title, content fetch
			String title = request.getParameter("title");
		    String content = request.getParameter("content");
		    Note note = new Note(title,content,new Date());
		    
		    //hibernate save
		    
		  Session s=  FactoryProvider.getFactory().openSession();
		  Transaction tx = s.beginTransaction();
		  s.save(note);
		  tx.commit();
		 
		  s.close();
		  response.setContentType("text/html");
		  
		  PrintWriter out = response.getWriter();
		  out.println("<h1> Note is added successfully</h1>");
		  out.println("<h1 style='text-align:center;'><a href='all_notes.jsp'>View all</a></h1>");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
