package com.example.trendyolBackendCase.service;

import com.example.trendyolBackendCase.dto.DefaultItemVasItemDTO;
import com.example.trendyolBackendCase.entity.*;
import com.example.trendyolBackendCase.exception.ItemNotFoundException;
import com.example.trendyolBackendCase.repository.DefaultItemVasItemRepository;
import com.example.trendyolBackendCase.repository.ItemRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CartService cartService;
    @Autowired
    DefaultItemVasItemRepository defaultItemVasItemRepository;

    public Boolean addItemToCart(Item item) {
        try {
            if (cartService.isCartValidToAddItem(item) && isItemValidToAdd(item)) {
                itemRepository.save(item);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isItemValidToAdd(Item item) {
        return isItemWithinMaxCount(item) &&
                isDigitalItemValid(item) &&
                isVasItemValid(item);
    }

    public boolean isItemWithinMaxCount(Item item) {
        return item.getQuantity() <= Item.MAX_ITEM_COUNT;
    }

    public boolean isDigitalItemValid(Item item) {
        return item.getCategoryId() != DigitalItem.DIGITAL_ITEM_CATEGORY_ID ||
                item.getQuantity() <= DigitalItem.MAX_DIGITAL_ITEM_QUANTITY;
    }

    public boolean isVasItemValid(Item item) {
        return item.getCategoryId() != VasItem.VAS_ITEM_CATEGORY_ID ||
                item.getSellerId() == VasItem.VAS_ITEM_SELLER_ID;
    }

    public Boolean removeItemFromCart(Integer itemId) {
        try {
            Optional<Item> itemOptional = itemRepository.findById(itemId);
            if (itemOptional.isPresent()) {
                itemRepository.delete(itemOptional.get());
                return true;
            } else {
                throw new ItemNotFoundException("Item not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public Boolean addVasItemToItem(DefaultItemVasItemDTO defaultItemVasItemDTO) {
        Optional<Item> defaultItemOptional = itemRepository.findById(defaultItemVasItemDTO.getItemId());
        Optional<Item> vasItemOptional = itemRepository.findById(defaultItemVasItemDTO.getVasItemId());

        if (defaultItemOptional.isPresent() && vasItemOptional.isPresent()) {
            Item defaultItem = defaultItemOptional.get();
            Item vasItem = vasItemOptional.get();

            if (vasItem.getPrice() <= defaultItem.getPrice() && isUnderMaxVasItemCount(defaultItem)) {
                saveDefaultItemVasItem(defaultItemVasItemDTO);
                return true;
            }
        }
        return false;
    }

    public boolean isUnderMaxVasItemCount(Item defaultItem) {
        Optional<List<DefaultItemVasItem>> defaultItemVasItemsOptional = defaultItemVasItemRepository.findAllByDefaultItemId(defaultItem.getId());
        return defaultItemVasItemsOptional.map(items -> items.size() < DefaultItem.MAX_VAS_ITEM_COUNT).orElse(true);
    }


    public void saveDefaultItemVasItem(DefaultItemVasItemDTO defaultItemVasItemDTO) {
        DefaultItemVasItem defaultItemVasItem = new DefaultItemVasItem();
        defaultItemVasItem.setVasItemId(defaultItemVasItemDTO.getVasItemId());
        defaultItemVasItem.setDefaultItemId(defaultItemVasItemDTO.getItemId());
        defaultItemVasItemRepository.save(defaultItemVasItem);
    }


}
