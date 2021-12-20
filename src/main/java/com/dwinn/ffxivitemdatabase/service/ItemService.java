package com.dwinn.ffxivitemdatabase.service;

import java.util.concurrent.CompletableFuture;

import com.dwinn.ffxivitemdatabase.client.ApiClient;
import com.dwinn.ffxivitemdatabase.dto.ItemResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <description>.
 *
 * @author David Winn
 */
@Service
public class ItemService {

	@Autowired
	private ApiClient apiClient;

	public void createItem(ItemResponse request) {
	}

	public CompletableFuture<ItemResponse> getItem(int id) {
		return apiClient.getItemData(id);
	}

	public Boolean deleteItem(int id) {
		return null;
	}
}
