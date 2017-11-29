package com;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Author: Samuel Scott
 * Contributors: Michael Gregory,
 *               Jacob Williams,
 *               Esther Sully,
 *               Daniel Viner.
 */
public class authFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        
        if(req.getSession().getAttribute("user") != null){
            chain.doFilter(request, response);
        } else {
            req.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }
}
