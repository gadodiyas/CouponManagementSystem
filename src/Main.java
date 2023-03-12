import model.Batch;
import model.BatchState;
import model.Coupon;
import model.CouponType;
import controller.CouponManagementController;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        CouponManagementController couponManagementController = new CouponManagementController();

        Batch batch1 = couponManagementController.createBatch(LocalDate.of(2023, Month.MARCH, 1), LocalDate.of(2023, Month.MARCH, 30), "Distributor xyz", CouponType.OPEN, 20);
        Batch batch2 = couponManagementController.createBatch(LocalDate.of(2023, Month.MARCH, 1), LocalDate.of(2023, Month.MARCH, 30), "Distributor xyz", CouponType.CLOSE, null);

        couponManagementController.updateState(batch1.getId(), BatchState.APPROVED);
        couponManagementController.updateState(batch2.getId(), BatchState.APPROVED);

        couponManagementController.getBatch(batch1.getId());
        couponManagementController.getBatch(batch2.getId());
        couponManagementController.ingestCoupons(batch1.getId(), new HashSet<>(Arrays.asList("a", "b", "c")));
        Coupon coupon = couponManagementController.grantCoupon(batch1.getId());
        int cnt = couponManagementController.getCouponsCount(batch1.getId());
    }
}