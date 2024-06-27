package com.example.securedYTSpace.ControllerClasses;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractAuthorizationCodeCallbackServlet extends HttpServlet {
    protected abstract void onSuccess(HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeResponseUrl successResponseUrl) throws IOException;

    protected abstract void onError(HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeResponseUrl errorResponseUrl) throws IOException;

    protected abstract AuthorizationCodeFlow initializeFlow() throws IOException;

    protected abstract String getRedirectUri(HttpServletRequest req) throws IOException;

    protected abstract String getUserId(HttpServletRequest req) throws IOException;
}
