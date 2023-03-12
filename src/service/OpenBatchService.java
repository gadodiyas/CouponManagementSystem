package service;

import model.*;
import repository.BatchRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class OpenBatchService implements BatchServiceI{

    BatchRepository batchRepository = new BatchRepository();

    public Batch createBatch(LocalDate start, LocalDate end, String distributor, CouponType couponType, Integer maxNoOfCoupons) {
        Batch batch;
        if(start == null || end == null || distributor == null || maxNoOfCoupons == null) {
            throw new RuntimeException("INVALID_BATCH_REQUEST");
        }
        return new OpenBatch(start, end, distributor, maxNoOfCoupons);

    }

    public void ingestCoupons(Batch batch, HashSet<String> codes) {

        OpenBatch openBatch = (OpenBatch)batch;
            openBatch.setCouponCode(codes.stream().findFirst().get());
        }

    @Override
    public Coupon grantCoupon(Batch batch) {
        OpenBatch openBatch = (OpenBatch) batch;
        Coupon coupon= new Coupon(openBatch.getCouponCode());
        coupon.setUsed(true);
        openBatch.getUsedcoupons().put(coupon.getCouponId(), coupon);
        return coupon;
    }

    @Override
    public int getCouponCount(Batch batch) {
        OpenBatch openBatch = (OpenBatch) batch;
        return openBatch.getMaxNoOfCoupons()-batch.getUsedcoupons().size();
    }
}




