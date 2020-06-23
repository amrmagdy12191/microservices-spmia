package com.spmia.zuul.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class TrackingFilter extends ZuulFilter {
	
	private static final int FILTER_ORDER = 1;
	private static final boolean SHOULD_FILTER= true;
	private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);
	
	@Autowired
	FilterUtils filterUtils;

	@Override
	public boolean shouldFilter() {
		return SHOULD_FILTER;
	}

	@Override
	public Object run() throws ZuulException {
		if(isCorrelationIdPresent()) {
			logger.info("tmx-correlation-id found in tracking filter: {}", filterUtils.getCorrelationId());
		}else {
			filterUtils.setCorrelationId(generateCorrelationId());
			logger.info("tmx-correlation-id found in tracking filter: {}", filterUtils.getCorrelationId());
		}
		
		RequestContext context= RequestContext.getCurrentContext();
		
		logger.info("Processing incoming request for {}.", context.getRequest().getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		return filterUtils.PRE_FILTER_TYPE;
	}

	@Override
	public int filterOrder() {
		return FILTER_ORDER;
	}
	
	private boolean isCorrelationIdPresent() {
		if(filterUtils.getCorrelationId() != null) {
			return true;
		}
		return false;
	}
	
	private String generateCorrelationId() {
		return java.util.UUID.randomUUID().toString();
	}

}
