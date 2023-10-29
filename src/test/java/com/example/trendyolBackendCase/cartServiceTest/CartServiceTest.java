package com.example.trendyolBackendCase.cartServiceTest;

import com.example.trendyolBackendCase.dto.ItemDTO;
import com.example.trendyolBackendCase.entity.Cart;
import com.example.trendyolBackendCase.entity.Item;
import com.example.trendyolBackendCase.repository.DefaultItemVasItemRepository;
import com.example.trendyolBackendCase.repository.ItemRepository;
import com.example.trendyolBackendCase.service.CartService;
import com.example.trendyolBackendCase.service.PromotionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartServiceTest {


    @Mock
    private ItemRepository itemRepository;

    @Mock
    private DefaultItemVasItemRepository defaultItemVasItemRepository;

    @Mock
    private PromotionService promotionService;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetTotalAmountOfCart() {
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setPrice(10);
        item1.setQuantity(2);
        items.add(item1);

        Item item2 = new Item();
        item2.setPrice(20);
        item2.setQuantity(3);
        items.add(item2);

        when(itemRepository.findAll()).thenReturn(items);
        double totalAmount = cartService.getTotalAmountOfCart();
        assertEquals(10 * 2 + 20 * 3, totalAmount);
    }

    @Test
    public void testGetTotalQuantityOfCart() {
        List<Item> items = new ArrayList<>();

        Item item1 = new Item();
        item1.setQuantity(2);
        items.add(item1);

        Item item2 = new Item();
        item2.setQuantity(3);
        items.add(item2);

        when(itemRepository.findAll()).thenReturn(items);
        int totalQuantity = cartService.getTotalQuantityOfCart();
        assertEquals(2 + 3, totalQuantity);
    }

    @Test
    public void testResetCart() {
        assertTrue(cartService.resetCart());
    }

    @Test
    public void testIsCartValid(){
        when(itemRepository.count()).thenReturn(2L);
        Item item = new Item();
        item.setQuantity(1);
        assertTrue(cartService.isCartValidToAddItem(item));
    }

    @Test
    public void testInvalidCartExceedingDistinctItemCount() {
        when(itemRepository.count()).thenReturn((long) Cart.MAX_DISTINCT_ITEM_COUNT);
        Item item = new Item();
        assertFalse(cartService.isCartValidToAddItem(item));
    }

    @Test
    public void testInvalidCartExceedingTotalItemCount() {
        when(itemRepository.count()).thenReturn(1L);
        Item item = new Item();
        item.setQuantity(Cart.MAX_TOTAL_ITEM_COUNT_OF_CART + 1);
        assertFalse(cartService.isCartValidToAddItem(item));
    }

    @Test
    public void testInvalidCartExceedingTotalAmount() {
        when(itemRepository.count()).thenReturn(1L);

        Item item = new Item();
        item.setQuantity(1);
        item.setPrice(Cart.MAX_TOTAL_AMOUNT_OF_CART + 1);

        assertFalse(cartService.isCartValidToAddItem(item));
    }

}
