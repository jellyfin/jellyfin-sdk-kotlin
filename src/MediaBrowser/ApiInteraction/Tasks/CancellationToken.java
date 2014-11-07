package MediaBrowser.apiinteraction.tasks;

/**
 * <p>A cancellation token to signal and propagate cancellation
 * requests when executing asynchronous operations.</p>
 *
 * <p>All the methods of this class are thread safe and may be used concurrently</p>
 *
 * <p>The interface of this class is inspired (and copied for some of its parts)
 * from the Microsoft .NET 4 platform.</p>
 *
 * @author Mauro Luigi Drago
 * https://code.google.com/p/bytenose-extra-utils/source/browse/src/main/java/com/bytenose/extrautils/concurrent/CancellationToken.java
 *
 */
public final class CancellationToken {
    /**
     * The states regulating the behavior of this class.
     * A newly created token starts in the NOT_FIXED state.
     * Whenever the isCancellationRequested attribute is set
     * (no matter the value), the state changes to FIXED.
     * In the FIXED state, no more changes to the cancellation
     * token are possible.
     */
    private enum TokenState { FIXED, NOT_FIXED };

    /** The current state of the token */
    private TokenState tokenState;

    /** A flag indicating whether cancellation has been requested */
    private boolean isCancellationRequested;

    /**
     * Creates a new {@link CancellationToken} with the
     * canceled state set to false and which
     * may be subsequently canceled.
     */
    CancellationToken() {
        tokenState = TokenState.NOT_FIXED;
        isCancellationRequested = false;
    }

    /**
     * <p>Creates a new {@link CancellationToken} with a fixed
     * and immutable cancellation state.</p>
     *
     * <p>This constructor is useful when asynchronous operations
     * cannot be canceled.</p>
     * @param canceled
     *              the canceled state for this token.
     */
    public CancellationToken(boolean canceled) {
        tokenState = TokenState.FIXED;
        isCancellationRequested = canceled;
    }

    /**
     * Checks whether cancellation has been requested for this token.
     * @return true if cancellation has been requested, false otherwise.
     */
    public synchronized boolean isCancellationRequested() {
        return isCancellationRequested;
    }

    /**
     * Changes the cancellation state of this token to canceled.
     * This method has no effect if the token is already in the canceled state.
     *
     * @throws IllegalStateException
     *              if the token is already in the FIXED state.
     */
    synchronized void cancel() throws IllegalStateException {
        if (isCancellationRequested) return;
        if (tokenState == TokenState.FIXED)
            throw new IllegalStateException("Cannot change the state of a fixed cancellation token");

        isCancellationRequested = true;
        tokenState = TokenState.FIXED;
    }
}
