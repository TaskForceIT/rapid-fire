/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Owners
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.rse.subsystem.resources;

import biz.rapidfire.core.model.IRapidFireJobResource;
import biz.rapidfire.core.model.IRapidFireNodeResource;
import biz.rapidfire.rse.Messages;

public class LibraryListsNode extends AbstractNodeResource implements IRapidFireNodeResource {

    public LibraryListsNode(IRapidFireJobResource job) {
        super(job, Messages.NodeText_LibraryLists);
    }

    public IRapidFireJobResource getParentResource() {
        return super.getJob();
    }
}
