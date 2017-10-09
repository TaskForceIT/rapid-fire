/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Owners
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.core.subsystem;

import biz.rapidfire.core.helpers.StringHelper;
import biz.rapidfire.core.model.IRapidFireJobResource;

public class RapidFireFilter {

    private static final String SLASH = "/"; //$NON-NLS-1$
    public static final String ASTERISK = "*"; //$NON-NLS-1$
    public static final String RAPIDFIRE_LIBRARY = "RAPIDFIRE"; //$NON-NLS-1$

    private String dataLibrary;
    private String job;
    private String status;

    public RapidFireFilter() {
        super();

        setDataLibrary(RAPIDFIRE_LIBRARY);
        setJob(ASTERISK);
        setStatus(ASTERISK);
    }

    public RapidFireFilter(String filterString) {
        this();

        setFilterString(filterString);
    }

    public String getDataLibrary() {
        return dataLibrary;
    }

    public void setDataLibrary(String dataLibrary) {
        this.dataLibrary = dataLibrary;
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

        appendFilterItem(filterString, dataLibrary);
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
        setDataLibrary(filterItem);
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

        if (!getDataLibrary().equals(job.getDataLibrary())) {
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
