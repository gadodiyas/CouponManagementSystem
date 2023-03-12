package repository;

import exception.BatchNotFoundException;
import model.Batch;

import java.util.HashMap;
import java.util.Map;

public class BatchRepository {
    private Map<Integer, Batch> batches = new HashMap<>();

    public Batch getBatch(int batchId) {
        Batch batch = batches.get(batchId);
        if(batch == null)
            throw new BatchNotFoundException();
        return batch;
    }

    public void addBatch(Batch batch) {
        batches.put(batch.getId(), batch);

    }
}
