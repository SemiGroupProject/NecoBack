package com.trade.project.item.domain.enums;

import com.trade.project.common.exceptions.InvalidValueException;
import lombok.Getter;

import static com.trade.project.common.exceptions.ErrorCode.SHIPPING_INVALID_VALUE;

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
               throw new InvalidValueException(SHIPPING_INVALID_VALUE);
        }
    }
}
