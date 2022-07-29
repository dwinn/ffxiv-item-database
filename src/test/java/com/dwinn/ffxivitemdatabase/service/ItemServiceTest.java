package com.dwinn.ffxivitemdatabase.service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.dwinn.ffxivitemdatabase.client.ApiClient;
import com.dwinn.ffxivitemdatabase.dto.ClassJob;
import com.dwinn.ffxivitemdatabase.dto.ItemRequest;
import com.dwinn.ffxivitemdatabase.dto.ItemResponse;
import com.dwinn.ffxivitemdatabase.persistence.ItemEntity;
import com.dwinn.ffxivitemdatabase.persistence.ItemRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for the {@link ItemService} class.
 *
 * @author David Winn
 */
@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

	private static final int ITEM_ID = 1675;
	private static final String ITEM_NAME = "Curtana";
	private static final int LEVEL_EQUIP = 50;
	private static final int LEVEL_ITEM = 80;
	private static final String CLASS_JOB_CATEGORY = "PLD";
	private static final String ICON = "/i/030000/030446.png";

	private static ItemRequest itemRequest = new ItemRequest(ITEM_ID);
//	private static ItemResponse itemResponse = new ItemResponse(ITEM_ID);

	@InjectMocks
	private ItemService subject;

	@Mock
	private ApiClient apiClient;

	@Mock
	private ItemRepository itemRepository;

	@Mock
	private ItemEntity itemEntity;

	@Mock
	private ItemResponse itemResponse;

	@Captor
	private ArgumentCaptor<ItemEntity> itemEntityArgumentCaptor;


	@BeforeEach
	public void setUp() {

//		when(itemRepository.findById(ITEM_ID)).thenReturn(Optional.of(itemEntity));

		ItemResponse itemResponse2 = new ItemResponse();
		itemResponse2.setId(ITEM_ID);
		itemResponse2.setClassJobCategory(new ClassJob());

		when(apiClient.getItemData(ITEM_ID)).thenReturn(CompletableFuture.completedFuture(itemResponse2));

	}

	@Test
	public void testCreateItem() {
		when(itemRepository.save(itemEntityArgumentCaptor.capture())).thenReturn(itemEntity);

		assertThatCode(() -> subject.createItem(itemRequest).get()).doesNotThrowAnyException();

//		verify(itemRepository).save(any());

		ItemEntity itemEntityCaptorValue = itemEntityArgumentCaptor.getValue();
		assertThat(itemEntityCaptorValue.getId()).isEqualTo(ITEM_ID);
	}
}
