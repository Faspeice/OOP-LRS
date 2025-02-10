package ru.omgtu.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class MyServlet extends HttpServlet {
    /** Constructor of the object. */
    public MyServlet() {
        super();
    }
    /** Destruction of the servlet. */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
// Put your code here
    }
    /** The doDelete method of the servlet.
     * This method is called when a HTTP delete request is received.
     * @param request the request send by the client to the erver
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred */
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
// Put your code here
    }
    /** The doGet method of the servlet.
     * This method is called when a form has its tag value method equals to get.
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException or @throws IOException if an error occurred */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        this.preventCaching(request, response);
        PrintWriter out = response.getWriter();
        out.println( "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML><HEAD>");
        out.println("<TITLE>A Servlet in GET</TITLE>");
        out.println("</HEAD><BODY>");
        out.print(" This is <B>");
        out.print(this.getClass().getName());
        out.println("</B>, using the <B>GET</B> method<BR>");
        out.println("</BODY></HTML>");
        out.flush();
        out.close();
    }
    /** The doPost method of the servlet.
     * This method is called when a form has its tag value method equals to post.
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException or @throws IOException if an error occurred */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        this.preventCaching(request, response);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML><HEAD>");
        out.println("<TITLE>A Servlet in POST</TITLE>");
        out.println("</HEAD><BODY>");
        out.print("This is <B>");
        out.print(this.getClass().getName());
        out.println("</B>, using the <B>POST</B> method");
        out.println("</BODY></HTML>");
        out.flush();
        out.close();
    }
    /** The doPut method of the servlet.
     * This method is called when a HTTP put request is received.
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException or @throws IOException if an error occurred */
    public void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
// Put your code here
    }
    /** Returns information about the servlet, such as
     * author, version, and copyright.
     * @return String information about this servlet */
    public String getServletInfo() {
        return "This is my default servlet created by Eclipse";
    }
    /* Initilaisation of the servlet.
     * @throws ServletException if an error occure */
    public void init() throws ServletException {
// Put your code here
    }
    /** Prevents navigator from caching data.
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     */
    protected void preventCaching(HttpServletRequest request,HttpServletResponse response) {
        /* see http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
        String protocol = request.getProtocol();
        if ("HTTP/1.0".equalsIgnoreCase(protocol)) {
            response.setHeader("Pragma", "no-cache");
        } else if ("HTTP/1.1".equalsIgnoreCase(protocol)) {
            response.setHeader("Cache-Control", "no-cache");
        }
        response.setDateHeader("Expires", 0);
    }
}
