package com.wirecard.smshandler.controller;

import com.wirecard.smshandler.exception.ProcessingErrorException;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by thofi_000 on 11/18/2017.
 * Dummy implementation of exception handler
 */
public class SMSHandlerBaseController {

    private static Logger log = Logger.getLogger(SMSHandlerBaseController.class);

    @ExceptionHandler(ProcessingErrorException.class)
    public void handleProcessingErrorException(ProcessingErrorException processingError){
        log.info("Processing error exception handler");

    }
}
