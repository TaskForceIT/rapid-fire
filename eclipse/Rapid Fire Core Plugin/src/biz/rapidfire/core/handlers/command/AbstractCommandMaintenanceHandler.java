/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Owners
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.core.handlers.command;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;

import biz.rapidfire.core.Messages;
import biz.rapidfire.core.RapidFireCorePlugin;
import biz.rapidfire.core.handlers.AbstractResourceMaintenanceHandler;
import biz.rapidfire.core.helpers.ExceptionHelper;
import biz.rapidfire.core.maintenance.MaintenanceMode;
import biz.rapidfire.core.maintenance.Result;
import biz.rapidfire.core.maintenance.command.CommandManager;
import biz.rapidfire.core.maintenance.command.shared.CommandAction;
import biz.rapidfire.core.maintenance.command.shared.CommandKey;
import biz.rapidfire.core.maintenance.file.shared.FileKey;
import biz.rapidfire.core.maintenance.job.shared.JobKey;
import biz.rapidfire.core.model.IRapidFireCommandResource;
import biz.rapidfire.core.model.IRapidFireJobResource;
import biz.rapidfire.core.model.dao.JDBCConnectionManager;

public abstract class AbstractCommandMaintenanceHandler extends AbstractResourceMaintenanceHandler<IRapidFireCommandResource, CommandAction> {

    private CommandManager manager;
    private CommandAction commandAction;

    public AbstractCommandMaintenanceHandler(MaintenanceMode mode, CommandAction commandAction) {
        super(mode);

        this.commandAction = commandAction;
    }

    protected CommandManager getManager() {
        return manager;
    }

    @Override
    protected Object executeWithResource(IRapidFireCommandResource command) throws ExecutionException {

        try {

            if (canExecuteAction(command, commandAction)) {
                Result result = initialize(command);
                if (result != null && result.isError()) {
                    MessageDialog.openError(getShell(), Messages.E_R_R_O_R, result.getMessage());
                } else {
                    performAction(command);
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

    protected CommandManager getOrCreateManager(IRapidFireJobResource job) throws Exception {

        if (manager == null) {
            String connectionName = job.getParentSubSystem().getConnectionName();
            String dataLibrary = job.getDataLibrary();
            boolean commitControl = isCommitControl();
            manager = new CommandManager(JDBCConnectionManager.getInstance().getConnection(connectionName, dataLibrary, commitControl));
        }

        return manager;
    }

    @Override
    protected boolean isValidAction(IRapidFireCommandResource command) throws Exception {
        return getOrCreateManager(command.getParentJob()).isValidAction(command, commandAction);
    }

    @Override
    protected boolean isInstanceOf(Object object) {

        if (object instanceof IRapidFireCommandResource) {
            return true;
        }

        return false;
    }

    @Override
    protected boolean canExecuteAction(IRapidFireCommandResource command, CommandAction commanddAction) {

        String message = null;

        try {

            // TODO: check action!
            Result result = getOrCreateManager(command.getParentJob()).checkAction(command.getKey(), commandAction);
            if (result.isSuccessfull()) {
                return true;
            } else {
                // TODO: fix message
                message = Messages.bindParameters(Messages.The_requested_operation_is_invalid_for_job_status_A,
                    command.getParentJob().getStatus().label);
            }

        } catch (Exception e) {
            logError("*** Could not check job action. Failed creating the job manager ***", e); //$NON-NLS-1$
        }

        if (message != null) {
            MessageDialog.openError(getShell(), Messages.E_R_R_O_R, message);
        }

        return false;
    }

    private Result initialize(IRapidFireCommandResource command) throws Exception {

        manager.openFiles();

        JobKey jobKey = new JobKey(command.getJob());
        Result result = manager.initialize(getMaintenanceMode(), new CommandKey(new FileKey(jobKey, command.getPosition()), command.getCommandType(),
            command.getSequence()));

        return result;
    }

    protected abstract void performAction(IRapidFireCommandResource command) throws Exception;

    private void terminate() throws Exception {

        if (manager != null) {
            manager.closeFiles();
            manager = null;
        }
    }

    private void logError(Throwable e) {
        RapidFireCorePlugin.logError("*** Could not handle Rapid Fire command resource request ***", e); //$NON-NLS-1$
        MessageDialog.openError(getShell(), Messages.E_R_R_O_R, ExceptionHelper.getLocalizedMessage(e));
    }
}
