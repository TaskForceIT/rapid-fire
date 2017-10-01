/*******************************************************************************
 * Copyright (c) 2012-2015 iSphere Project Owners
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     iSphere Project Owners - Maintenance and enhancements
 *******************************************************************************/

package biz.rapidfire.rse.subsystem;

import java.util.Vector;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.rse.core.filters.ISystemFilter;
import org.eclipse.rse.core.filters.ISystemFilterPool;
import org.eclipse.rse.core.filters.ISystemFilterPoolManager;
import org.eclipse.rse.core.subsystems.ISubSystem;
import org.eclipse.rse.core.subsystems.ISubSystemConfiguration;
import org.eclipse.rse.ui.SystemMenuManager;
import org.eclipse.rse.ui.filters.actions.SystemChangeFilterAction;
import org.eclipse.rse.ui.filters.actions.SystemNewFilterAction;
import org.eclipse.rse.ui.view.SubSystemConfigurationAdapter;
import org.eclipse.swt.widgets.Shell;

import biz.rapidfire.rse.actions.AddRapidFireInstanceAction;

public class RapidFireSubSystemConfigurationAdapter extends SubSystemConfigurationAdapter {

    public RapidFireSubSystemConfigurationAdapter() {
        super();
    }

    @Override
    protected IAction[] getSubSystemNewFilterPoolActions(SystemMenuManager menu, IStructuredSelection selection, Shell shell, String menuGroup,
        ISubSystemConfiguration config, ISubSystem selectedSubSystem) {

        return super.getSubSystemNewFilterPoolActions(menu, selection, shell, menuGroup, config, selectedSubSystem);
    }

    @Override
    public IAction[] getSubSystemActions(SystemMenuManager menu, IStructuredSelection selection, Shell shell, String menuGroup,
        ISubSystemConfiguration config, ISubSystem selectedSubSystem) {

        menu.add("group.new", new AddRapidFireInstanceAction(shell, (RapidFireSubSystem)selectedSubSystem));

        return super.getSubSystemActions(menu, selection, shell, menuGroup, config, selectedSubSystem);
    }

    @Override
    protected void addConnectOrDisconnectAction(Vector actions, Shell shell, ISubSystem selectedSS) {

        super.addConnectOrDisconnectAction(actions, shell, selectedSS);
    }

    @Override
    protected IAction[] getNewFilterPoolFilterActions(SystemMenuManager menu, IStructuredSelection selection, Shell shell, String menuGroup,
        ISubSystemConfiguration config, ISystemFilterPool selectedPool) {

        SystemNewFilterAction filterAction = (SystemNewFilterAction)super.getNewFilterPoolFilterAction(config, selectedPool, shell);
        filterAction.setWizardPageTitle("Messages.Message_Filter");
        filterAction.setPage1Description("Messages.Create_a_new_filter_to_list_messages");
        filterAction.setType("Messages.Message_Filter");
        filterAction.setText("Messages.Message_Filter_Dots");
        filterAction.setFilterStringEditPane(new RapidFireInstanceFilterStringEditPane(shell));

        ISystemFilterPoolManager[] filterPoolManager = config.getSystemFilterPoolManagers();
        ISystemFilterPool[] poolsToSelectFrom = null;

        int i = 0;
        if (i < filterPoolManager.length) {
            poolsToSelectFrom = filterPoolManager[i].getSystemFilterPools();
        }

        if (poolsToSelectFrom != null) {
            filterAction.setAllowFilterPoolSelection(poolsToSelectFrom);
        }

        IAction[] actions = new IAction[1];
        actions[0] = filterAction;

        return actions;
    }

    @Override
    protected IAction getChangeFilterAction(ISubSystemConfiguration factory, ISystemFilter selectedFilter, Shell shell) {

        SystemChangeFilterAction action = (SystemChangeFilterAction)super.getChangeFilterAction(factory, selectedFilter, shell);
        selectedFilter.getType();
        action.setDialogTitle("Messages.Change_Message_Filter");
        action.setFilterStringEditPane(new RapidFireInstanceFilterStringEditPane(shell));

        return action;
    }

    @Override
    public ImageDescriptor getSystemFilterImage(ISystemFilter filter) {
        // return
        // ISphereMessageSubsystemRSEPlugin.getDefault().getImageRegistry().getDescriptor(ISphereMessageSubsystemRSEPlugin.IMAGE_MESSAGE_FILTER);
        return null;
    }

    @Override
    protected Vector<IAction> getAdditionalFilterActions(ISubSystemConfiguration config, ISystemFilter selectedFilter, Shell shell) {

        // Vector<IAction> actions = new Vector<IAction>();
        // actions.add(getChangeFilterAction(config, selectedFilter, shell));
        //
        // return actions;
        return null;
    }

}
