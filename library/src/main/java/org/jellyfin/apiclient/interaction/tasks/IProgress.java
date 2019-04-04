package org.jellyfin.apiclient.interaction.tasks;

public interface IProgress<T> {

    void report(T progress);
    void reportComplete();
    void reportCancelled();
    void reportError(Exception exception);
}
