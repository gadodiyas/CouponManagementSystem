package service;

import model.Batch;
import model.Coupon;
import model.CouponType;

import java.time.LocalDate;
import java.util.HashSet;

public interface BatchServiceI {
    Batch createBatch(LocalDate start, LocalDate end, String distributor, CouponType couponType, Integer maxNOOfCoupons);

    public void ingestCoupons(Batch batch, HashSet<String> codes);

    Coupon grantCoupon(Batch batch);

    int getCouponCount(Batch batch);
}
