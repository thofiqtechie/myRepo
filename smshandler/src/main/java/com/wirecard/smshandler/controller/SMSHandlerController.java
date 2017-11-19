package com.wirecard.smshandler.controller;

import com.wirecard.smshandler.exception.ProcessingErrorException;
import com.wirecard.smshandler.services.delegate.SMSHandlerDelegate;
import com.wirecard.smshandler.vo.SMSHandlerRequest;
import com.wirecard.smshandler.vo.SMSHandlerResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by thofi_000 on 11/18/2017.
 * This class will act as an api to the external systems to post the sms data for processing
 */
@RestController
@RequestMapping("/sms")
public class SMSHandlerController extends SMSHandlerBaseController{

    private static Logger log = Logger.getLogger(SMSHandlerController.class);

    @Autowired
    private SMSHandlerDelegate smsHandlerDelegate;

    /**
     * Posting the sms data by external system
     * @param smsHandlerRequest
     * @return
     */
    @RequestMapping(path = "/post", method = RequestMethod.POST)
    public SMSHandlerResponse handleSMSRequest(@RequestBody SMSHandlerRequest smsHandlerRequest) throws ProcessingErrorException {
        log.info("Successfully received sms handler request : "+smsHandlerRequest);
        String serviceResult = smsHandlerDelegate.delegateService(smsHandlerRequest);
        log.info("Service Response in controller : "+serviceResult);
        return new SMSHandlerResponse(smsHandlerRequest.getSmsContent(), smsHandlerRequest.getSenderDeviceId(), serviceResult, "200");
    }

}
