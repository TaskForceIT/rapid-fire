/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Team
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.core.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ibm.as400.access.AS400;

public interface IBaseDAO {

    public AS400 getSystem() throws Exception;

    public String getConnectionName();

    public PreparedStatement prepareStatement(String sql, String defaultLibrary) throws Exception;

    public void destroy(ResultSet resultSet) throws Exception;

    public void destroy(PreparedStatement preparedStatement) throws Exception;

    public void rollback(Connection connection) throws Exception;

    public void commit(Connection connection) throws Exception;

    public boolean convertYesNo(String yesNoValue);
}
