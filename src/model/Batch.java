package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Batch {
    private static  int cnt = 0;
    private int id;

    private CouponType couponType;
    private BatchState batchState;
    private LocalDate startDate;
    private LocalDate endDate;

    //For now taking distributor as just name, can make Distributor an entity if needed
    private String distributor;

    private Map<Integer, Coupon> usedcoupons = new HashMap<>();

    public Batch(LocalDate start, LocalDate end, String distributor, CouponType couponType) {
        id = ++cnt;
        this.startDate = start;
        endDate = end;
        this.distributor = distributor;
        this.batchState = BatchState.CREATED;
        this.couponType = couponType;
    }

    public static int getCnt() {
        return cnt;
    }

    public int getId() {
        return id;
    }

    public BatchState getBatchState() {
        return batchState;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Map<Integer, Coupon> getUsedcoupons() {
        return usedcoupons;
    }

    public CouponType getCouponType() {
        return couponType;
    }

    public void setCouponType(CouponType couponType) {
        this.couponType = couponType;
    }

    public void setUsedcoupons(Map<Integer, Coupon> usedcoupons) {
        this.usedcoupons = usedcoupons;
    }

    public void setBatchState(BatchState batchState) {
        this.batchState = batchState;
    }

    public void checkBatchValidity(){
        /*ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Runnable() {
                                          @Override
                                          public void run() {
                                              if(LocalDate.now().equals(startDate)) {
                                                  batchState = BatchState.ACTIVE;

                                              } else if(LocalDate.now().equals(endDate)){
                                                  batchState = BatchState.EXPIRED;
                                              }
                                          }
                                      },
                0,
                TimeUnit.DAYS.toSeconds(1),
                TimeUnit.SECONDS);

*/

    }

    @Override
    public String toString() {
        return "Batch{" +
                "id=" + id +
                ", couponType=" + couponType +
                ", batchState=" + batchState +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", distributor='" + distributor + '\'' +
                ", usedcoupons=" + usedcoupons +
                '}';
    }
}
