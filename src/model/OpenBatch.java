package model;

import interfaces.BatchDetails;

import java.time.LocalDate;

public class OpenBatch extends Batch {
    private int maxNoOfCoupons;

    private String couponCode;

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public int getMaxNoOfCoupons() {
        return maxNoOfCoupons;
    }

    @Override
    public Coupon grantCoupon() {
        if(getBatchState() != BatchState.ACTIVE) {
            throw new RuntimeException("BATCH_NOT_ACTIVE");
        }
        if(getUsedcoupons().size() == getMaxNoOfCoupons()) {
            throw new RuntimeException("BATCH_COUPON_EXHAUSTED");
        }
        Coupon coupon= new Coupon(getCouponCode());
        getUsedcoupons().put(coupon.getCouponId(), coupon);
        return coupon;
    }

    public void setMaxNoOfCoupons(int maxNoOfCoupons) {
        this.maxNoOfCoupons = maxNoOfCoupons;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public OpenBatch(LocalDate start, LocalDate end, String distributor, int maxNoOfCoupons) {
        super(start, end, distributor);
        this.maxNoOfCoupons = maxNoOfCoupons;
    }
}
