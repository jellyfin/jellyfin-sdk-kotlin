package org.jellyfin.apiclient.logging;

import org.jellyfin.apiclient.model.logging.ILogger;

public class ConsoleLogger implements ILogger {

    @Override
    public void Info(String message, Object... paramList) {
        System.out.println(paramList != null && paramList.length > 0 ? String.format(message, paramList) : message);
    }

    @Override
    public void Error(String message, Object... paramList) {
        System.out.println(paramList != null && paramList.length > 0 ? String.format(message, paramList) : message);
    }

    @Override
    public void Warn(String message, Object... paramList) {
        System.out.println(paramList != null && paramList.length > 0 ? String.format(message, paramList) : message);
    }

    @Override
    public void Debug(String message, Object... paramList) {
        System.out.println(paramList != null && paramList.length > 0 ? String.format(message, paramList) : message);
    }

    @Override
    public void Fatal(String message, Object... paramList) {
        System.out.println(paramList != null && paramList.length > 0 ? String.format(message, paramList) : message);
    }

    @Override
    public void FatalException(String message, Exception exception, Object... paramList) {

        LogException(message, exception, paramList);
    }

    @Override
    public void ErrorException(String message, Exception exception, Object... paramList) {
        LogException(message, exception, paramList);
    }

    private void LogException(String message, Exception exception, Object... paramList) {

        String msg = paramList != null && paramList.length > 0 ? String.format(message, paramList) : message;

        String exceptionMessage = exception.getMessage();

        if (exceptionMessage != null){
            msg += System.lineSeparator() +  exceptionMessage;
        }

        for (StackTraceElement elem : exception.getStackTrace()) {
            msg += System.lineSeparator() +  elem.toString();
        }

        System.out.println(msg);
    }
}
