package com.tracker.goal.configuration.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class WrongAuthenticationHandler implements AuthenticationFailureHandler {

    private static final String REDIRECT_STRING = "/api/home?";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException {
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        redirectStrategy.sendRedirect(request, response, REDIRECT_STRING + request.getQueryString());
    }
}
