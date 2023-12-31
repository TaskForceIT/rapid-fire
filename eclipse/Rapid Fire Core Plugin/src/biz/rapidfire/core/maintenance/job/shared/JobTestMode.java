/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Team
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.core.maintenance.job.shared;

import java.util.HashMap;
import java.util.Map;

public enum JobTestMode {
    NO ("*YES"), //$NON-NLS-1$
    YES ("*NO"); //$NON-NLS-1$

    private String label;

    private static Map<String, JobTestMode> executionModes;

    static {
        executionModes = new HashMap<String, JobTestMode>();
        for (JobTestMode status : JobTestMode.values()) {
            executionModes.put(status.label, status);
        }
    }

    private JobTestMode(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    public static String[] labels() {
        return executionModes.keySet().toArray(new String[executionModes.size()]);
    }

    public static JobTestMode find(String label) {
        // TODO: remove debug code
        if (label.length() != label.trim().length()) {
            throw new IllegalArgumentException("Expect to see a trimmed value here."); //$NON-NLS-1$
        }
        return executionModes.get(label);
    }
}
