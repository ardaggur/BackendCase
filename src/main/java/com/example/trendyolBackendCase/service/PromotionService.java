package com.example.trendyolBackendCase.service;

import com.example.trendyolBackendCase.entity.*;
import com.example.trendyolBackendCase.repository.ItemRepository;
import com.example.trendyolBackendCase.repository.PromotionRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class PromotionService {

    PromotionRepository promotionRepository;
    ItemRepository itemRepository;
    CartService cartService;

    public boolean isSameSellerPromotionApplicable() {
        List<Item> items = itemRepository.findAll();
        Set<Integer> nonVasSellerIds = items.stream()
                .filter(item -> item.getSellerId() != VasItem.VAS_ITEM_SELLER_ID)
                .map(Item::getSellerId)
                .collect(Collectors.toSet());
        return nonVasSellerIds.size() == 1;
    }

    public double getSameSellerPromotionDiscountAmount() {
        return isSameSellerPromotionApplicable() ? cartService.getTotalAmountOfCart() - (cartService.getTotalAmountOfCart() * SameSellerPromotion.DISCOUNT_RATIO): cartService.getTotalAmountOfCart();
    }

    public Boolean isCategoryPromotionApplicable() {
        List<Item> items = itemRepository.findAll();
        return items.stream().anyMatch(item -> item.getCategoryId() == CategoryPromotion.TARGET_CATEGORY_ID);
    }

    public double getCategoryPromotionDiscountAmount() {
        if (isCategoryPromotionApplicable()) {
            List<Item> items = itemRepository.findAll();
            double totalDiscountedPrice = items.stream()
                    .mapToDouble(item -> {
                        if (item.getCategoryId() == CategoryPromotion.TARGET_CATEGORY_ID) {
                            double originalPrice = item.getPrice();
                            double discountedPrice = originalPrice * CategoryPromotion.DISCOUNT_RATIO;
                            item.setPrice(discountedPrice);
                            return discountedPrice;
                        } else {
                            return item.getPrice();
                        }
                    })
                    .sum();
            return cartService.getTotalAmountOfCart() - totalDiscountedPrice;
        } else {
            return cartService.getTotalAmountOfCart();
        }
    }

    public boolean isTotalPricePromotionApplicable()
    {
        return cartService.getTotalAmountOfCart() > 0;
    }

    public double getTotalPricePromotionDiscountAmount() {
        if (isTotalPricePromotionApplicable()) {
            double totalAmountOfCart = cartService.getTotalAmountOfCart();
            double discount;

            if (totalAmountOfCart < TotalPricePromotion.THRESHOLD_1) {
                discount = TotalPricePromotion.DISCOUNT_1;
            } else if (totalAmountOfCart < TotalPricePromotion.THRESHOLD_2) {
                discount = TotalPricePromotion.DISCOUNT_2;
            } else if (totalAmountOfCart < TotalPricePromotion.THRESHOLD_3) {
                discount = TotalPricePromotion.DISCOUNT_3;
            } else {
                discount = TotalPricePromotion.DISCOUNT_4;
            }

            return discount;
        } else {
            return cartService.getTotalAmountOfCart();
        }
    }

    public double getBestPromotionDiscount() {
        return Math.max(getTotalPricePromotionDiscountAmount(), Math.max(getCategoryPromotionDiscountAmount(), getSameSellerPromotionDiscountAmount()));
    }

    public int getBestPromotionId()
    {
        double bestDiscount = getBestPromotionDiscount();

        if (bestDiscount == getTotalPricePromotionDiscountAmount()) {
            return TotalPricePromotion.TOTAL_PRICE_PROMOTION_ID;
        } else if (bestDiscount == getCategoryPromotionDiscountAmount()) {
            return CategoryPromotion.CATEGORY_PROMOTION_ID;
        } else {
            return SameSellerPromotion.SAME_SELLER_PROMOTION_ID;
        }
    }


}
