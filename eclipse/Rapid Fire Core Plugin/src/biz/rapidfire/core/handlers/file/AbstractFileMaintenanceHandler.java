/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Owners
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.core.handlers.file;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;

import biz.rapidfire.core.Messages;
import biz.rapidfire.core.RapidFireCorePlugin;
import biz.rapidfire.core.handlers.AbstractResourceMaintenanceHandler;
import biz.rapidfire.core.helpers.ExceptionHelper;
import biz.rapidfire.core.maintenance.MaintenanceMode;
import biz.rapidfire.core.maintenance.Result;
import biz.rapidfire.core.maintenance.file.FileManager;
import biz.rapidfire.core.maintenance.file.shared.FileAction;
import biz.rapidfire.core.maintenance.file.shared.FileKey;
import biz.rapidfire.core.maintenance.job.shared.JobKey;
import biz.rapidfire.core.model.IRapidFireFileResource;
import biz.rapidfire.core.model.IRapidFireJobResource;
import biz.rapidfire.core.model.dao.JDBCConnectionManager;

public abstract class AbstractFileMaintenanceHandler extends AbstractResourceMaintenanceHandler<IRapidFireFileResource, FileAction> {

    private FileManager manager;
    private FileAction fileAction;

    public AbstractFileMaintenanceHandler(MaintenanceMode mode, FileAction fileAction) {
        super(mode);

        this.fileAction = fileAction;
    }

    protected FileManager getManager() {
        return manager;
    }

    @Override
    protected Object executeWithResource(IRapidFireFileResource file) throws ExecutionException {

        try {

            if (canExecuteAction(file, fileAction)) {
                Result result = initialize(file);
                if (result != null && result.isError()) {
                    MessageDialog.openError(getShell(), Messages.E_R_R_O_R, result.getMessage());
                } else {
                    performAction(file);
                }
            }

        } catch (Throwable e) {
            logError(e);
        } finally {
            try {
                terminate();
            } catch (Throwable e) {
                logError(e);
            }
        }

        return null;
    }

    protected FileManager getOrCreateManager(IRapidFireJobResource file) throws Exception {

        if (manager == null) {
            String connectionName = file.getParentSubSystem().getConnectionName();
            String dataLibrary = file.getDataLibrary();
            boolean commitControl = isCommitControl();
            manager = new FileManager(JDBCConnectionManager.getInstance().getConnection(connectionName, dataLibrary, commitControl));
        }

        return manager;
    }

    @Override
    protected boolean isValidAction(IRapidFireFileResource file) throws Exception {
        return true;
    }

    @Override
    protected boolean isInstanceOf(Object object) {

        if (object instanceof IRapidFireFileResource) {
            return true;
        }

        return false;
    }

    @Override
    protected boolean canExecuteAction(IRapidFireFileResource file, FileAction fileAction) {

        String message = null;

        try {

            // TODO: check action!
            Result result = getOrCreateManager(file.getParentJob()).checkAction(file.getKey(), fileAction);
            if (result.isSuccessfull()) {
                return true;
            } else {
                // TODO: fix message
                message = Messages
                    .bindParameters(Messages.The_requested_operation_is_invalid_for_job_status_A, file.getParentJob().getStatus().label);
            }

        } catch (Exception e) {
            logError("*** Could not check job action. Failed creating the job manager ***", e); //$NON-NLS-1$
        }

        if (message != null) {
            MessageDialog.openError(getShell(), Messages.E_R_R_O_R, message);
        }

        return false;
    }

    private Result initialize(IRapidFireFileResource file) throws Exception {

        manager.openFiles();

        Result result = manager.initialize(getMaintenanceMode(), new FileKey(new JobKey(file.getJob()), file.getPosition()));

        return result;
    }

    protected abstract void performAction(IRapidFireFileResource file) throws Exception;

    private void terminate() throws Exception {

        if (manager != null) {
            manager.closeFiles();
            manager = null;
        }
    }

    private void logError(Throwable e) {
        RapidFireCorePlugin.logError("*** Could not handle Rapid Fire file resource request ***", e); //$NON-NLS-1$
        MessageDialog.openError(getShell(), Messages.E_R_R_O_R, ExceptionHelper.getLocalizedMessage(e));
    }
}
