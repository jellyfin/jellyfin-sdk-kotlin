package org.jellyfin.apiclient.logging;

public class ConsoleLogger implements ILogger {

    @Override
    public void debug(String formatString, Object... paramList) {
        System.out.println(paramList != null && paramList.length > 0 ? String.format(formatString, paramList) : formatString);
    }

    @Override
    public void info(String formatString, Object... paramList) {
        System.out.println(paramList != null && paramList.length > 0 ? String.format(formatString, paramList) : formatString);
    }

    @Override
    public void error(String formatString, Object... paramList) {
        System.out.println(paramList != null && paramList.length > 0 ? String.format(formatString, paramList) : formatString);
    }

    @Override
    public void error(String formatString, Exception exception, Object... paramList) {
        LogException(formatString, exception, paramList);
    }

    private void LogException(String formatString, Exception exception, Object... paramList) {

        String msg = paramList != null && paramList.length > 0 ? String.format(formatString, paramList) : formatString;

        String exceptionMessage = exception.getMessage();

        if (exceptionMessage != null) {
            msg += System.lineSeparator() + exceptionMessage;
        }

        for (StackTraceElement elem : exception.getStackTrace()) {
            msg += System.lineSeparator() + elem.toString();
        }

        System.out.println(msg);
    }
}
