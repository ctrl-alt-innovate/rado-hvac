package com.evanwahrmund.server;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class AuthFilter implements Filter {


    private static final Set<String> protectedPaths = Set.of(
            "/invoices.html",
            "/messages-list.html",
            "/review-list.html"
    );
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String path = req.getRequestURI();

        if (protectedPaths.contains(path)) {
            if (session == null || session.getAttribute("username") == null) {
                res.sendRedirect("/login.html");
                return;
            }
        }
        chain.doFilter(request,response);
    }
}
