package com.orange.bscs.api.connection.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.orange.bscs.api.connection.IConnectionBackend;
import com.orange.bscs.api.model.SVLObjectWrapper;
import com.orange.bscs.api.model.exception.SOIException;

/**
 * LOG output of CMS execution.
 * INFO : log only Command names
 * DEBUG : Log Command and input parameters
 * TRACE : Log Command, inputs and outputs.
 * <p>
 * LoggerName is :"com.orange.bscs.api.connection.CMS"
 *
 * @author IT&Labs
 */
public class ConnectionBackendLog extends ConnectionBackendBase {

    private static final String CMD_ROLLBACK = "ROLLBACK";
    private static final String CMD_REOPEN = "REOPEN";
    private static final String CMD_COMMIT = "COMMIT";
    private static final Logger LOG_SVL = LoggerFactory.getLogger("com.orange.bscs.api.connection.CMS");
    private static final Logger AUDIT_SVL = LoggerFactory.getLogger("com.orange.bscs.api.connection.CMS.Audit");

    /* by instance, Log nb executions*/
    private int nbExecutionsTimeBetweenCommit;

    /* by instance, Log total execution time*/
    private long totalExecutionTimeBetweenCommit;


    /**
     * Instance/by backend constructor
     *
     * @param source source/delegated backend
     */
    public ConnectionBackendLog(IConnectionBackend source) {
        super(source);
    }


    @Override
    public void close() {
        Long start = System.currentTimeMillis();
        boolean ok = false;
        try {
            super.close();
            logExecuteSuccess("CLOSE", null, null, start);
            ok = true;
        } finally {
            logAndResetTotalExecutionTime(start, ok);
            if (!ok) {
                logExecuteFail("CLOSE", null, null, start);
            }
        }
    }


    @Override
    public void reopen() {
        Long start = System.currentTimeMillis();
        boolean ok = false;
        try {
            nbExecutionsTimeBetweenCommit = 0;
            totalExecutionTimeBetweenCommit = 0;
            super.reopen();
            logExecuteSuccess(CMD_REOPEN, null, null, start);
            ok = true;
        } finally {
            if (!ok) {
                logExecuteFail(CMD_REOPEN, null, null, start);
            }
        }
    }


    @Override
    public void commit() {
        Long start = System.currentTimeMillis();
        boolean ok = false;
        try {
            super.commit();
            logExecuteSuccess(CMD_COMMIT, null, null, start);
            ok = true;
        } finally {
            logAndResetTotalExecutionTime(start, ok);
            if (!ok) {
                logExecuteFail(CMD_COMMIT, null, null, start);
            }
        }
    }

    @Override
    public void rollback() {
        Long start = System.currentTimeMillis();
        boolean ok = false;
        try {
            super.rollback();
            logExecuteSuccess(CMD_ROLLBACK, null, null, start);
            ok = true;
        } finally {
            logAndResetTotalExecutionTime(start, ok);
            if (!ok) {
                logExecuteFail(CMD_ROLLBACK, null, null, start);
            }
        }
    }

    @Override
    public SVLObjectWrapper execute(String command, SVLObjectWrapper input) {

        SVLObjectWrapper out = null;
        long now = System.currentTimeMillis();
        try {
            out = super.execute(command, input);

            logExecuteSuccess(command, input, out, now);
        } catch (SOIException e) {
            logExecuteFail(command, input, e, now);
            throw e;
        }
        return out;
    }


    /**
     * Trace command.
     *
     * @param command CMS Command
     * @param input   SVLObject parameters
     * @param out     SVLObject parameters
     * @param start   Time when starting execute command (to calculate Period/Timespan)
     */
    void logExecuteSuccess(String command, SVLObjectWrapper input, SVLObjectWrapper out, long start) {
        if (!ConnectionBackendFactoryLog.getCommandsToSkip().contains(command)) {
            long end = System.currentTimeMillis();
            long dureeMs = end - start;

            totalExecutionTimeBetweenCommit += dureeMs;
            nbExecutionsTimeBetweenCommit++;

            if (null == input || input.isEmpty()) {
                LOG_SVL.info("#{} ms# execute ({})", dureeMs, command);
            } else {
                if (LOG_SVL.isDebugEnabled() && !ConnectionBackendFactoryLog.getCommandsToSkipInput().contains(command)) {
                    LOG_SVL.debug("#{} ms# execute ({}, {\n{}})", new Object[]{dureeMs, command, input.toString()});
                } else {
                    LOG_SVL.info("#{} ms# execute ({}, {...})", dureeMs, command);
                }
            }
            if (null != out && LOG_SVL.isTraceEnabled() && !out.isEmpty() && !ConnectionBackendFactoryLog.getCommandsToSkipOutput().contains(command)) {
                LOG_SVL.trace(" => {\n{}})", out.toString());
            }
        }
    }

    /**
     * Trace command ending in error
     *
     * @param command CMS Command
     * @param input   SVLObject parameters
     * @param e       SOIException (inner exception should be lhs Exception)
     * @param start   Time when starting execute command (to calculate Period/Timespan)
     */
    private void logExecuteFail(String command, SVLObjectWrapper input, SOIException e, long start) {
        long end = System.currentTimeMillis();
        long dureeMs = end - start;

        totalExecutionTimeBetweenCommit += dureeMs;
        nbExecutionsTimeBetweenCommit++;

        if (LOG_SVL.isInfoEnabled() && !ConnectionBackendFactoryLog.getCommandsToSkip().contains(command)) {
            if (null == input || input.isEmpty()) {
                LOG_SVL.info("#{} ms# execute ({})", dureeMs, command);
            } else {
                LOG_SVL.info("#{} ms# execute ({}, {\n{}})", new Object[]{dureeMs, command, input.toString()});
            }
            LOG_SVL.error(" => ERROR {}", (null == e ? null : e.getMessage()));
        }
    }


    private void logAndResetTotalExecutionTime(long start, boolean lastExecutionIsCount) {
        if (!lastExecutionIsCount) {
            long end = System.currentTimeMillis();
            long dureeMs = end - start;
            totalExecutionTimeBetweenCommit += dureeMs;
            nbExecutionsTimeBetweenCommit++;
        }
        AUDIT_SVL.info("CMS: Total commands count/duration : {}/{} ms", nbExecutionsTimeBetweenCommit, totalExecutionTimeBetweenCommit);

        // Cumul stats in Audit Context
        String sTime = MDC.get("BackendTime");
        String sCount = MDC.get("BackendCount");

        long mdcTime = (null == sTime ? 0 : Long.valueOf(sTime)) + totalExecutionTimeBetweenCommit;
        long mdcCount = (null == sCount ? 0 : Long.valueOf(sCount)) + nbExecutionsTimeBetweenCommit;

        MDC.put("BackendTime", Long.toString(mdcTime));
        MDC.put("BackendCount", Long.toString(mdcCount));


        // Reset counter
        totalExecutionTimeBetweenCommit = 0;
        nbExecutionsTimeBetweenCommit = 0;

    }


}
