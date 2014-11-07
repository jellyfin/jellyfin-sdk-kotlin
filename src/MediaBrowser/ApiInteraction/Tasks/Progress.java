package MediaBrowser.apiinteraction.tasks;

public class Progress<T> implements IProgress<T> {

    public void report(T progress){
        onProgress(progress);
    }

    public void reportComplete() {
        onComplete();
    }

    public void reportCancelled() {
        onCancelled();
    }

    public void reportError(Exception exception) {
        onError(exception);
    }

    public void onProgress(T progress)
    {

    }

    public void onComplete()
    {

    }

    public void onCancelled()
    {

    }

    public void onError(Exception exception)
    {

    }
}
