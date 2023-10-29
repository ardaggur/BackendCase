package com.example.trendyolBackendCase.promotionServiceTest;

import com.example.trendyolBackendCase.entity.Item;
import com.example.trendyolBackendCase.repository.ItemRepository;
import com.example.trendyolBackendCase.service.CartService;
import com.example.trendyolBackendCase.service.PromotionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class SameSellerPromotionTest {

    @Mock
    private ItemRepository itemRepository;
    @Mock
    private CartService cartService;

    @InjectMocks
    private PromotionService promotionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIsSameSellerPromotionApplicableTrue(){
        List<Item> items = Arrays.asList(
                new Item(1,10.0,1000,1001,1),
                new Item(2,10.0,1000,1002,1),
                new Item(3,10.0,5003,1002,1),
                new Item(4,10.0,1000,1002,2),
                new Item(5,10.0,1000,1002,3),
                new Item(6,10.0,1000,1002,4)
        );

        when(itemRepository.findAll()).thenReturn(items);
        assertTrue(promotionService.isSameSellerPromotionApplicable());
    }

    @Test
    public void testIsSameSellerPromotionApplicableFalse(){
        List<Item> items = Arrays.asList(
                new Item(1,10.0,1000,1001,1),
                new Item(2,10.0,1000,1002,1),
                new Item(3,10.0,5003,1002,1),
                new Item(4,10.0,1000,1002,2),
                new Item(5,10.0,1000,1002,3),
                new Item(6,10.0,999,1002,4)
        );
        when(itemRepository.findAll()).thenReturn(items);
        assertFalse(promotionService.isSameSellerPromotionApplicable());
    }

    @Test
    public void testGetSameSellerPromotionDiscountAmount(){
        List<Item> items = Arrays.asList(
                new Item(1,10.0,1000,1001,1),
                new Item(2,10.0,1000,1002,1),
                new Item(3,10.0,5003,1002,1),
                new Item(4,10.0,1000,1002,2),
                new Item(5,10.0,1000,1002,3),
                new Item(6,10.0,1000,1002,4)
                );

        when(itemRepository.findAll()).thenReturn(items);
        when(cartService.getTotalAmountOfCart()).thenReturn(120.0);

        double discount = promotionService.getSameSellerPromotionDiscountAmount();
        assertEquals(12.0, discount);

    }

}
