package com.wirecard.smshandler.services.delegate;

import com.wirecard.smshandler.constants.SMSResponseEnum;
import com.wirecard.smshandler.constants.SMSServicesEnum;
import com.wirecard.smshandler.exception.ProcessingErrorException;
import com.wirecard.smshandler.services.SMSHandler;
import com.wirecard.smshandler.constants.SMSHandlerConstants;
import com.wirecard.smshandler.util.SMSHandlerUtil;
import com.wirecard.smshandler.vo.SMSHandlerRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by thofi_000 on 11/18/2017.
 * This class will verify the user based on device id and requested service
 */
@Service
public class SMSHandlerDelegate {

    private static Logger log = Logger.getLogger(SMSHandlerDelegate.class);

    @Autowired
    private SMSHandlerUtil smsHandlerUtil;

    @Autowired
    @Qualifier(value = "balanceSMSHandler")
    private SMSHandler balanceSMSHandler;

    @Autowired
    @Qualifier(value = "sendSMSHandler")
    private SMSHandler sendSMSHandler;

    @Autowired
    @Qualifier(value = "totalSMSHandler")
    private SMSHandler totalSMSHandler;


    /**
     * Delegate the service to respective service
     * @param smsHandlerRequest
     * @return
     */
    public String delegateService (SMSHandlerRequest smsHandlerRequest) throws ProcessingErrorException {

        log.info("SMSHandlerDelegate :: delegateService Entry");

        try{

            //Get the service id from the property file
            String serviceId = this.getServiceId(smsHandlerRequest);
            log.info("service id  = "+serviceId);

            String smsContent = smsHandlerRequest.getSmsContent();
            String senderDeviceId = smsHandlerRequest.getSenderDeviceId();

            if(serviceId != null){

                String result = null;
                serviceId = serviceId.trim();

                //Process 1:
                // Get the class name from property file and process the sms easy to configure but creating new instance every time
                /*String className = smsHandlerUtil.getServiceClassFromServiceClassProperty(serviceId);
                log.info("Class name for service : "+serviceId +" Class : "+className);
                Class<?> aClass = Class.forName(className);
                Object serviceClassInstance = aClass.newInstance();
                Method handleSmsRequestMethod = aClass.getMethod("handleSmsRequest", String.class, String.class);
                handleSmsRequestMethod.setAccessible(true);
                Object result = handleSmsRequestMethod.invoke(serviceClassInstance, new String(smsHandlerRequest.getSmsContent()),
                        new String(smsHandlerRequest.getSenderDeviceId()));
                return new SMSHandlerResponse(smsHandlerRequest.getSmsContent(),smsHandlerRequest.getSenderDeviceId(),
                        (String)result, SMSHandlerConstants.SUCCESS_CODE);*/

                //Process 2
                if(serviceId.equals(SMSServicesEnum.BALANCE_SERVICE.getServiceId())){
                    result = balanceSMSHandler.handleSmsRequest(smsContent, senderDeviceId);
                }else if(serviceId.equals(SMSServicesEnum.SEND_SERVICE.getServiceId())){
                    result = sendSMSHandler.handleSmsRequest(smsContent, senderDeviceId);
                }else if(serviceId.equals(SMSServicesEnum.TOTAL_SERVICE.getServiceId())){
                    result = totalSMSHandler.handleSmsRequest(smsContent, senderDeviceId);
                }

                log.info("SMSHandlerDelegate :: delegateService Result "+result);

                return result;

            }else{
                return smsHandlerUtil.getValueFromMessageProperty(SMSResponseEnum.ERR_UNKNOWN_COMMAND.name());
            }

        }catch (Exception exception){
            log.error(exception);
            throw new ProcessingErrorException(smsHandlerUtil.getValueFromMessageProperty("ERR_SMS_DELEGATE"));
        }
    }


    /**
     * Get the service id to route
     * @param smsHandlerRequest
     * @return
     * @throws ProcessingErrorException
     */
    private String getServiceId(SMSHandlerRequest smsHandlerRequest) throws ProcessingErrorException{

        log.info("SMSHandlerDelegate :: getServiceId Entry");

        try {
            String smsContent = smsHandlerRequest.getSmsContent().trim();
            String tokenizer = smsHandlerUtil.getValueFromMessageProperty(SMSHandlerConstants.SMS_COMMAND_TOKENIZER);
            String[] tokens = smsHandlerUtil.splitStringUsingDelimeter(smsContent, tokenizer);

            //Based on the sms command if service not available
            if(tokens == null || tokens.length == 0){
                return null;
            }

            //First token will be the command
            StringBuffer serviceCommand = new StringBuffer(tokens[0].trim());

            if(serviceCommand != null && serviceCommand.toString().equals("TOTAL")){
                serviceCommand.append("_").append(tokens[1].trim());
            }

            log.info("SMSHandlerDelegate :: getServiceId serviceCommand : "+serviceCommand.toString());
            log.info("SMSHandlerDelegate :: getServiceId Exit");

            return smsHandlerUtil.getServiceIdFromServiceProperty(serviceCommand.toString());

        }catch (Exception exception){
            log.error(exception);
            throw new ProcessingErrorException(smsHandlerUtil.getValueFromMessageProperty("ERR_SMS_DELEGATE"));
        }
    }
}
