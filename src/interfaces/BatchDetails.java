package interfaces;

import model.Coupon;

public interface BatchDetails {

    int getMaxNoOfCoupons();

    Coupon grantCoupon();
}
