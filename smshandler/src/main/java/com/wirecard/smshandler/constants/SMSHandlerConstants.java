package com.wirecard.smshandler.constants;

import java.math.BigDecimal;

/**
 * Created by thofi_000 on 11/18/2017.
 */
public interface SMSHandlerConstants {

    String SMS_COMMAND_TOKENIZER = "SMS_COMMAND_TOKENIZER";
    BigDecimal ZERO_VALUE = new BigDecimal(0);
    String EMPTY_STRING = "";
    String COMMA = ",";

    //Error Constants
    String ERR_SMS_BALANCE_SERVICE_01 = "ERR_SMS_BALANCE_SERVICE_01";
    String ERR_SMS_SEND_SERVICE_01 = "ERR_SMS_SEND_SERVICE_01";
    String ERR_SMS_TOTAL_SERVICE_01 = "ERR_SMS_TOTAL_SERVICE_01";

}
