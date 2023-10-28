package com.example.trendyolBackendCase.service;

import com.example.trendyolBackendCase.dto.CartDTO;
import com.example.trendyolBackendCase.dto.ItemDTO;
import com.example.trendyolBackendCase.entity.*;
import com.example.trendyolBackendCase.repository.DefaultItemVasItemRepository;
import com.example.trendyolBackendCase.repository.ItemRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class CartService {
   @Autowired
   ItemRepository itemRepository;
   @Autowired
   DefaultItemVasItemRepository defaultItemVasItemRepository;
   @Autowired
   PromotionService promotionService;

   public boolean isCartValidToAddItem(Item item)
   {
      return (itemRepository.count() < Cart.MAX_DISTINCT_ITEM_COUNT &&
              (getTotalQuantityOfCart() + item.getQuantity()) <= Cart.MAX_TOTAL_ITEM_COUNT_OF_CART &&
              (getTotalAmountOfCart() + item.getQuantity()*item.getPrice())<= Cart.MAX_TOTAL_AMOUNT_OF_CART);
   }

   public Double getTotalAmountOfCart() {
      List<Item> items = itemRepository.findAll();
      return items.stream()
              .mapToDouble(item -> item.getPrice() * item.getQuantity())
              .sum();
   }

   public int getTotalQuantityOfCart() {
      List<Item> items = itemRepository.findAll();
       return items.stream()
              .mapToInt(Item::getQuantity)
              .sum();
   }

   public Boolean resetCart()
   {
      try{
         itemRepository.deleteAll();
         return true;
      }catch(Exception e)
      {
         e.printStackTrace();
      }
      return false;
   }

   public CartDTO displayCart()
   {
      CartDTO cartDTO = new CartDTO();
      //get all items
      List<Item> items = itemRepository.findAll();

      List<ItemDTO> itemDTOs = new ArrayList<>();
      for(Item item : items)
      {
         ItemDTO itemDTO = new ItemDTO();
         itemDTO.setItemId(item.getId());
         itemDTO.setPrice(item.getPrice());
         itemDTO.setSellerId(item.getSellerId());
         itemDTO.setQuantity(item.getQuantity());
         itemDTO.setCategoryId(item.getCategoryId());

         Optional<List<DefaultItemVasItem>> defaultItemVasItemsOptional = defaultItemVasItemRepository.findAllByDefaultItemId(item.getId());
         List<DefaultItemVasItem> defaultItemVasItems = defaultItemVasItemsOptional.get();
         List<Integer> vasItemIds = new ArrayList<>();

         for(DefaultItemVasItem defaultItemVasItem : defaultItemVasItems)
         {
            vasItemIds.add(defaultItemVasItem.getVasItemId());
         }
         List<Item> vasItemsOptional = itemRepository.findAllById(vasItemIds);
         itemDTO.setVasItems(vasItemsOptional);
         itemDTOs.add(itemDTO);
      }

      cartDTO.setItemDTO(itemDTOs);
      cartDTO.setTotalPrice(getTotalAmountOfCart());
      cartDTO.setTotalDiscount(promotionService.getBestPromotionDiscount());
      cartDTO.setAppliedPromotionId(promotionService.getBestPromotionId());

      return cartDTO;

   }

}
