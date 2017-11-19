package com.wirecard.smshandler.services.external.impl;

import com.wirecard.smshandler.services.external.UserManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by thofi_000 on 11/18/2017.
 * Dummy user manager implementation to test sms handler
 */
@Service
@Transactional
public class UserManagerImpl implements UserManager {

    private static Logger log = Logger.getLogger(UserManagerImpl.class);

    //Valid sending and receiver user id
    private HashMap<String, String> users = new HashMap<>();

    @PostConstruct
    public void constuctUses() {
        users.put("UNIQUEID1", "FFRITZ");
        users.put("UNIQUEID2", "FFRITZ");
        users.put("UNIQUEID3", "MSMITH");
        users.put("UNIQUEID4", "TESTUSER");
    }

    @Override
    public boolean existsUser(String username) {
        return users.containsValue(username);
    }

    @Override
    public BigDecimal getBalance(String username) {
        return new BigDecimal("1500");
    }

    @Override
    public String getUserNameForDeviceId(String deviceId) {
        return users.get(deviceId);
    }
}
