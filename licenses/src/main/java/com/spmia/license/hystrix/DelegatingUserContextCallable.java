package com.spmia.license.hystrix;

import java.util.concurrent.Callable;

import com.spmia.license.utils.UserContext;
import com.spmia.license.utils.UserContextHolder;

public final class DelegatingUserContextCallable<V> implements Callable<V> {

	private final Callable<V> delegate;
    private UserContext originalUserContext;
    
    public DelegatingUserContextCallable(Callable<V> delegate, UserContext originalUserContext) {
		this.delegate = delegate;
		this.originalUserContext = originalUserContext;
	}
    
	
	public static <V> Callable<V> create(Callable<V> delegate,
            UserContext userContext) {
		return new DelegatingUserContextCallable<V>(delegate, userContext);
	}


	@Override
	public V call() throws Exception {
		UserContextHolder.setContext(originalUserContext);
		try {
			return delegate.call();
		} finally {
			this.originalUserContext = null;
		}
	}

}
