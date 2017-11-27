/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Team
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.core.handlers.conversion;

import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.dialogs.Dialog;

import biz.rapidfire.core.dialogs.maintenance.conversion.ConversionMaintenanceDialog;
import biz.rapidfire.core.model.IRapidFireConversionResource;
import biz.rapidfire.core.model.maintenance.IMaintenance;
import biz.rapidfire.core.model.maintenance.conversion.ConversionValues;

public class ChangeConversionHandler extends AbstractConversionMaintenanceHandler implements IHandler {

    public ChangeConversionHandler() {
        super(IMaintenance.MODE_CHANGE);
    }

    @Override
    protected void performAction(IRapidFireConversionResource conversion) throws Exception {

        ConversionValues values = getManager().getValues();

        ConversionMaintenanceDialog dialog = ConversionMaintenanceDialog.getChangeDialog(getShell(), getManager());
        dialog.setValue(values);

        if (dialog.open() == Dialog.OK) {
            getManager().book();
            refreshUI(conversion);
        }
    }
}
