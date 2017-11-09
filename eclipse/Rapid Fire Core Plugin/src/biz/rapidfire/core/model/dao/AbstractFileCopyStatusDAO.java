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
import java.util.Random;

import org.eclipse.swt.widgets.Shell;

import biz.rapidfire.core.model.IFileCopyStatus;
import biz.rapidfire.core.model.list.FileCopyStatus;

public abstract class AbstractFileCopyStatusDAO {

    public static final String JOB = "JOB"; //$NON-NLS-1$
    public static final String POSITION = "POS"; //$NON-NLS-1$
    public static final String AREA = "ARA"; //$NON-NLS-1$
    public static final String FILE = "FILE"; //$NON-NLS-1$
    public static final String LIBRARY = "LIB"; //$NON-NLS-1$
    public static final String RCDS_IN_PRODUCTION_LIBRARY = "RPL"; //$NON-NLS-1$
    public static final String RCDS_IN_SHADOW_LIBRARY = "RSL"; //$NON-NLS-1$
    public static final String RCDS_TO_COPY = "RTO"; //$NON-NLS-1$
    public static final String RCDS_COPIED = "RCO"; //$NON-NLS-1$
    public static final String ESTIMATED_TIME = "ETC"; //$NON-NLS-1$
    public static final String CHANGES_TO_APPLY = "CTA"; //$NON-NLS-1$
    public static final String CHANGES_APPLIED = "CAP"; //$NON-NLS-1$
    public static final String PERCENT_DONE = "PRC"; //$NON-NLS-1$

    private IBaseDAO dao;

    public AbstractFileCopyStatusDAO(IBaseDAO dao) {

        this.dao = dao;
    }

    public List<IFileCopyStatus> load(final String libraryName, String job, Shell shell) throws Exception {

        final List<IFileCopyStatus> fileCopyStatuses = new ArrayList<IFileCopyStatus>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        if (!dao.checkRapidFireLibrary(shell, libraryName)) {
            return fileCopyStatuses;
        }

        try {

            String sqlStatement = getSqlStatement(libraryName, job);
            preparedStatement = dao.prepareStatement(sqlStatement, libraryName);
            resultSet = preparedStatement.executeQuery();
            resultSet.setFetchSize(50);

            if (resultSet != null) {
                while (resultSet.next()) {
                    fileCopyStatuses.add(produceFileCopyStatus(libraryName, resultSet));
                }
            }

        } finally {
            dao.destroy(preparedStatement);
            dao.destroy(resultSet);
        }

        return fileCopyStatuses;
    }

    private FileCopyStatus produceFileCopyStatus(String dataLibrary, ResultSet resultSet) throws SQLException {

        String job = resultSet.getString(JOB).trim();
        int position = resultSet.getInt(POSITION);
        String area = resultSet.getString(AREA).trim();
        String file = resultSet.getString(FILE).trim();
        String library = resultSet.getString(LIBRARY).trim();
        long recordsInProductionLibrary = resultSet.getLong(RCDS_IN_PRODUCTION_LIBRARY);
        long recordsInShadowLibrary = resultSet.getLong(RCDS_IN_SHADOW_LIBRARY);
        long recordsToCopy = resultSet.getLong(RCDS_TO_COPY);
        long recordsCopied = resultSet.getLong(RCDS_COPIED);
        String estimatedTime = resultSet.getString(ESTIMATED_TIME).trim();
        long changesToApply = resultSet.getLong(CHANGES_TO_APPLY);
        long changesApplied = resultSet.getLong(CHANGES_APPLIED);
        int percentDone = resultSet.getInt(PERCENT_DONE);

        FileCopyStatus fileCopyStatus = new FileCopyStatus();
        fileCopyStatus.setJob(job);
        fileCopyStatus.setPosition(position);
        fileCopyStatus.setArea(area);
        fileCopyStatus.setFile(file);
        fileCopyStatus.setLibrary(library);
        fileCopyStatus.setRecordsInProductionLibrary(recordsInProductionLibrary);
        fileCopyStatus.setRecordsInShadowLibrary(recordsInShadowLibrary);
        fileCopyStatus.setRecordsToCopy(recordsToCopy);
        fileCopyStatus.setRecordsCopied(recordsCopied);
        fileCopyStatus.setEstimatedTime(estimatedTime);
        fileCopyStatus.setChangesToApply(changesToApply);
        fileCopyStatus.setChangesApplied(changesApplied);

        // TODO: remove debug code
        percentDone = new Random().nextInt((100) + 1);

        fileCopyStatus.setPercentDone(percentDone);

        return fileCopyStatus;
    }

    private String getSqlStatement(String libraryName, String job) throws Exception {

        String where;
        if (job != null) {
            where = "AREAS.JOB = ''" + job + "''";
        } else {
            where = "";
        }

        // @formatter:off
        String sqlStatement = 
        "SELECT " +
            JOB + ", " +
            POSITION + ", " +
            AREA + ", " +
            FILE + ", " +
            LIBRARY + ", " +
            RCDS_IN_PRODUCTION_LIBRARY + ", " +
            RCDS_IN_SHADOW_LIBRARY + ", " +
            RCDS_TO_COPY + ", " +
            RCDS_COPIED + ", " +
            ESTIMATED_TIME + ", " +
            CHANGES_TO_APPLY + ", " +
            CHANGES_APPLIED + ", " +
            PERCENT_DONE + " " +
        "FROM TABLE(" +
            IBaseDAO.LIBRARY +
            "\"LODSTSE_loadStatusEntries\"('" + where + "')" +
            ") AS X";
        // @formatter:on

        return dao.insertLibraryQualifier(sqlStatement, libraryName);
    }
}
