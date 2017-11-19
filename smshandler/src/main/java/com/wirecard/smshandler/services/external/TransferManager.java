package com.wirecard.smshandler.services.external;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by thofi_000 on 11/18/2017.
 * Transfer manager contract
 */
public interface TransferManager {

    void sendMoney(String senderUsername, String recipientUsername, BigDecimal amount);

    List<BigDecimal> getAllTransactions(String senderUsername, String recipientUsername);

}
