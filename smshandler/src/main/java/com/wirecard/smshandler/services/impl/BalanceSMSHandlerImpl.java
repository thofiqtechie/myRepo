package com.wirecard.smshandler.services.impl;

import com.wirecard.smshandler.constants.SMSResponseEnum;
import com.wirecard.smshandler.exception.ProcessingErrorException;
import com.wirecard.smshandler.services.SMSHandler;
import com.wirecard.smshandler.services.external.UserManager;
import com.wirecard.smshandler.util.SMSHandlerUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by thofi_000 on 11/18/2017.
 */
@Service(value = "balanceSMSHandler")
@Transactional
public class BalanceSMSHandlerImpl implements SMSHandler{

    private static Logger log = Logger.getLogger(BalanceSMSHandlerImpl.class);

    @Autowired
    private UserManager userManager;

    @Autowired
    private SMSHandlerUtil smsHandlerUtil;

    /**
     * @param smsContent     the incoming SMS command string.
     * @param senderDeviceId is a unique string that uniquely identifies the customer’s mobile device.
     *                       The UserManager proves a means to identify the sender user.
     * @return The SMS content that should be returned to the user.
     */
    @Override
    public String handleSmsRequest(String smsContent, String senderDeviceId) {

        log.info("Invoking BalanceSMSHandlerImpl :: handleSmsRequest with smsContent: "+smsContent+" " +
                "and senderDeviceId : "+senderDeviceId);

        try {
            String username = userManager.getUserNameForDeviceId(senderDeviceId);
            log.debug("User name : "+username);
            if (userManager.existsUser(username)) {
                return String.valueOf(userManager.getBalance(username));
            } else {
                return smsHandlerUtil.getValueFromMessageProperty(SMSResponseEnum.ERR_NO_USER.name());
            }
        }catch (Exception exception){
            log.error(exception);
            throw new ProcessingErrorException(exception.getMessage());
        }

    }
}
