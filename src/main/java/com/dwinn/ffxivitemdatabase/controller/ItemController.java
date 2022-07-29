package com.dwinn.ffxivitemdatabase.controller;

import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import com.dwinn.ffxivitemdatabase.dto.ItemDetails;
import com.dwinn.ffxivitemdatabase.dto.ItemRequest;
import com.dwinn.ffxivitemdatabase.dto.ItemResponse;
import com.dwinn.ffxivitemdatabase.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ItemController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

	@Autowired
	private ItemService itemService;

	@PostMapping(value = "/item", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CompletableFuture<Void> createItem(@Valid @RequestBody ItemRequest item) {
		return itemService.createItem(item);
	}

	@GetMapping(value = "/item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ItemDetails getItem(@PathVariable("id") int id) {
		return itemService.getItem(id);
	}

	@DeleteMapping(value = "/item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteItem(@PathVariable("id") int id) {
		itemService.deleteItem(id);
	}

	@PostMapping(value = "/item/direct",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public CompletableFuture<ItemResponse> getItemFromApi(@Valid @RequestBody ItemRequest item) {
		return itemService.getItemFromApi(item);
	}

	// Helpful for debugging bad requests in end points.
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handle(Exception e) {
		LOGGER.warn("Returning HTTP 400 Bad Request", e);
	}
}
