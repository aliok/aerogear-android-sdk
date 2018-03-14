package org.aerogear.mobile.core.reactive;

import java.util.concurrent.ExecutorService;

import org.aerogear.mobile.core.Request;
import org.aerogear.mobile.core.Responder;

/**
 * This request will run a Request on a thread provided by RunOn.
 *
 * @param <T> type of the value to be emitted
 */
public final class RunOnRequest<T> extends AbstractRequest<T> {

    private final Request<T> delegateTo;
    private final ExecutorService executorService;

    public RunOnRequest(Request<T> delegateTo, ExecutorService executorService) {
        this.delegateTo = delegateTo;
        this.executorService = executorService;
    }

    @Override
    public Request<T> respondWith(final Responder<T> responder) {
        executorService.submit(() -> delegateTo.respondWith(responder));
        return this;
    }

    @Override
    public void cancel() {
        delegateTo.cancel();
    }
}
