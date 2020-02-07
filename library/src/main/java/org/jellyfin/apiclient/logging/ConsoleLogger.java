package org.jellyfin.apiclient.logging;

import org.jellyfin.apiclient.model.logging.ILogger;

public class ConsoleLogger implements ILogger {

    @Override
    public void Info(String formatString, Object... paramList) {
        System.out.println(paramList != null && paramList.length > 0 ? String.format(formatString, paramList) : formatString);
    }

    @Override
    public void Error(String formatString, Object... paramList) {
        System.out.println(paramList != null && paramList.length > 0 ? String.format(formatString, paramList) : formatString);
    }

    @Override
    public void Warn(String formatString, Object... paramList) {
        System.out.println(paramList != null && paramList.length > 0 ? String.format(formatString, paramList) : formatString);
    }

    @Override
    public void Debug(String formatString, Object... paramList) {
        System.out.println(paramList != null && paramList.length > 0 ? String.format(formatString, paramList) : formatString);
    }

    @Override
    public void Fatal(String formatString, Object... paramList) {
        System.out.println(paramList != null && paramList.length > 0 ? String.format(formatString, paramList) : formatString);
    }

    @Override
    public void FatalException(String formatString, Exception exception, Object... paramList) {

        LogException(formatString, exception, paramList);
    }

    @Override
    public void ErrorException(String formatString, Exception exception, Object... paramList) {
        LogException(formatString, exception, paramList);
    }

    private void LogException(String message, Exception exception, Object... paramList) {

        String msg = paramList != null && paramList.length > 0 ? String.format(message, paramList) : message;

        String exceptionMessage = exception.getMessage();

        if (exceptionMessage != null) {
            msg += System.lineSeparator() +  exceptionMessage;
        }

        for (StackTraceElement elem : exception.getStackTrace()) {
            msg += System.lineSeparator() +  elem.toString();
        }

        System.out.println(msg);
    }
}
