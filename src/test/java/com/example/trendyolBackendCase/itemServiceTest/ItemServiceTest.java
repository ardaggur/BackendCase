package com.example.trendyolBackendCase.itemServiceTest;

import com.example.trendyolBackendCase.dto.DefaultItemVasItemDTO;
import com.example.trendyolBackendCase.entity.DefaultItem;
import com.example.trendyolBackendCase.entity.DigitalItem;
import com.example.trendyolBackendCase.entity.Item;
import com.example.trendyolBackendCase.entity.VasItem;
import com.example.trendyolBackendCase.exception.ItemNotFoundException;
import com.example.trendyolBackendCase.repository.DefaultItemVasItemRepository;
import com.example.trendyolBackendCase.repository.ItemRepository;
import com.example.trendyolBackendCase.service.CartService;
import com.example.trendyolBackendCase.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private DefaultItemVasItemRepository defaultItemVasItemRepository;

    @Mock
    private CartService cartService;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIsItemValidToAdd() {
        Item item = new Item();
        item.setQuantity(Item.MAX_ITEM_COUNT + 1);
        assertFalse(itemService.isItemValidToAdd(item));

        item.setCategoryId(DigitalItem.DIGITAL_ITEM_CATEGORY_ID);
        item.setQuantity(DigitalItem.MAX_DIGITAL_ITEM_QUANTITY + 1);
        assertFalse(itemService.isItemValidToAdd(item));

        item.setCategoryId(VasItem.VAS_ITEM_CATEGORY_ID);
        item.setSellerId(VasItem.VAS_ITEM_SELLER_ID);
        assertTrue(itemService.isItemValidToAdd(item));
    }

    @Test
    public void testAddItemToCart() {
        Item item = new Item();
        when(cartService.isCartValidToAddItem(item)).thenReturn(true);
        when(itemRepository.save(item)).thenReturn(item);

        assertTrue(itemService.addItemToCart(item));

        when(cartService.isCartValidToAddItem(item)).thenReturn(false);
        assertFalse(itemService.addItemToCart(item));
    }

    @Test
    public void testRemoveItemFromCart() {
        int itemId = 1;
        Item item = new Item();
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
        assertTrue(itemService.removeItemFromCart(itemId));

    }

    @Test
    public void testAddVasItemToItem() {
        DefaultItemVasItemDTO dto = new DefaultItemVasItemDTO();
        dto.setItemId(1);
        dto.setVasItemId(2);

        Item defaultItem = new Item();
        defaultItem.setId(dto.getItemId());
        defaultItem.setPrice(100.0);

        Item vasItem = new Item();
        vasItem.setId(dto.getVasItemId());
        vasItem.setPrice(90.0);

        when(itemRepository.findById(dto.getItemId())).thenReturn(Optional.of(defaultItem));
        when(itemRepository.findById(dto.getVasItemId())).thenReturn(Optional.of(vasItem));
        when(defaultItemVasItemRepository.findAllByDefaultItemId(dto.getItemId())).thenReturn(Optional.empty());

        assertTrue(itemService.addVasItemToItem(dto));

    }

}
