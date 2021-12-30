package com.dwinn.ffxivitemdatabase.controller;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

import com.dwinn.ffxivitemdatabase.dto.ItemDetails;
import com.dwinn.ffxivitemdatabase.dto.ItemRequest;
import com.dwinn.ffxivitemdatabase.dto.ItemResponse;
import com.dwinn.ffxivitemdatabase.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for ItemController.
 */
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {
        "com.dwinn.ffxivitemdatabase.controller"
})
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    private static final String ENDPOINT_CREATE_ITEM = "/api/item";
    private static final String ENDPOINT_GET_ITEM = "/api/item/%s";
    private static final String ENDPOINT_DELETE_ITEM = "/api/item/%s";
    private static final String ENDPOINT_GET_ITEM_FROM_API = "/api/item/direct";

    private static final int ITEM_ID = 1675;
    private static final String ITEM_NAME = "Curtana";
    private static final int LEVEL_EQUIP = 50;
    private static final int LEVEL_ITEM = 80;
    private static final String CLASS_JOB_CATEGORY = "PLD";
    private static final String ICON = "/i/030000/030446.png";

    private String payload;
    private ItemDetails itemDetails = new ItemDetails(ITEM_ID, ITEM_NAME, LEVEL_EQUIP, LEVEL_ITEM, CLASS_JOB_CATEGORY,
            ICON);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Value("classpath:/fixtures/item_request.json")
    private Resource itemRequest;

    @Value("classpath:/fixtures/api_response.json")
    private Resource apiResponse;

    @Captor
    private ArgumentCaptor<ItemRequest> itemRequestArgumentCaptor;

    @BeforeEach
    public void setUp() throws Exception {
        payload = asString(itemRequest);
    }

    @Test
    public void testCreateItem() throws Exception {
        MockHttpServletRequestBuilder request = post(ENDPOINT_CREATE_ITEM)
                .accept(MediaType.ALL_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(payload);

        mockMvc.perform(request)
                .andExpect(status().isCreated());

        verify(itemService).createItem(itemRequestArgumentCaptor.capture());
        assertThat(itemRequestArgumentCaptor.getValue().getId()).isEqualTo(ITEM_ID);
    }

    @Test
    public void testGetItem() throws Exception {
        when(itemService.getItem(ITEM_ID)).thenReturn(itemDetails);

        MockHttpServletRequestBuilder request = get(String.format(ENDPOINT_GET_ITEM, ITEM_ID))
                .contentType(MediaType.ALL_VALUE);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ITEM_ID))
                .andExpect(jsonPath("$.name").value(ITEM_NAME))
                .andExpect(jsonPath("$.equip_level").value(LEVEL_EQUIP))
                .andExpect(jsonPath("$.item_level").value(LEVEL_ITEM))
                .andExpect(jsonPath("$.class_job_category").value(CLASS_JOB_CATEGORY))
                .andExpect(jsonPath("$.icon").value(ICON));
    }

    @Test
    public void testDeleteItem() throws Exception {
        MockHttpServletRequestBuilder request = delete(String.format(ENDPOINT_DELETE_ITEM, ITEM_ID))
                .contentType(MediaType.ALL_VALUE);

        mockMvc.perform(request)
                .andExpect(status().isOk());

        verify(itemService).deleteItem(ITEM_ID);
    }

    @Test
    public void testGetItemFromApi() throws Exception {
        // Create an ItemResponse.
        ObjectMapper objectMapper = new ObjectMapper();
        ItemResponse itemResponse = objectMapper.readValue(asString(apiResponse), ItemResponse.class);

        when(itemService.getItemFromApi(any())).thenReturn(CompletableFuture.completedFuture(itemResponse));

        MockHttpServletRequestBuilder request = post(ENDPOINT_GET_ITEM_FROM_API)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(payload);

        final MvcResult result = mockMvc.perform(request).andReturn();
        mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ID").value(ITEM_ID))
                .andExpect(jsonPath("$.Name_en").value(ITEM_NAME))
                .andExpect(jsonPath("$.LevelEquip").value(LEVEL_EQUIP))
                .andExpect(jsonPath("$.LevelItem").value(LEVEL_ITEM))
                .andExpect(jsonPath("$.ClassJobCategory.Name_en").value(CLASS_JOB_CATEGORY))
                .andExpect(jsonPath("$.Icon").value(ICON));

        verify(itemService).getItemFromApi(itemRequestArgumentCaptor.capture());
        assertThat(itemRequestArgumentCaptor.getValue().getId()).isEqualTo(ITEM_ID);
    }

    private static String asString(Resource resource) throws Exception {
        return resource == null ? null : IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
    }
}
