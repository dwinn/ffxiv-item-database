package com.dwinn.ffxivitemdatabase.controller;

import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import com.dwinn.ffxivitemdatabase.dto.ItemResponse;
import com.dwinn.ffxivitemdatabase.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	@Autowired
	private ItemService itemService;

	@PostMapping(value = "/item", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void createItem(@Valid @RequestBody ItemResponse item) {
		itemService.createItem(item);
	}

	@GetMapping(value = "/item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CompletableFuture<ItemResponse> getItem(@PathVariable("id") int id) {
		return itemService.getItem(id);
	}

	@DeleteMapping(value = "/item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean deleteItem(@PathVariable("id") int id) {
		return itemService.deleteItem(id);
	}
}
