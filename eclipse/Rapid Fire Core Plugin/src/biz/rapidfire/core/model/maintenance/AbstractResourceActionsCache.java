/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Owners
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.core.model.maintenance;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractResourceActionsCache<K extends IKeyResourceActionCache, A extends IResourceAction> {

    private Map<String, Set<A>> cachedKeys;

    /**
     * Private constructor to ensure the Singleton pattern.
     */
    protected AbstractResourceActionsCache() {
        this.cachedKeys = new HashMap<String, Set<A>>();
    }

    public void putActions(K key, A[] actions) {
        Set<A> actionsSet = new HashSet<A>(Arrays.asList(actions));
        cachedKeys.put(key.getValue(), actionsSet);
    }

    public Set<A> getActions(K key) {
        return cachedKeys.get(key.toString());
    }
}