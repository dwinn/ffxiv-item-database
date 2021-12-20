package com.dwinn.ffxivitemdatabase.config;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Config class for setting up the {@link AsyncHttpClient} bean.
 *
 * @author David Winn
 */
@Configuration
public class AsyncClientConfig {

	/**
	 * The amount of time (in milliseconds) to wait to read data from the server
	 * after we have connected. Defaults to 30000.
	 */
	@Value("${http.read.timeout:30000}")
	private int httpReadTimeout;

	/**
	 * The amount of time (in milliseconds) to wait for the initial connection
	 * to the server. Defaults to 30000.
	 */
	@Value("${http.connect.timeout:30000}")
	private int httpConnectTimeout;

	@Bean
	public AsyncHttpClient asyncHttpClient() {
		final DefaultAsyncHttpClientConfig.Builder asyncHttpClientConfig = new DefaultAsyncHttpClientConfig.Builder();
		asyncHttpClientConfig.setReadTimeout(httpReadTimeout);
		asyncHttpClientConfig.setRequestTimeout(httpReadTimeout);
		asyncHttpClientConfig.setConnectTimeout(httpConnectTimeout);


		return new DefaultAsyncHttpClient(asyncHttpClientConfig.build());
	}
}
