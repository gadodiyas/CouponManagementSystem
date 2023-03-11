package model;

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
}
