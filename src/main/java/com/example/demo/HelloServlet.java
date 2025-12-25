package com.example.demo;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Simple servlet that responds with "Hello from HelloServlet" in plain text.
 * Matches the expectations of the unit tests.
 */
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // Set content type as expected by the test
        response.setContentType("text/plain");

        // Write the exact message the tests are looking for
        PrintWriter writer = response.getWriter();
        writer.print("Hello from HelloServlet");
        writer.flush();
    }

    // Optional: handle POST the same way (not required by tests but good practice)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doGet(request, response);
    }
}