/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Owners
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.core.maintenance.conversion;

import biz.rapidfire.core.maintenance.AbstractKeyResourceActionCache;
import biz.rapidfire.core.model.IRapidFireConversionResource;

/**
 * This class produces the key value for the FileActionCache.
 * 
 * <pre>
 * Form of the key:    [dataLibrary], [jobName], [jobStatus], [position], [fieldToConvert_isEmpty]
 * Example key value:  RFPRI, CUSTUPD, RDY, 10, IS_EMPTY
 * </pre>
 */
public class KeyConversionActionCache extends AbstractKeyResourceActionCache {

    public KeyConversionActionCache(IRapidFireConversionResource conversion) {
        super(conversion.getParentJob(), Integer.toString(conversion.getPosition()), isStringValueEmpty(conversion.getFieldToConvert()));
    }
}
