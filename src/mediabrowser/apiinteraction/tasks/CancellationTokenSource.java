package mediabrowser.apiinteraction.tasks;

/**
 * <p>A source of cancellation tokens to signal and propagate cancellation
 * requests when executing asynchronous operations.</p>
 *
 * <p>All the methods of this class are thread safe and may be used concurrently</p>
 *
 * <p>The interface of this class is inspired (and copied for some of its parts)
 * from the Microsoft .NET 4 platform.</p>
 *
 * @author Mauro Luigi Drago
 * https://code.google.com/p/bytenose-extra-utils/source/browse/src/main/java/com/bytenose/extrautils/concurrent/CancellationTokenSource.java
 *
 */
public final class CancellationTokenSource {

    /** The cancellation token associated to this source */
    private final CancellationToken token = new CancellationToken();

    /**
     * Creates a new {@link CancellationTokenSource}.
     */
    public CancellationTokenSource() {
    }

    /**
     * Communicates a request of cancellation to the tokens associated
     * to this source.
     * This method has no effect if cancellation has been already requested.
     */
    public void cancel() {
        token.cancel();
    }

    /**
     * Gets the cancellation token associated to this source.
     * @return the cancellation token.
     */
    public CancellationToken getToken() {
        return token;
    }
}
