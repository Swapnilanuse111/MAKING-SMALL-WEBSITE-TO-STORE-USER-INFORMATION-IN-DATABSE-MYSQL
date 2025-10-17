<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration</title>
</head>
<body>

    <h2>New User Registration</h2>
    
    <% 
        // Display status message after registration attempt
        String status = (String) request.getAttribute("status");
        if (status != null) {
            if (status.equals("success")) {
                out.println("<p style='color:green;'>Registration Successful! You can now log in.</p>");
            } else if (status.equals("fail")) {
                out.println("<p style='color:red;'>Registration Failed. Please try a different email.</p>");
            }
        }
    %>

    <form action="RegisterServlet" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <input type="submit" value="Register">
    </form>

</body>
</html>