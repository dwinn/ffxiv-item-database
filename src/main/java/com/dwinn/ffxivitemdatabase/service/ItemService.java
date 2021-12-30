package com.dwinn.ffxivitemdatabase.service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.dwinn.ffxivitemdatabase.client.ApiClient;
import com.dwinn.ffxivitemdatabase.dto.ItemDetails;
import com.dwinn.ffxivitemdatabase.dto.ItemRequest;
import com.dwinn.ffxivitemdatabase.dto.ItemResponse;
import com.dwinn.ffxivitemdatabase.persistence.ItemEntity;
import com.dwinn.ffxivitemdatabase.persistence.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <description>.
 *
 * @author David Winn
 */
@Service
public class ItemService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

	private final ApiClient apiClient;
	private final ItemRepository itemRepository;

	@Autowired
	public ItemService(ApiClient apiClient, ItemRepository itemRepository) {
		this.apiClient = apiClient;
		this.itemRepository = itemRepository;
	}

	@Transactional
	public void createItem(ItemRequest request) {

		CompletableFuture<ItemResponse> response = apiClient.getItemData(request.getId());

		response.whenComplete((result, ex) -> {

			if (ex != null) {
				LOGGER.error("Error getting the item information.", ex);
				// Handle exception.
			}

			itemRepository.save(new ItemEntity(
					result.getId(),
					result.getNameEn(),
					result.getLevelEquip(),
					result.getLevelItem(),
					result.getClassJobCategory().getNameEn(),
					result.getIcon()
			));
		});
	}

	public ItemDetails getItem(int id) {
		Optional<ItemEntity> itemEntity = itemRepository.findById(id);

		if (itemEntity.isPresent()) {
			return new ItemDetails(itemEntity.get());
		}

		// Change to a more friendly output.
		return null;
	}

	public void deleteItem(int id) {
		itemRepository.deleteById(id);

		// Maybe return a deletion success message, if exists.
	}

	public CompletableFuture<ItemResponse> getItemFromApi(ItemRequest request) {
		return apiClient.getItemData(request.getId());
	}
}
