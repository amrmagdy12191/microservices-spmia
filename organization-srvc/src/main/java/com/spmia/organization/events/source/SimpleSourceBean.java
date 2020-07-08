package com.spmia.organization.events.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.spmia.organization.events.model.OrganizationChangeModel;
import com.spmia.organization.utils.UserContextHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * Message publicher to (Kafka [Message Broker])
 * @author amrmagdy
 *
 */
//@Component
@Service
@Slf4j
public class SimpleSourceBean {
	private final GreetingsStreams greetingsStreams;
	
	private Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);
	
	@Autowired
	public SimpleSourceBean(GreetingsStreams greetingsStreams) {
		this.greetingsStreams = greetingsStreams;
	}
	
	public void publishOrgChange(String action, String orgId) {
		logger.info("Send Kafka Message {} for organization id : {}", action, orgId );
		
		MessageChannel messageChannel = greetingsStreams.outboundGreetings();
		
		
		
		final OrganizationChangeModel changeModel = new OrganizationChangeModel(
				OrganizationChangeModel.class.getTypeName(),action,
				orgId, UserContextHolder.createUserContext().getCorrelationId());
		

		messageChannel.send(MessageBuilder
                .withPayload(changeModel)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .setHeader("Authorization", "Bearer " + UserContextHolder.getContext().getAuthToken())
                .build());
		
		logger.info("Authorization : " + UserContextHolder.getContext().getAuthToken());
		logger.info("End Kafka Message {} for organization id : {}", action, orgId );
		
		// when u need to publish to single channel using output
//		greetingsStreams.output().send(MessageBuilder.withPayload(changeModel).build());
	}

}
