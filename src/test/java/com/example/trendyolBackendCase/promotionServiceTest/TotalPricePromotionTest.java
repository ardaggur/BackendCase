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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class TotalPricePromotionTest {

    @Mock
    private ItemRepository itemRepository;
    @Mock
    private CartService cartService;
    @InjectMocks
    private PromotionService promotionService;

    @BeforeEach
    public void setUp(){MockitoAnnotations.initMocks(this);}

    @Test
    public void testTotalPricePromotionTrue(){
        List<Item> items = Arrays.asList(
                new Item(1,200.0,1000,3003,2),
                new Item(2,10.0,1000,1002,1),
                new Item(3,10.0,5003,1002,1)
        );

        when(itemRepository.findAll()).thenReturn(items);
        when(cartService.getTotalAmountOfCart()).thenReturn(420.0);
        assertTrue(promotionService.isTotalPricePromotionApplicable());
    }

    @Test
    public void testTotalPricePromotionFalse(){
        List<Item> items = Arrays.asList(
                new Item(1,200.0,1000,3003,1),
                new Item(2,10.0,1000,1002,1),
                new Item(3,10.0,5003,1002,1)
        );

        when(itemRepository.findAll()).thenReturn(items);
        when(cartService.getTotalAmountOfCart()).thenReturn(220.0);
        assertFalse(promotionService.isTotalPricePromotionApplicable());
    }


}
