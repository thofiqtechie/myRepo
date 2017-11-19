package com.wirecard.smshandler.vo;

import java.io.Serializable;

/**
 * Created by thofi_000 on 11/18/2017.
 * SMS Handler Response object to return the result to respective calling system
 */
public class SMSHandlerResponse implements Serializable{

    private static final long serialVersionUID = 2L;

    private String smsContent;
    private String senderDeviceId;
    private String responseMessage;
    private String errorCode;
    private String errorMessage;
    private String statusCode;

    /**
     * No arg constructor for response posting if required
     */
    public SMSHandlerResponse(){

    }

    /**
     * Constructor to create response message and its required parameters
     * @param smsContent
     * @param senderDeviceId
     * @param responseMessage
     */
    public SMSHandlerResponse(String smsContent, String senderDeviceId, String responseMessage, String statusCode){
        this.smsContent = smsContent;
        this.senderDeviceId = senderDeviceId;
        this.responseMessage = responseMessage;
        this.statusCode = statusCode;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public String getSenderDeviceId() {
        return senderDeviceId;
    }

    public void setSenderDeviceId(String senderDeviceId) {
        this.senderDeviceId = senderDeviceId;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public SMSHandlerResponse withSenderDeviceId(String senderDeviceId){
        this.senderDeviceId = senderDeviceId;
        return this;
    }

    public SMSHandlerResponse withSMSContent(String smsContent){
        this.smsContent = smsContent;
        return this;
    }

    public SMSHandlerResponse withErrorCode(String errorCode){
        this.errorCode = errorCode;
        return this;
    }

    public SMSHandlerResponse withErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
        return this;
    }

    @Override
    public String toString() {
        return "SMSHandlerResponse{" +
                "smsContent='" + smsContent + '\'' +
                ", senderDeviceId='" + senderDeviceId + '\'' +
                ", responseMessage='" + responseMessage + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
