/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Owners
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.rse.subsystem.actions.command;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;

import biz.rapidfire.core.RapidFireCorePlugin;
import biz.rapidfire.core.handlers.command.NewCommandHandler;
import biz.rapidfire.core.model.IRapidFireFileResource;
import biz.rapidfire.rse.Messages;
import biz.rapidfire.rse.RapidFireRSEPlugin;
import biz.rapidfire.rse.subsystem.resources.CommandsNode;
import biz.rapidfire.rse.subsystem.resources.RapidFireCommandResource;

import com.ibm.etools.systems.core.ui.actions.SystemBaseAction;

public class NewCommandsNodePopupMenuAction extends SystemBaseAction {

    public NewCommandsNodePopupMenuAction(Shell shell) {
        super(Messages.ActionLabel_New_Command, Messages.ActionTooltip_New_Command, shell);

        setImageDescriptor(RapidFireRSEPlugin.getDefault().getImageRegistry().getDescriptor(RapidFireRSEPlugin.IMAGE_NEW_COMMAND));
        setContextMenuGroup("group.new");
    }

    @Override
    public void run() {

        try {

            Object element = getFirstSelection();

            if (element instanceof CommandsNode) {
                CommandsNode commandsNode = (CommandsNode)element;
                IRapidFireFileResource file = commandsNode.getFile();

                RapidFireCommandResource command = RapidFireCommandResource.createEmptyInstance(file);

                NewCommandHandler handler = new NewCommandHandler();
                IStructuredSelection selection = new StructuredSelection(command);
                handler.executeWithSelection(selection);
            }

        } catch (Exception e) {
            RapidFireCorePlugin.logError("*** Could not execute 'New Command' handler ***", e); //$NON-NLS-1$
        }

    }
}
