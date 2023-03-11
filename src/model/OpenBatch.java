package model;

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
