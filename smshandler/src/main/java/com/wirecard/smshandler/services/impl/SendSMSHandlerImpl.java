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

/**
 * Created by thofi_000 on 11/18/2017.
 */
@Service(value = "sendSMSHandler")
@Transactional
public class SendSMSHandlerImpl implements SMSHandler{

    private static Logger log = Logger.getLogger(SendSMSHandlerImpl.class);

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

        try {

            String senderUserName = userManager.getUserNameForDeviceId(senderDeviceId);

            log.debug("Sender user name :"+senderUserName);

            if (userManager.existsUser(senderUserName)) {
                int tokenCount = 0;
                BigDecimal amountToTransfer = null;

                //If send command is changed later to transfer multiple user we can use this block of code
                    for(String token : tokens){
                        //first token is command
                        if(tokenCount == 0){
                            tokenCount++;
                            continue;
                        }else if(tokenCount == 1){
                            //Second token is amount to transfer
                            amountToTransfer = new BigDecimal(token);
                            tokenCount++;
                            continue;
                        }else if(tokenCount > 1){
                            //After second token consider as user name to transfer it
                            String recipientUsername = token;

                            log.debug("Recipient user name:"+recipientUsername);

                            if(userManager.existsUser(recipientUsername)){
                                if(userManager.getBalance(senderUserName).compareTo(amountToTransfer) > 0){
                                    transferManager.sendMoney(senderUserName, recipientUsername, amountToTransfer);
                                    continue;
                                }else{
                                    return smsHandlerUtil.getValueFromMessageProperty(SMSResponseEnum.ERR_INSUFFICIENT_FUND.name());
                                }
                            }else{
                                return smsHandlerUtil.getValueFromMessageProperty(SMSResponseEnum.ERR_NO_USER.name());
                            }
                        }
                    }
                return smsHandlerUtil.getValueFromMessageProperty(SMSResponseEnum.SUCCESS_RESPONSE_OK.name());
            } else {
                return smsHandlerUtil.getValueFromMessageProperty(SMSResponseEnum.ERR_NO_USER.name());
            }
        }catch (Exception exception){
            log.error(exception);
            throw new ProcessingErrorException(exception.getMessage());
        }
    }
}
