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

    public boolean isItemValidToAdd(Item item)
    {
        if(item.getQuantity() > Item.MAX_ITEM_COUNT)
        {
            return false;
        }
        else if(item.getCategoryId() == DigitalItem.DIGITAL_ITEM_CATEGORY_ID &&
                item.getQuantity() > DigitalItem.MAX_DIGITAL_ITEM_QUANTITY)
        {
            return false;
        }
        else if(item.getCategoryId() == VasItem.VAS_ITEM_CATEGORY_ID && item.getSellerId() != VasItem.VAS_ITEM_SELLER_ID)
        {
            return false;
        }
        return true;
    }

    public Boolean addItemToCart(Item item)
    {
        try {
            if (cartService.isCartValidToAddItem(item) && isItemValidToAdd(item)) {
                itemRepository.save(item);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public Boolean removeItemFromCart(Integer itemId) {
        try {
            itemRepository.findById(itemId).ifPresentOrElse(
                    item -> itemRepository.delete(item),
                    () -> {
                        throw new ItemNotFoundException();
                    }
            );
            return true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean addVasItemToItem(DefaultItemVasItemDTO defaultItemVasItemDTO) {

        Optional<Item> defaultItemOptional = itemRepository.findById(defaultItemVasItemDTO.getItemId());
        if(defaultItemOptional.isEmpty())
        {
            return false;
        }
        Optional<Item> vasItemOptional = itemRepository.findById(defaultItemVasItemDTO.getVasItemId());
        Item vasItem = vasItemOptional.get();
        if(vasItem.getPrice() > defaultItemOptional.get().getPrice()){
            return false;
        }
        else{
            Item defaultItem = defaultItemOptional.get();
            Optional<List<DefaultItemVasItem>> defaultItemVasItemsOptional = defaultItemVasItemRepository.findAllByDefaultItemId(defaultItem.getId());
            if(defaultItemVasItemsOptional.isPresent())
            {
                if(defaultItemVasItemsOptional.get().size() < DefaultItem.MAX_VAS_ITEM_COUNT)
                {
                    saveDefaultItemVasItem(defaultItemVasItemDTO);
                    return true;
                }
            }
            else{
                saveDefaultItemVasItem(defaultItemVasItemDTO);
                return true;
            }
        }
        return false;
    }

    private void saveDefaultItemVasItem(DefaultItemVasItemDTO defaultItemVasItemDTO) {
        DefaultItemVasItem defaultItemVasItem = new DefaultItemVasItem();
        defaultItemVasItem.setVasItemId(defaultItemVasItemDTO.getVasItemId());
        defaultItemVasItem.setDefaultItemId(defaultItemVasItemDTO.getItemId());
        defaultItemVasItemRepository.save(defaultItemVasItem);
    }


}
