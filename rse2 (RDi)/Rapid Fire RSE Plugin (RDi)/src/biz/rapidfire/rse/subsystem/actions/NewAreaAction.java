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
import biz.rapidfire.core.handlers.area.NewAreaHandler;
import biz.rapidfire.core.model.IRapidFireFileResource;
import biz.rapidfire.rse.Messages;
import biz.rapidfire.rse.RapidFireRSEPlugin;
import biz.rapidfire.rse.subsystem.resources.AreasNode;
import biz.rapidfire.rse.subsystem.resources.RapidFireAreaResource;

public class NewAreaAction extends SystemBaseAction {

    public NewAreaAction(Shell shell) {
        super(Messages.ActionLabel_New_Area, Messages.ActionTooltip_New_Area, shell);

        setImageDescriptor(RapidFireRSEPlugin.getDefault().getImageRegistry().getDescriptor(RapidFireRSEPlugin.IMAGE_NEW_AREA));
        setContextMenuGroup("group.new");
    }

    @Override
    public void run() {

        try {

            Object element = getFirstSelection();
            AreasNode areasNode = (AreasNode)element;
            IRapidFireFileResource file = areasNode.getFile();

            RapidFireAreaResource area = RapidFireAreaResource.createEmptyInstance(file);
            area.setSubSystem((ISubSystem)file.getParentSubSystem());

            NewAreaHandler handler = new NewAreaHandler();
            IStructuredSelection selection = new StructuredSelection(area);
            handler.executeWithSelection(selection);

        } catch (Exception e) {
            RapidFireCorePlugin.logError("*** Could not execute 'New Area' handler ***", e); //$NON-NLS-1$
        }

    }
}
