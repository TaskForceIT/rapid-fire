/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Team
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.rse.model.dao;

import biz.rapidfire.core.model.IRapidFireJobResource;
import biz.rapidfire.core.model.dao.AbstractJobsDAO;
import biz.rapidfire.core.model.dao.DAOManager;
import biz.rapidfire.core.model.dao.IJobsDAO;
import biz.rapidfire.rse.subsystem.resources.RapidFireJobResource;

public class JobsDAO extends AbstractJobsDAO implements IJobsDAO {

    public JobsDAO(String connectionName, String libraryName) throws Exception {
        super(DAOManager.getInstance().getBaseDAO(connectionName, libraryName, false));
    }

    @Override
    protected IRapidFireJobResource createJobInstance(String dataLibrary, String name) {
        return new RapidFireJobResource(dataLibrary, name);
    }
}
