/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Team
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.core.subsystem.resources;

import biz.rapidfire.core.model.IRapidFireLibraryResource;

public class RapidFireLibraryResourceDelegate implements Comparable<IRapidFireLibraryResource> {

    private String dataLibrary;
    private String job;
    private String library;
    private String shadowLibrary;

    public RapidFireLibraryResourceDelegate(String dataLibrary, String job, String library) {

        this.dataLibrary = dataLibrary;
        this.job = job;
        this.library = library;
    }

    /*
     * IRapidFireResource methods
     */

    public String getDataLibrary() {
        return dataLibrary;
    }

    /*
     * IRapidFireLibraryResource methods
     */

    public String getJob() {
        return job;
    }

    public String getLibrary() {
        return library;
    }

    public String getShadowLibrary() {
        return shadowLibrary;
    }

    public void setShadowLibrary(String shadowLibrary) {
        this.shadowLibrary = shadowLibrary;
    }

    public int compareTo(IRapidFireLibraryResource resource) {

        if (resource == null) {
            return 1;
        }

        int result = resource.getDataLibrary().compareTo(getDataLibrary());
        if (result != 0) {
            return result;
        }

        result = resource.getJob().compareTo(getJob());
        if (result != 0) {
            return result;
        }

        return getLibrary().compareTo(resource.getLibrary());
    }

    @Override
    public String toString() {
        return getLibrary();
    }

}
