package controller;

import exception.BatchNotActiveException;
import model.*;
import repository.BatchRepository;
import service.BatchServiceI;
import service.CloseBatchService;
import service.OpenBatchService;

import java.time.LocalDate;
import java.util.*;

public class CouponManagementController {

    private BatchRepository batchRepository = new BatchRepository();

    private Map<CouponType, BatchServiceI> serviceMap = new HashMap<>();


    public CouponManagementController() {
       serviceMap.put(CouponType.OPEN, new OpenBatchService());
       serviceMap.put(CouponType.CLOSE, new CloseBatchService());
    }

    BatchServiceI batchService ;
    public Batch createBatch(LocalDate start, LocalDate end, String distributor, CouponType couponType, Integer maxNOOfCoupons) {
        batchService = serviceMap.get(couponType);
        Batch batch =  batchService.createBatch(start, end, distributor, couponType, maxNOOfCoupons);
        batchRepository.addBatch(batch);
        return batch;

    }

    public void ingestCoupons(int id, HashSet<String> codes) {

        //Assuming for open batch we set coupon code for all as first code
        if(codes.isEmpty()) {
            throw  new RuntimeException("Cannot add injest empty set");
        }
        Batch batch = batchRepository.getBatch(id);
        batchService = serviceMap.get(batch.getCouponType());
        batchService.ingestCoupons(batch, codes);

    }

    public void updateState(int id, BatchState batchState) {
        Batch batch = batchRepository.getBatch(id);
        batch.setBatchState(batchState);
    }

    public Batch getBatch(int id) {
        Batch batch = batchRepository.getBatch(id);
        System.out.println("-------------------------------------------------------------");
        System.out.println(batch.toString());
        return batch;
    }

    public Coupon grantCoupon(int id) {
        Batch batch = batchRepository.getBatch(id);
        if(!isBatchActive(batch)){
            throw new BatchNotActiveException();
        }
        batchService = serviceMap.get(batch.getCouponType());
        Coupon coupon = batchService.grantCoupon(batch);
        System.out.println("-------------------------------------------------------------");
        System.out.println(coupon.toString());

        return coupon;
    }

    private boolean isBatchActive(Batch batch) {
        if(LocalDate.now().isAfter(batch.getStartDate()) && LocalDate.now().isBefore(batch.getEndDate()))
            return true;
        return false;
    }
    public int getCouponsCount(int id) {
        Batch batch = batchRepository.getBatch(id);
        batchService = serviceMap.get(batch.getCouponType());
        return batchService.getCouponCount(batch);
    }
}
