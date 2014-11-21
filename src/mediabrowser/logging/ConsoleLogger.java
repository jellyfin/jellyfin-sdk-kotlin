package mediabrowser.logging;

import mediabrowser.model.logging.ILogger;

public class ConsoleLogger implements ILogger {

    @Override
    public void Info(String message, Object... paramList) {
        System.out.println(String.format(message, paramList));
    }

    @Override
    public void Error(String message, Object... paramList) {
        System.out.println(String.format(message, paramList));
    }

    @Override
    public void Warn(String message, Object... paramList) {
        System.out.println(String.format(message, paramList));
    }

    @Override
    public void Debug(String message, Object... paramList) {
        System.out.println(String.format(message, paramList));
    }

    @Override
    public void Fatal(String message, Object... paramList) {
        System.out.println(String.format(message, paramList));
    }

    @Override
    public void FatalException(String message, Exception exception, Object... paramList) {
        System.out.println(String.format(message, paramList));
    }

    @Override
    public void ErrorException(String message, Exception exception, Object... paramList) {
        System.out.println(String.format(message, paramList));
    }
}
