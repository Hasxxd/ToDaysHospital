// ===== ActionForward.java =====
package com.middleproject.controller;

public class ActionForward {

    public ActionForward(String webinFviewsmemberloginjsp, boolean par) {
    }

    private String path;
    private boolean isRedirect;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setRedirect(boolean isRedirect) {
        this.isRedirect = isRedirect;
    }
}
