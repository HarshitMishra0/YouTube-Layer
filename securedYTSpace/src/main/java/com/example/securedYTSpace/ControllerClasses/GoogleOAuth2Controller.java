package com.example.securedYTSpace.ControllerClasses;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;

import com.google.api.client.http.GenericUrl;
import com.google.api.services.youtube.YouTube;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class GoogleOAuth2Controller extends AbstractAuthorizationCodeCallbackServlet {

    @Autowired
    private AuthorizationCodeFlow authorizationCodeFlow;

    @Autowired
    private YouTube youtube;

    @Override
    protected void onSuccess(HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeResponseUrl successResponseUrl) throws IOException {
        resp.sendRedirect("/");
    }

    @Override
    protected void onError(HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeResponseUrl errorResponseUrl) throws IOException {
        resp.sendRedirect("/");
    }

    @Override
    protected AuthorizationCodeFlow initializeFlow() throws IOException {
        return authorizationCodeFlow;
    }

    @Override
    protected String getRedirectUri(HttpServletRequest req) throws IOException {
        GenericUrl url = new GenericUrl(req.getRequestURL().toString());
        url.setRawPath("/oauth2callback");
        return url.build();
    }

    @Override
    protected String getUserId(HttpServletRequest req) throws IOException {
        return "user";
    }
}
