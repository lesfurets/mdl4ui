/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.sample.context;

import java.util.Collection;
import java.util.HashSet;

public class UserAccount {

    private String login;
    private String password;

    private Timezone timezone;
    private Language language;

    private Boolean acceptEmail;
    private Collection<EmailType> emailTypes = new HashSet<EmailType>();
    private int maxEmailPerWeek;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Boolean isAcceptEmail() {
        return acceptEmail;
    }

    public void setAcceptEmail(Boolean acceptEmail) {
        this.acceptEmail = acceptEmail;
    }

    public Collection<EmailType> getEmailTypes() {
        return emailTypes;
    }

    public void setEmailTypes(Collection<EmailType> emailTypes) {
        this.emailTypes = emailTypes;
    }

    public int getMaxEmailPerWeek() {
        return maxEmailPerWeek;
    }

    public void setMaxEmailPerWeek(int maxEmailPerWeek) {
        this.maxEmailPerWeek = maxEmailPerWeek;
    }
}
