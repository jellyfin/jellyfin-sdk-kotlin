package MediaBrowser.ApiInteraction.Tasks;

public class Progress<T> implements IProgress<T> {

    public void report(T progress){
        onProgress(progress);
    }

    public void reportComplete() {

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

    public void onError(Exception exception)
    {

    }
}
