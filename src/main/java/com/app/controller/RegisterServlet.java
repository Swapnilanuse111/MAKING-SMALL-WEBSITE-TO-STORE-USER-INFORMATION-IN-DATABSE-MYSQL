package com.app.controller;

import com.app.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;

import com.app.dao.UserDAO;
import java.io.IOException;


// Maps the Servlet to the URL pattern /registerServlet
@WebServlet("/RegisterServlet")
public class RegisterServlet extends jakarta.servlet.http.HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) 
            throws jakarta.servlet.ServletException, IOException {
        
        // 1. Get parameters from the request
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password"); 
        
        // **NOTE:** In a real app, you must hash the password here before saving!

        // 2. Create the Model object (User Bean)
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password); // Assuming simple save for this example

        // 3. Call the Model's DAO method
        int result = userDAO.registerUser(user);

        // 4. Set status and forward back to the view
        if (result > 0) {
            request.setAttribute("status", "success");
        } else {
            // This might mean a duplicate email (due to UNIQUE constraint) or a DB error
            request.setAttribute("status", "fail"); 
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);
    }
}