/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Owners
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.rse.subsystem.actions;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.rse.core.subsystems.ISubSystem;
import org.eclipse.rse.ui.actions.SystemBaseAction;
import org.eclipse.swt.widgets.Shell;

import biz.rapidfire.core.RapidFireCorePlugin;
import biz.rapidfire.core.handlers.library.NewLibraryHandler;
import biz.rapidfire.core.model.IRapidFireJobResource;
import biz.rapidfire.rse.Messages;
import biz.rapidfire.rse.RapidFireRSEPlugin;
import biz.rapidfire.rse.subsystem.resources.LibrariesNode;
import biz.rapidfire.rse.subsystem.resources.RapidFireLibraryResource;

public class NewLibraryAction extends SystemBaseAction {

    public NewLibraryAction(Shell shell) {
        super(Messages.ActionLabel_New_Library, Messages.ActionTooltip_New_Library, shell);

        setImageDescriptor(RapidFireRSEPlugin.getDefault().getImageRegistry().getDescriptor(RapidFireRSEPlugin.IMAGE_NEW_LIBRARY));
        setContextMenuGroup("group.new");
    }

    @Override
    public void run() {

        try {

            Object element = getFirstSelection();
            LibrariesNode librariesNode = (LibrariesNode)element;
            IRapidFireJobResource job = librariesNode.getJob();

            RapidFireLibraryResource library = RapidFireLibraryResource.createEmptyInstance(job.getDataLibrary(), job.getName());
            library.setSubSystem((ISubSystem)job.getParentSubSystem());

            NewLibraryHandler handler = new NewLibraryHandler();
            IStructuredSelection selection = new StructuredSelection(library);
            handler.executeWithSelection(selection);

        } catch (Exception e) {
            RapidFireCorePlugin.logError("*** Could not execute 'New Library' handler ***", e); //$NON-NLS-1$
        }

    }
}
