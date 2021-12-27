package com.dwinn.ffxivitemdatabase.client;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

import com.dwinn.ffxivitemdatabase.dto.ItemResponse;
import com.dwinn.ffxivitemdatabase.exception.ExternalServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

/**
 * <description>.
 *
 * @author David Winn
 */
@Component
public class ApiClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiClient.class);

	private static final String FFXIV_DB_ENDPOINT = "%s/item/%s";

	private final AsyncHttpClient asyncHttpClient;
	private final ObjectMapper objectMapper;
	private final String apiUrl;

	@Autowired
	public ApiClient(AsyncHttpClient asyncHttpClient, ObjectMapper objectMapper, @Value("${api.url}") String apiUrl) {
		this.asyncHttpClient = asyncHttpClient;
		this.objectMapper = objectMapper;
		this.apiUrl = apiUrl;
	}

	public CompletableFuture<ItemResponse> getItemData(int itemId) {

		// Query string is hard coded for testing for now. Add query parameters next.
		final String url = String.format(FFXIV_DB_ENDPOINT, apiUrl, itemId) + "?columns=ID,Name_en,LevelItem,LevelEquip,ClassJobCategory.Name_en,Icon";
System.out.println(url);
		LOGGER.debug("Making request to [{}] for the item information.", url);

		final BoundRequestBuilder request = asyncHttpClient.prepareGet(url)
				.setCharset(StandardCharsets.UTF_8)
				.setHeader("Accept", MediaType.APPLICATION_JSON_VALUE);

		return request.execute().toCompletableFuture().thenApply(response -> {
			try {
				return objectMapper.readValue(response.getResponseBody(), ItemResponse.class);
			} catch (IOException e) {
				LOGGER.error("Error getting the item information.", e);
				throw new ExternalServiceException(e);
			}
		});
	}
}
