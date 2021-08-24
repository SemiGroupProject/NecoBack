package com.trade.project.item.domain;

import lombok.Getter;

@Getter
public enum ShippingPrice {
    YES, NO;

    ShippingPrice() {
    }

    public static ShippingPrice convertShippingPrice(String value) {
        switch (value) {
            case "yes" :
                return ShippingPrice.YES;
            case "no" :
                return ShippingPrice.NO;
            default:
                throw new IllegalArgumentException("wrong value on shipping price");
        }
    }
}
