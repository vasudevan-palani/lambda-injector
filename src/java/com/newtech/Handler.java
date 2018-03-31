package com.newtech;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private static final Logger LOG = Logger.getLogger(Handler.class);

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		BasicConfigurator.configure();

		LOG.info("received: " + input);
		
		String url = (String) input.get("url");
		UrlMapper mapper = new UrlMapper();
		com.newtech.Context nutchContext = new com.newtech.Context();
		mapper.setup(nutchContext);
		try {
			mapper.map(url, nutchContext);
			LOG.info(nutchContext.getOutput());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		Response responseBody = new Response("Go Serverless v1.x! Your function executed successfully!", input);
		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody(responseBody)
				.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
				.build();
	}
}
