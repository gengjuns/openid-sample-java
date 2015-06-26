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
public class ConsumerReturnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsumerReturnServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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

			if (isAttribueExchangeResponse(request) && simpleConsumer.verifyResponse(request) != null) {
				processLoginSuccess(request, response);
			}else{
				processLoginError(request, response);
			}
		} catch (Exception e) {
			processLoginError(request, response);
			e.printStackTrace();
		}

	}

	private boolean isAttribueExchangeResponse(HttpServletRequest request) {
		ParameterList paramList = new ParameterList(request.getParameterMap());
		if ("id_res".equals(paramList.getParameterValue("openid.mode"))) {
			return true;
		}
		return false;
	}

	private void processLoginSuccess(HttpServletRequest request,
			HttpServletResponse response) {
		// Login was successful
		try {
		    request.getSession().setAttribute("isLogin", true);
			response.sendRedirect("welcome.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processLoginError(HttpServletRequest request,
			HttpServletResponse response) {
		// Login failed
		try {
		        request.getSession().setAttribute("isLogin", false);
			response.sendRedirect("error.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
