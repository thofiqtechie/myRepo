package com.wirecard.smshandler.services.impl;

import com.wirecard.smshandler.constants.SMSHandlerConstants;
import com.wirecard.smshandler.constants.SMSResponseEnum;
import com.wirecard.smshandler.exception.ProcessingErrorException;
import com.wirecard.smshandler.services.SMSHandler;
import com.wirecard.smshandler.services.external.TransferManager;
import com.wirecard.smshandler.services.external.UserManager;
import com.wirecard.smshandler.util.SMSHandlerUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by thofi_000 on 11/18/2017.
 */
@Service(value = "totalSMSHandler")
@Transactional
public class TotalSMSHandlerImpl implements SMSHandler{

    private static Logger log = Logger.getLogger(TotalSMSHandlerImpl.class);

    @Autowired
    private SMSHandlerUtil smsHandlerUtil;

    @Autowired
    private UserManager userManager;

    @Autowired
    private TransferManager transferManager;

    /**
     * @param smsContent     the incoming SMS command string.
     * @param senderDeviceId is a unique string that uniquely identifies the customer’s mobile device.
     *                       The UserManager proves a means to identify the sender user.
     * @return The SMS content that should be returned to the user.
     */
    @Override
    public String handleSmsRequest(String smsContent, String senderDeviceId) {

        log.info("Invoking SendSMSHandlerImpl :: handleSmsRequest with smsContent: "+smsContent+" " +
                "and senderDeviceId : "+senderDeviceId);

        String tokenizer = smsHandlerUtil.getValueFromMessageProperty(SMSHandlerConstants.SMS_COMMAND_TOKENIZER);
        String[] tokens = smsHandlerUtil.splitStringUsingDelimeter(smsContent, tokenizer);

        StringBuffer result = new StringBuffer(SMSHandlerConstants.EMPTY_STRING);

        try {
            String senderUserName = userManager.getUserNameForDeviceId(senderDeviceId);

            log.debug("Sender user name :"+senderUserName);

            if (userManager.existsUser(senderUserName)) {

                int tokenCount = 0;

                //If send command is changed later to transfer multiple user we can use this block of code
                for(String token : tokens){
                    //first and second token is command
                    if(tokenCount == 0 || tokenCount == 1){
                        tokenCount++;
                        continue;
                    }else if(tokenCount > 1){
                        //After second token consider as user name to which money transferred
                        String recipientUsername = token;

                        log.debug("Recipient user name :"+recipientUsername);

                        if(!userManager.existsUser(recipientUsername)){
                            return smsHandlerUtil.getValueFromMessageProperty(SMSResponseEnum.ERR_NO_USER.name());
                        }
                        List<BigDecimal> allTransactions = transferManager.getAllTransactions(senderUserName, recipientUsername);
                        BigDecimal totalAmount = SMSHandlerConstants.ZERO_VALUE;
                        for(BigDecimal transferredAmount : allTransactions){
                            totalAmount = totalAmount.add(transferredAmount);
                        }
                        result.append(totalAmount);
                        if((tokens.length - 1) > tokenCount){
                            result.append(SMSHandlerConstants.COMMA);
                        }

                        tokenCount++;
                    }
                }
                return result.toString();
            } else {
                return smsHandlerUtil.getValueFromMessageProperty(SMSResponseEnum.ERR_NO_USER.name());
            }
        }catch (Exception exception){
            log.error(exception);
            throw new ProcessingErrorException(exception.getMessage());
        }

    }
}
