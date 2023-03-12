import interfaces.BatchDetails;
import model.Batch;
import model.BatchState;
import model.Coupon;
import model.CouponType;
import service.CouponManagementSystem;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        CouponManagementSystem couponManagementSystem = new CouponManagementSystem();

        Batch batch1 = couponManagementSystem.createBatch(LocalDate.of(2023, Month.MARCH, 1), LocalDate.of(2023, Month.MARCH, 30), "Distributor xyz", CouponType.OPEN, 20);
        Batch batch2 = couponManagementSystem.createBatch(LocalDate.of(2023, Month.MARCH, 1), LocalDate.of(2023, Month.MARCH, 30), "Distributor xyz", CouponType.CLOSE);

        couponManagementSystem.updateState(batch1.getId(), BatchState.APPROVED);
        couponManagementSystem.updateState(batch2.getId(), BatchState.APPROVED);

        BatchDetails batch = couponManagementSystem.getBatch(batch1.getId());
        couponManagementSystem.ingestCoupons(batch2.getId(), new HashSet<>(Arrays.asList("a", "b", "c")));
        Coupon coupon = couponManagementSystem.grantCoupon(batch1.getId());
        int cnt = couponManagementSystem.getCouponsCount(batch1.getId());
    }
}