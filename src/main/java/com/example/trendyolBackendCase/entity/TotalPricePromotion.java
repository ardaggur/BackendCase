package com.example.trendyolBackendCase.entity;

public class TotalPricePromotion extends Promotion{

    public static final int TOTAL_PRICE_PROMOTION_ID = 1232;
    public static final double MIN_AMOUNT_TO_APPLY_PROMOTION = 250.0;
    public static final double DISCOUNT_1 = 250.0;
    public static final double DISCOUNT_2 = 500.0;
    public static final double DISCOUNT_3 = 1000.0;
    public static final double DISCOUNT_4 = 2000.0;
    public static final double THRESHOLD_1 = 5000.0;
    public static final double THRESHOLD_2 = 10000.0;
    public static final double THRESHOLD_3 = 50000.0;

    public TotalPricePromotion(){
        super(TOTAL_PRICE_PROMOTION_ID);
    }




}
