/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Team
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.rse.subsystem.resources;

import org.eclipse.rse.core.subsystems.AbstractResource;
import org.eclipse.rse.core.subsystems.ISubSystem;

import biz.rapidfire.core.exceptions.IllegalParameterException;
import biz.rapidfire.core.maintenance.librarylist.shared.LibraryListKey;
import biz.rapidfire.core.model.IRapidFireJobResource;
import biz.rapidfire.core.model.IRapidFireLibraryListResource;
import biz.rapidfire.core.subsystem.IRapidFireSubSystem;
import biz.rapidfire.core.subsystem.resources.RapidFireLibraryListResourceDelegate;

public class RapidFireLibraryListResource extends AbstractResource implements IRapidFireLibraryListResource,
    Comparable<IRapidFireLibraryListResource> {

    private IRapidFireJobResource parentJob;
    private RapidFireLibraryListResourceDelegate delegate;

    public static RapidFireLibraryListResource createEmptyInstance(IRapidFireJobResource job) {
        return new RapidFireLibraryListResource(job, ""); //$NON-NLS-1$
    }

    public RapidFireLibraryListResource(IRapidFireJobResource job, String library) {

        if (job == null) {
            throw new IllegalParameterException("job", null); //$NON-NLS-1$
        }

        if (library == null) {
            throw new IllegalParameterException("library", library); //$NON-NLS-1$
        }

        this.parentJob = job;
        this.delegate = new RapidFireLibraryListResourceDelegate(job.getDataLibrary(), job.getName(), library);
        super.setSubSystem((ISubSystem)job.getParentSubSystem());
    }

    public LibraryListKey getKey() {
        return new LibraryListKey(parentJob.getKey(), delegate.getName());
    }

    /*
     * IRapidFireResource methods
     */

    public String getDataLibrary() {
        return delegate.getDataLibrary();
    }

    public IRapidFireSubSystem getParentSubSystem() {
        return (IRapidFireSubSystem)super.getSubSystem();
    }

    public IRapidFireJobResource getParentJob() {
        return this.parentJob;
    }

    /*
     * IRapidFireFileResource methods
     */

    public String getJob() {
        return delegate.getJob();
    }

    public String getName() {
        return delegate.getName();
    }

    public String getDescription() {
        return delegate.getDescription();
    }

    public void setDescription(String description) {
        delegate.setDescription(description);
    }

    public int compareTo(IRapidFireLibraryListResource resource) {
        return delegate.compareTo(resource);
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
