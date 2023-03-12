package model;

import interfaces.BatchDetails;

import java.time.LocalDate;
import java.util.*;

public class CloseBatch extends Batch {

    //Assuming get coupon by id is only for closebatch
    private List<Coupon> unusedcoupons = new ArrayList<>();


    public CloseBatch(LocalDate start, LocalDate end, String distributor) {
        super(start, end, distributor);
    }

    public List<Coupon> getUnusedcoupons() {
        return unusedcoupons;
    }

    @Override
    public int getMaxNoOfCoupons() {
        return 0;
    }

    @Override
    public Coupon grantCoupon() {
        if(getBatchState() != BatchState.ACTIVE) {
            throw new RuntimeException("BATCH_NOT_ACTIVE");
        }
        if(getUnusedcoupons().isEmpty()) {
            throw new RuntimeException("BATCH_COUPON_EXHAUSTED");
        }
        Coupon coupon = getUnusedcoupons().remove(0);
        getUsedcoupons().put(coupon.getCouponId(), coupon);
        return coupon;
    }
}
