package com.example.trendyolBackendCase.promotionServiceTest;

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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CategoryPromotionTest {

    @Mock
    private ItemRepository itemRepository;
    @Mock
    private CartService cartService;
    @InjectMocks
    private PromotionService promotionService;

    @BeforeEach
    public void setUp(){MockitoAnnotations.initMocks(this);}

    @Test
    public void testIsCategoryPromotionApplicableTrue(){
        List<Item> items = Arrays.asList(
                new Item(1,10.0,1000,3003,1),
                new Item(2,10.0,1000,1002,1),
                new Item(3,10.0,5003,1002,1)
        );

        when(itemRepository.findAll()).thenReturn(items);
        assertTrue(promotionService.isCategoryPromotionApplicable());
    }

    @Test
    public void testIsCategoryPromotionApplicableFalse(){
        List<Item> items = Arrays.asList(
                new Item(1,10.0,1000,1001,1),
                new Item(2,10.0,1000,1002,1),
                new Item(3,10.0,5003,1002,1)
        );

        when(itemRepository.findAll()).thenReturn(items);
        assertTrue(promotionService.isCategoryPromotionApplicable());
    }

    @Test
    public void testGetCategoryPromotionDiscountAmountTrue(){
        List<Item> items = Arrays.asList(
                new Item(1,100.0,1000,3003,1),
                new Item(2,10.0,1000,1002,1),
                new Item(3,10.0,5003,1002,1),
                new Item(4,100.0,1000,3003,2)
        );

        when(itemRepository.findAll()).thenReturn(items);
        when(cartService.getTotalAmountOfCart()).thenReturn(220.0);

        double discount = promotionService.getCategoryPromotionDiscountAmount();
        assertEquals(15.0,discount);
    }

    @Test
    public void testGetCategoryPromotionDiscountAmountFalse(){
        List<Item> items = Arrays.asList(
                new Item(1,100.0,1000,3003,1),
                new Item(2,10.0,1000,1002,1),
                new Item(3,10.0,5003,1002,1),
                new Item(4,100.0,1000,3003,2)
        );

        when(itemRepository.findAll()).thenReturn(items);
        when(cartService.getTotalAmountOfCart()).thenReturn(220.0);

        double discount = promotionService.getCategoryPromotionDiscountAmount();
        assertEquals(10.0,discount);

    }


}
