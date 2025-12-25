package com.example.demo;

import com.example.demo.servlet.HelloServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

public class SkillBasedVolunteerTaskAssignorApplicationTests {

    // ================================
    //          SERVLET TESTS
    // ================================

    @Test(priority = 1)
    public void testServletRespondsWithHelloMessage() throws Exception {
        HelloServlet servlet = new HelloServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getMethod()).thenReturn("GET");

        StringWriter writer = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(writer));

        servlet.service(request, response); // ✅ FIX

        Assert.assertTrue(writer.toString().contains("Hello from HelloServlet"));
    }

    @Test(priority = 2)
    public void testServletContentTypeIsPlainText() throws Exception {
        HelloServlet servlet = new HelloServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getMethod()).thenReturn("GET");
        when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));

        servlet.service(request, response); // ✅ FIX

        verify(response).setContentType("text/plain");
    }

    @Test(priority = 3)
    public void testServletMultipleInvocations() throws Exception {
        HelloServlet servlet = new HelloServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getMethod()).thenReturn("GET");
        when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));

        servlet.service(request, response);
        servlet.service(request, response);

        verify(response, times(2)).getWriter();
    }

    @Test(priority = 4)
    public void testServletHandlesNullRequestGracefully() throws Exception {
        HelloServlet servlet = new HelloServlet();

        HttpServletResponse response = mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));

        servlet.service(null, response); // allowed

        verify(response).getWriter();
    }

    @Test(priority = 5)
    public void testServletThrowsWhenWriterFails() throws Exception {
        HelloServlet servlet = new HelloServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getMethod()).thenReturn("GET");
        when(response.getWriter()).thenThrow(new RuntimeException("Writer error"));

        try {
            servlet.service(request, response);
            Assert.fail("Expected exception");
        } catch (RuntimeException ex) {
            Assert.assertEquals(ex.getMessage(), "Writer error");
        }
    }

    @Test(priority = 6)
    public void testServletOutputLengthGreaterThanZero() throws Exception {
        HelloServlet servlet = new HelloServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getMethod()).thenReturn("GET");

        StringWriter writer = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(writer));

        servlet.service(request, response);

        Assert.assertTrue(writer.toString().length() > 0);
    }

    @Test(priority = 7)
    public void testServletOutputIsDeterministic() throws Exception {
        HelloServlet servlet = new HelloServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getMethod()).thenReturn("GET");

        StringWriter w1 = new StringWriter();
        StringWriter w2 = new StringWriter();

        when(response.getWriter())
                .thenReturn(new PrintWriter(w1))
                .thenReturn(new PrintWriter(w2));

        servlet.service(request, response);
        servlet.service(request, response);

        Assert.assertEquals(w1.toString(), w2.toString());
    }

    @Test(priority = 8)
    public void testServletIsHttpServlet() {
        HelloServlet servlet = new HelloServlet();
        Assert.assertTrue(servlet instanceof jakarta.servlet.http.HttpServlet);
    }
}
