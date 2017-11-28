/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Team
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.core.handlers.file;

import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.dialogs.Dialog;

import biz.rapidfire.core.dialogs.maintenance.file.FileMaintenanceDialog;
import biz.rapidfire.core.model.IRapidFireFileResource;
import biz.rapidfire.core.model.maintenance.IMaintenance;
import biz.rapidfire.core.model.maintenance.file.FileValues;
import biz.rapidfire.core.model.maintenance.file.shared.FileAction;

public class CopyFileHandler extends AbstractFileMaintenanceHandler implements IHandler {

    public CopyFileHandler() {
        super(IMaintenance.MODE_COPY, FileAction.COPY);
    }

    @Override
    protected void performAction(IRapidFireFileResource file) throws Exception {

        FileValues values = getManager().getValues();

        FileMaintenanceDialog dialog = FileMaintenanceDialog.getCopyDialog(getShell(), getManager());
        dialog.setValue(values);

        if (dialog.open() == Dialog.OK) {
            getManager().book();
            refreshUI(file);
        }
    }
}
