package com;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Author: Samuel Scott
 * Contributors: Jacob Williams
 *               Michael Gregory
 *               Esther Sully
 *               Daniel Viner
 */
public class loginFilter implements Filter {
    
    FilterConfig filterConfig = null;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        
        if(request.getSession().getAttribute("user") != null){
            chain.doFilter(req, res);
        }else{
            request.getRequestDispatcher("/login.jsp").forward(req, res);
        }
    }

    @Override
    public void destroy() {
    }
    

}