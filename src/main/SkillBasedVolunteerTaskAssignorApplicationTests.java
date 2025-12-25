package com.example.demo;

import com.example.demo.servlet.HelloServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

public class SkillBasedVolunteerTaskAssignorApplicationTests {

    // ================================
    //        HELLO SERVLET TESTS
    // ================================

    @Test
    public void testHelloServletResponse() throws Exception {
        HelloServlet servlet = new HelloServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getMethod()).thenReturn("GET");

        StringWriter writer = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(writer));

        servlet.service(request, response); // ✅ CORRECT

        Assert.assertEquals(writer.toString(), "Hello from HelloServlet");
    }

    @Test
    public void testHelloServletContentType() throws Exception {
        HelloServlet servlet = new HelloServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getMethod()).thenReturn("GET");
        when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));

        servlet.service(request, response); // ✅ CORRECT

        verify(response).setContentType("text/plain");
    }

    @Test
    public void testHelloServletCalledTwice() throws Exception {
        HelloServlet servlet = new HelloServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getMethod()).thenReturn("GET");
        when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));

        servlet.service(request, response);
        servlet.service(request, response);

        verify(response, times(2)).getWriter();
    }

    @Test
    public void testHelloServletIsHttpServlet() {
        HelloServlet servlet = new HelloServlet();
        Assert.assertTrue(servlet instanceof jakarta.servlet.http.HttpServlet);
    }
}
