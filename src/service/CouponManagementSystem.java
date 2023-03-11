package service;

import model.*;

import java.time.LocalDate;
import java.util.*;

public class CouponManagementSystem {
    private Map<Integer, OpenBatch> openBatches = new HashMap<>();

    private Map<Integer, CloseBatch> closedBatches = new HashMap<>();
    BatchService batchService = new BatchService();
    public Batch createBatch(LocalDate start, LocalDate end, String distributor, CouponType couponType, Integer maxNOOfCoupons) {
        Batch batch = batchService.createBatch(start, end, distributor, couponType, maxNOOfCoupons);
        if(batch instanceof OpenBatch) {
            openBatches.put(batch.getId(), (OpenBatch) batch);
        }
        else
            closedBatches.put(batch.getId(),(CloseBatch) batch);


        return batch;
    }

    public Batch createBatch(LocalDate start, LocalDate end, String distributor, CouponType couponType) {
        return createBatch(start, end, distributor, couponType, null);
    }

    public void ingestCoupons(int id, HashSet<String> codes) {

        //Assuming for open batch we set coupon code for all as first code
        if(codes.isEmpty()) {
            throw  new RuntimeException("Cannot add injest empty set");
        }
        if(openBatches.containsKey(id)) {
            openBatches.get(id).setCouponCode(codes.stream().findFirst().get());
        }
        else{
            Set<Coupon> coupons = new HashSet<>();
            for(String code : codes){
                coupons.add(new Coupon(code));
            }
            closedBatches.get(id).getUnusedcoupons().addAll(coupons);
        }
    }

    public void updateState(int id, BatchState batchState) {
        if(openBatches.containsKey(id)) {
            openBatches.get(id).setBatchState(batchState);
        }
        else if(closedBatches.containsKey(id)){
            closedBatches.get(id).setBatchState(batchState);
        }
        else
            throw new RuntimeException("Batch not found");
    }

    public Batch getBatch(int id) {
        if(openBatches.containsKey(id)) {
            return openBatches.get(id);
        }
        else if(closedBatches.containsKey(id)){
            return closedBatches.get(id);
        }
        else
            throw new RuntimeException("Batch not found");
    }

    public Coupon grantCoupon(int id) {
        if(openBatches.containsKey(id)) {
            OpenBatch batch = openBatches.get(id);
            if(batch.getBatchState() != BatchState.ACTIVE) {
                throw new RuntimeException("BATCH_NOT_ACTIVE");
            }
            if(batch.getUsedcoupons().size() == batch.getMaxNoOfCoupons()) {
                throw new RuntimeException("BATCH_COUPON_EXHAUSTED");
            }
            Coupon coupon= new Coupon(batch.getCouponCode());
            batch.getUsedcoupons().put(coupon.getCouponId(), coupon);
            return coupon;

        }
        else if(closedBatches.containsKey(id)){
            CloseBatch batch = closedBatches.get(id);
            if(batch.getBatchState() != BatchState.ACTIVE) {
                throw new RuntimeException("BATCH_NOT_ACTIVE");
            }
            if(batch.getUnusedcoupons().isEmpty()) {
                throw new RuntimeException("BATCH_COUPON_EXHAUSTED");
            }
            Coupon coupon = batch.getUnusedcoupons().remove(0);
            batch.getUsedcoupons().put(coupon.getCouponId(), coupon);
            return coupon;
        }
        else
            throw new RuntimeException("Batch not found");
    }

    public int getCouponsCount(int id) {
        if(openBatches.containsKey(id)) {
            OpenBatch batch = openBatches.get(id);
            return batch.getMaxNoOfCoupons()-batch.getUsedcoupons().size();
        }
        else if(closedBatches.containsKey(id)){
            CloseBatch batch = closedBatches.get(id);
            return batch.getUnusedcoupons().size();
        }
        else
            throw new RuntimeException("Batch not found");
    }
}
