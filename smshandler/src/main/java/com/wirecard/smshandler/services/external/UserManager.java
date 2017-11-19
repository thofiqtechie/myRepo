package com.wirecard.smshandler.services.external;

import java.math.BigDecimal;

/**
 * Created by thofi_000 on 11/18/2017.
 * User manager contract
 */
public interface UserManager {

    boolean existsUser(String username);

    BigDecimal getBalance(String username);

    String getUserNameForDeviceId(String deviceId);

}
