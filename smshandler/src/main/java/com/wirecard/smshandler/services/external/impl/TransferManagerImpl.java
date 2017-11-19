package com.wirecard.smshandler.services.external.impl;

import com.wirecard.smshandler.services.external.TransferManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thofi_000 on 11/18/2017.
 * Transfer manager dummy implementation to test sms handler
 */
@Service
@Transactional
public class TransferManagerImpl implements TransferManager {

    private static Logger log = Logger.getLogger(TransferManagerImpl.class);

    private List<TransactionDetail> transactionDetails = new ArrayList<>();

    @Override
    public void sendMoney(String senderUsername, String recipientUsername, BigDecimal amount) {
        log.info("TransferManagerImpl :: sendMoney -- entry");
        transactionDetails.add(new TransactionDetail(senderUsername, recipientUsername, amount));
        log.info("TransferManagerImpl :: sendMoney -- exit");
    }

    @Override
    public List<BigDecimal> getAllTransactions(String senderUsername, String recipientUsername) {
        List<BigDecimal> transferredAmounts = new ArrayList<>();
        for(TransactionDetail transactionDetail : transactionDetails){
            if(transactionDetail.getSenderUsername().equals(senderUsername) &&
                    transactionDetail.getRecipientUsername().equals(recipientUsername)){
                transferredAmounts.add(transactionDetail.getTransferredAmount());
            }
        }
        return transferredAmounts;
    }

    /**
     * Dummy class for transaction detail
     */
    class TransactionDetail{

        private String senderUsername;
        private String recipientUsername;
        private BigDecimal transferredAmount;

        public TransactionDetail(String senderUsername, String recipientUsername, BigDecimal transferredAmount) {
            this.senderUsername = senderUsername;
            this.recipientUsername = recipientUsername;
            this.transferredAmount = transferredAmount;
        }

        public String getSenderUsername() {
            return senderUsername;
        }

        public String getRecipientUsername() {
            return recipientUsername;
        }

        public BigDecimal getTransferredAmount() {
            return transferredAmount;
        }
    }
}
