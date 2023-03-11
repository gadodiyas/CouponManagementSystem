package service;

import model.Batch;
import model.CloseBatch;
import model.CouponType;
import model.OpenBatch;

import java.time.LocalDate;
import java.util.HashSet;

public class BatchService {


    public Batch createBatch(LocalDate start, LocalDate end, String distributor, CouponType couponType, Integer maxNoOfCoupons) {
        Batch batch;
        if(start == null || end == null || distributor == null) {
            throw new RuntimeException("INVALID_BATCH_REQUEST");
        }

        if(CouponType.OPEN == couponType ) {
            if(maxNoOfCoupons == null)
                throw new RuntimeException("INVALID_BATCH_REQUEST");
           batch = new OpenBatch(start, end, distributor, maxNoOfCoupons);
        } else {
            batch = new CloseBatch(start, end, distributor);
        }
        return batch;
    }



}
