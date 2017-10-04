/*******************************************************************************
 * Copyright (c) 2005 SoftLanding Systems, Inc. and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     SoftLanding - initial API and implementation
 *     iSphere Project Owners - Maintenance and enhancements
 *******************************************************************************/
package biz.rapidfire.core.subsystem;

import biz.rapidfire.core.helpers.StringHelper;
import biz.rapidfire.core.model.IRapidFireJobResource;

public class RapidFireFilter {

    private static final String SLASH = "/"; //$NON-NLS-1$
    public static final String ASTERISK = "*"; //$NON-NLS-1$
    public static final String RAPIDFIRE_LIBRARY = "RAPIDFIRE"; //$NON-NLS-1$

    private String library;
    private String job;
    private String status;

    public RapidFireFilter() {
        super();

        setLibrary(RAPIDFIRE_LIBRARY);
        setJob(ASTERISK);
        setStatus(ASTERISK);
    }

    public RapidFireFilter(String filterString) {
        this();
        setFilterString(filterString);
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFilterString() {

        StringBuffer filterString = new StringBuffer();

        appendFilterItem(filterString, library);
        appendFilterItem(filterString, job);
        appendFilterItem(filterString, status);

        return filterString.toString();
    }

    private void appendFilterItem(StringBuffer filterString, String filterItem) {

        if (filterItem == null) {
            filterString.append(ASTERISK);
        } else {
            filterString.append(filterItem);
        }
        filterString.append(SLASH);
    }

    public static RapidFireFilter getDefaultFilter() {
        return new RapidFireFilter();
    }

    public static String getDefaultFilterString() {
        return getDefaultFilter().getFilterString();
    }

    public void setFilterString(String filterString) {

        int start = 0;
        int end = 0;
        String filterItem;

        end = filterString.indexOf(SLASH, start);
        filterItem = retrieveFilterItem(filterString, start, end);
        setLibrary(filterItem);
        start = end + 1;

        end = filterString.indexOf(SLASH, start);
        filterItem = retrieveFilterItem(filterString, start, end);
        setJob(filterItem);
        start = end + 1;

        end = filterString.indexOf(SLASH, start);
        filterItem = retrieveFilterItem(filterString, start, end);
        setStatus(filterItem);
        start = end + 1;
    }

    public boolean matches(IRapidFireJobResource job) {

        if (!getLibrary().equals(job.getLibrary())) {
            return false;
        }

        if (!ASTERISK.equals(getJob()) && !StringHelper.matchesGeneric(job.getName(), getJob())) {
            return false;
        }

        if (!ASTERISK.equals(getStatus()) & !getStatus().equals(job.getStatus().label)) {
            return false;
        }

        return true;
    }

    private String retrieveFilterItem(String filterString, int start, int end) {

        if (end >= filterString.length()) {
            return null;
        }

        String temp;
        if (start < 0 || end < 0 || start >= filterString.length()) {
            temp = ASTERISK;
        } else {
            temp = filterString.substring(start, end);
        }

        return temp;
    }

}