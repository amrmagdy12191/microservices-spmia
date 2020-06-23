package com.spmia.zuul.filters;

import org.springframework.stereotype.Component;

import com.netflix.zuul.context.RequestContext;

@Component
public class FilterUtils {
	public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN     = "tmx-auth-token";
    public static final String USER_ID        = "tmx-user-id";
    public static final String ORG_ID         = "tmx-org-id";
    public static final String PRE_FILTER_TYPE= "pre";
    public static final String POST_FILTER_TYPE = "post";
    public static final String ROUTE_FILTER_TYPE = "route";
    public static final String SERVICE_ID = "serviceId";
    
    public String getCorrelationId() {
    	RequestContext context = RequestContext.getCurrentContext();
    	if(context.getRequest().getHeader(CORRELATION_ID) != null) {
    		return context.getRequest().getHeader(CORRELATION_ID);
    	}
    		
    	return context.getZuulRequestHeaders().get(CORRELATION_ID);
    }
    
    public void setCorrelationId(String correlationId) {
    	RequestContext context = RequestContext.getCurrentContext();
    	context.addZuulRequestHeader(CORRELATION_ID, correlationId);
    }
    
    public String getServiceId() {
    	RequestContext context = RequestContext.getCurrentContext();
    	if(context.get(SERVICE_ID) == null) {
    		return "";
    	}
    	return context.get(SERVICE_ID).toString();
    }

}
