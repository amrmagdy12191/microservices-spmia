package com.spmia.zuul.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

@Component
public class SpecialRoutesFilter extends ZuulFilter{
	private static final int  FILTER_ORDER=1;
    private static final boolean  SHOULD_FILTER=true;
    private static final Logger logger = LoggerFactory.getLogger(SpecialRoutesFilter.class);

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return SHOULD_FILTER;
	}

	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return FilterUtils.ROUTE_FILTER_TYPE;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return FILTER_ORDER;
	}
	

//    private AbTestingRoute getAbRoutingInfo(String serviceName){
//       
//    }
//
//    private String buildRouteString(String oldEndpoint, String newEndpoint, String serviceName){
//        
//    }
//
//    private String getVerb(HttpServletRequest request) {
//        
//    }
//
//    private HttpHost getHttpHost(URL host) {
//        
//    }
//
//    private Header[] convertHeaders(MultiValueMap<String, String> headers) {
//        
//    }
//
//    private HttpResponse forwardRequest(HttpClient httpclient, HttpHost httpHost,
//                                        HttpRequest httpRequest) throws IOException {
//        
//    }
//
//
//    private MultiValueMap<String, String> revertHeaders(Header[] headers) {
//        
//    }
//
//    private InputStream getRequestBody(HttpServletRequest request) {
//        
//    }
//
//    private void setResponse(HttpResponse response) throws IOException {
//        
//    }
//
//    private HttpResponse forward(HttpClient httpclient, String verb, String uri,
//                                 HttpServletRequest request, MultiValueMap<String, String> headers,
//                                 MultiValueMap<String, String> params, InputStream requestEntity)
//            throws Exception {
//        
//
//    }
//
//
//
//    public boolean useSpecialRoute(AbTestingRoute testRoute){
//
//        return false;
//    }
//
//
//
//    private void forwardToSpecialRoute(String route) {
//
//    }

}
