package com.unibg.UnibgProject.utils;

import jakarta.servlet.http.HttpSession;

public class UtilsGeneric {
    public static Boolean isSessionActive( HttpSession session){
        return session.getAttribute("mail") != null;
    }
}
