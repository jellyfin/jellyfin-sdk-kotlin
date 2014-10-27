package MediaBrowser.ApiInteraction.Tasks;

public interface IProgress<T> {

    void report(T progress);
    void reportComplete();
    void reportError(Exception exception);
}
