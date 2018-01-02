/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Team
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.core.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Shell;

import biz.rapidfire.core.model.IRapidFireJobResource;
import biz.rapidfire.core.model.IRapidFireLibraryListResource;

public abstract class AbstractLibraryListsDAO {

    public static final String JOB = "JOB"; //$NON-NLS-1$
    public static final String LIBRARY_LIST = "LIBRARY_LIST"; //$NON-NLS-1$
    public static final String DESCRIPTION = "DESCRIPTION"; //$NON-NLS-1$

    private IJDBCConnection dao;

    public AbstractLibraryListsDAO(IJDBCConnection dao) {

        this.dao = dao;
    }

    public List<IRapidFireLibraryListResource> load(IRapidFireJobResource job, Shell shell) throws Exception {

        final List<IRapidFireLibraryListResource> libraryLists = new ArrayList<IRapidFireLibraryListResource>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        if (!dao.checkRapidFireLibrary(shell)) {
            return libraryLists;
        }

        try {

            String sqlStatement = getSqlStatement();
            preparedStatement = dao.prepareStatement(sqlStatement);
            preparedStatement.setString(1, job.getName());
            resultSet = preparedStatement.executeQuery();
            resultSet.setFetchSize(50);

            if (resultSet != null) {
                while (resultSet.next()) {
                    libraryLists.add(produceLibrary(resultSet, job));
                }
            }
        } finally {
            dao.closeStatement(preparedStatement);
            dao.closeResultSet(resultSet);
        }

        return libraryLists;
    }

    public IRapidFireLibraryListResource load(IRapidFireJobResource job, String libraryListName, Shell shell) throws Exception {

        IRapidFireLibraryListResource libraryList = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        if (!dao.checkRapidFireLibrary(shell)) {
            return libraryList;
        }

        try {

            String sqlStatement = getSqlStatement();
            sqlStatement = sqlStatement + " AND LIBRARY_LIST = ?";
            preparedStatement = dao.prepareStatement(sqlStatement);
            preparedStatement.setString(1, job.getName());
            preparedStatement.setString(2, libraryListName);
            resultSet = preparedStatement.executeQuery();
            resultSet.setFetchSize(50);

            if (resultSet != null) {
                while (resultSet.next()) {
                    libraryList = produceLibrary(resultSet, job);
                }
            }
        } finally {
            dao.closeStatement(preparedStatement);
            dao.closeResultSet(resultSet);
        }

        return libraryList;
    }

    private IRapidFireLibraryListResource produceLibrary(ResultSet resultSet, IRapidFireJobResource job) throws SQLException {

        // String job = resultSet.getString(JOB).trim();
        String library = resultSet.getString(LIBRARY_LIST).trim();

        IRapidFireLibraryListResource libraryListsResource = createLibraryListInstance(job, library);

        String description = resultSet.getString(DESCRIPTION).trim();

        libraryListsResource.setDescription(description);

        return libraryListsResource;
    }

    protected abstract IRapidFireLibraryListResource createLibraryListInstance(IRapidFireJobResource job, String library);

    private String getSqlStatement() throws Exception {

        // @formatter:off
        String sqlStatement = 
        "SELECT " +
            "JOB, " +
            "LIBRARY_LIST, " +
            "DESCRIPTION " +
        "FROM " +
            IJDBCConnection.LIBRARY +
            "LIBLS " +
        "WHERE " +
            "JOB = ?";
        // @formatter:on

        return dao.insertLibraryQualifier(sqlStatement);
    }
}
