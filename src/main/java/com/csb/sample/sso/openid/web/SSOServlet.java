package com.csb.sample.sso.openid.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.openid4java.consumer.ConsumerException;
import org.openid4java.message.ParameterList;

import com.csb.sample.sso.openid.service.SimpleConsumer;

/**
 * Servlet implementation class LoginServlet
 */
public class SSOServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SSOServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Object osc = session.getAttribute("SimpleConsumer");
        SimpleConsumer simpleConsumer = null;
        try {
            // Initialize SimpleConsumer
            if (osc != null) {
                simpleConsumer = (SimpleConsumer) osc;
            } else {
                simpleConsumer = new SimpleConsumer();
                session.setAttribute("SimpleConsumer", simpleConsumer);
            }

            simpleConsumer.authRequest("http://localhost:8080/saasisv/op/idp.do", request, response);

        } catch (ConsumerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
