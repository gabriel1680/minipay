package com.minipay.application.fixture;

import com.minipay.domain.transfer.Transfer;
import com.minipay.domain.transfer.TransferRepository;

import java.util.ArrayList;
import java.util.List;

public class MemoryTransferRepository implements TransferRepository {
    private final List<Transfer> transfers = new ArrayList<>();

    @Override
    public void save(Transfer transfer) {
        transfers.add(transfer);
    }

    public int count() {
        return transfers.size();
    }
}
