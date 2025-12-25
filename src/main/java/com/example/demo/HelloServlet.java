package com.example.demo;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A simple servlet that responds with "Hello from HelloServlet" in plain text.
 * Used for demonstration and testing purposes.
 */
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // Set the content type to text/plain as expected by the tests
        response.setContentType("text/plain");

        // Get the writer and output the message
        PrintWriter writer = response.getWriter();
        writer.print("Hello from HelloServlet");
        writer.flush();
    }

    // Optional: handle POST the same way as GET (some tests might call it indirectly)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doGet(request, response);
    }
}