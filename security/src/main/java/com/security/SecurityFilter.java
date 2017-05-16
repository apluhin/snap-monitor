package com.security;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession();
        if (session.getAttribute("user") == null && !checkSession(((HttpServletRequest) request).getRequestURI())) {
            ((HttpServletResponse) response).sendRedirect("/login");
        } else {

            chain.doFilter(request, response);
        }
    }

    private boolean checkSession(String request) {
        return request.contains("/login") ||
                request.contains("/css") ||
                request.contains("/js");
    }

    @Override
    public void destroy() {

    }
}
