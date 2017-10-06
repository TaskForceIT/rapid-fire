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

package biz.rapidfire.rse.subsystem.adapters;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import biz.rapidfire.core.model.IRapidFireJobResource;
import biz.rapidfire.core.properties.JobNameProperties;
import biz.rapidfire.rse.Messages;
import biz.rapidfire.rse.RapidFireRSEPlugin;
import biz.rapidfire.rse.model.RapidFireJobResource;

import com.ibm.etools.systems.core.ui.SystemMenuManager;
import com.ibm.etools.systems.core.ui.view.ISystemRemoteElementAdapter;

public class RapidFireJobResourceAdapter extends AbstractResourceAdapter implements ISystemRemoteElementAdapter {

    private static final String JOB = "JOB"; //$NON-NLS-1$
    private static final String DESCRIPTION = "DESCRIPTION"; //$NON-NLS-1$
    private static final String CREATE_ENVIRONMENT = "CREATE_ENVIRONMENT"; //$NON-NLS-1$
    private static final String JOB_QUEUE_LIBRARY = "JOB_QUEUE_LIBRARY"; //$NON-NLS-1$
    private static final String JOB_QUEUE = "JOB_QUEUE"; //$NON-NLS-1$
    private static final String STATUS = "STATUS"; //$NON-NLS-1$
    private static final String PHASE = "PHASE"; //$NON-NLS-1$
    private static final String ERROR = "ERROR"; //$NON-NLS-1$
    private static final String ERROR_TEXT = "ERROR_TEXT"; //$NON-NLS-1$
    private static final String BATCH_JOB = "BATCH_JOB"; //$NON-NLS-1$

    // private static final String STOP_APPLY_CHANGES = "STOP_APPLY_CHANGES";
    // private static final String CMONE_FORM = "CMONE_FORM";

    public RapidFireJobResourceAdapter() {
        super();
    }

    @Override
    public void addActions(SystemMenuManager menu, IStructuredSelection selection, Shell parent, String menuGroup) {
    }

    @Override
    public ImageDescriptor getImageDescriptor(Object object) {
        return RapidFireRSEPlugin.getDefault().getImageRegistry().getDescriptor(RapidFireRSEPlugin.IMAGE_LIBRARY);
    }

    @Override
    public boolean handleDoubleClick(Object object) {
        return false;
    }

    /**
     * Returns the name of the resource, e.g. for showing the name in the status
     * line.
     */
    public String getText(Object element) {

        RapidFireJobResource resource = (RapidFireJobResource)element;

        return resource.getName() + " - " + resource.getStatus(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    /**
     * Returns the absolute name of the resource for building hash values.
     */
    public String getAbsoluteName(Object element) {

        RapidFireJobResource resource = (RapidFireJobResource)element;
        String library = resource.getParent();

        String name = "RapidFireJob." + library + "." + resource.getName(); //$NON-NLS-1$ //$NON-NLS-2$

        return name;
    }

    public String getAbsoluteParentName(Object element) {
        return ((RapidFireJobResource)element).getParent().toString();
    }

    /**
     * Returns the type of the resource. Used for qualifying the name of the
     * resource, when displayed on the status line.
     */
    @Override
    public String getType(Object element) {
        return Messages.Resource_Rapid_Fire_Job;
    }

    @Override
    public Object getParent(Object element) {
        return ((RapidFireJobResource)element).getParent();
    }

    @Override
    public boolean hasChildren(Object element) {
        return true;
    }

    @Override
    public Object[] getChildren(Object o) {
        return new Object[0];
    }

    @Override
    protected IPropertyDescriptor[] internalGetPropertyDescriptors() {

        PropertyDescriptor[] ourPDs = new PropertyDescriptor[10];
        ourPDs[0] = new PropertyDescriptor(JOB, Messages.Job_name);
        ourPDs[0].setDescription(Messages.Tooltip_Job_name);
        ourPDs[1] = new PropertyDescriptor(DESCRIPTION, Messages.Description);
        ourPDs[1].setDescription(Messages.Tooltip_Description);
        ourPDs[2] = new PropertyDescriptor(BATCH_JOB, Messages.Batch_job);
        ourPDs[2].setDescription(Messages.Tooltip_Batch_job);
        ourPDs[3] = new PropertyDescriptor(CREATE_ENVIRONMENT, Messages.Create_environment);
        ourPDs[3].setDescription(Messages.Tooltip_Create_environment);
        ourPDs[4] = new PropertyDescriptor(JOB_QUEUE, Messages.Job_queue);
        ourPDs[4].setDescription(Messages.Tooltip_Job_queue);
        ourPDs[5] = new PropertyDescriptor(JOB_QUEUE_LIBRARY, Messages.Job_queue_library);
        ourPDs[5].setDescription(Messages.Tooltip_Job_queue_library);
        ourPDs[6] = new PropertyDescriptor(STATUS, Messages.Status);
        ourPDs[6].setDescription(Messages.Tooltip_Status);
        ourPDs[7] = new PropertyDescriptor(PHASE, Messages.Phase);
        ourPDs[7].setDescription(Messages.Tooltip_Phase);
        ourPDs[8] = new PropertyDescriptor(ERROR, Messages.Error);
        ourPDs[8].setDescription(Messages.Tooltip_Error);
        ourPDs[9] = new PropertyDescriptor(ERROR_TEXT, Messages.Error_text);
        ourPDs[9].setDescription(Messages.Tooltip_Error_text);

        return ourPDs;
    }

    @Override
    public Object internalGetPropertyValue(Object propKey) {

        final IRapidFireJobResource resource = (IRapidFireJobResource)propertySourceInput;

        if (propKey.equals(JOB)) {
            return resource.getName();
        } else if (propKey.equals(DESCRIPTION)) {
            return resource.getDescription();
        } else if (propKey.equals(BATCH_JOB)) {
            return new JobNameProperties(resource);
        } else if (propKey.equals(CREATE_ENVIRONMENT)) {
            return resource.isDoCreateEnvironment();
        } else if (propKey.equals(JOB_QUEUE)) {
            return resource.getJobQueue().getObjectName();
        } else if (propKey.equals(JOB_QUEUE_LIBRARY)) {
            return resource.getJobQueue().getLibraryName();
        } else if (propKey.equals(STATUS)) {
            return resource.getStatus().label;
        } else if (propKey.equals(PHASE)) {
            return resource.getPhase().label;
        } else if (propKey.equals(ERROR)) {
            return resource.isError();
        } else if (propKey.equals(ERROR_TEXT)) {
            return resource.getErrorText();
        }

        return null;
    }

    @Override
    public boolean showDelete(Object element) {
        return false;
    }

    @Override
    public boolean canDelete(Object element) {
        return false;
    }

    @Override
    public boolean doDelete(Shell shell, Object element, IProgressMonitor monitor) {

        return false;
    }

    public Object getRemoteParent(Shell arg0, Object arg1) throws Exception {
        return null;
    }

    public String[] getRemoteParentNamesInUse(Shell arg0, Object arg1) throws Exception {
        return null;
    }

    public boolean supportsUserDefinedActions(Object arg0) {
        return false;
    }
}