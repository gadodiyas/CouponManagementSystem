package model;

public class Coupon {
    static int cnt = 0;
    private int couponId;
    private String couponCode;

    private boolean used;


    public Coupon(String code) {
        couponId = ++cnt;
        couponCode = code;
    }

    public int getCouponId() {
        return couponId;
    }


    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
