package service;

import exception.InvalidBatchCreationRequest;
import model.*;
import repository.BatchRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CloseBatchService implements BatchServiceI{

    BatchRepository batchRepository = new BatchRepository();
    public Batch createBatch(LocalDate start, LocalDate end, String distributor, CouponType couponType, Integer maxNoOfCoupons) {
        Batch batch;
        if(start == null || end == null || distributor == null) {
            throw new InvalidBatchCreationRequest();
        }

        return new CloseBatch(start, end, distributor);

    }

    public void ingestCoupons(Batch batch, HashSet<String> codes) {

            Set<Coupon> coupons = new HashSet<>();
            for(String code : codes){
                coupons.add(new Coupon(code));
            }
            CloseBatch closeBatch = (CloseBatch)batch;
            closeBatch.getUnusedcoupons().addAll(coupons);

    }

    @Override
    public Coupon grantCoupon(Batch batch) {
        CloseBatch closeBatch = (CloseBatch) batch;
        Coupon coupon = closeBatch.getUnusedcoupons().remove(0);
        coupon.setUsed(true);
        closeBatch.getUsedcoupons().put(coupon.getCouponId(), coupon);
        return coupon;
    }

    @Override
    public int getCouponCount(Batch batch) {
        CloseBatch closeBatch = (CloseBatch) batch;
        return closeBatch.getUnusedcoupons().size();
    }


}
