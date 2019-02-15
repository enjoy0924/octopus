package com.octopus.taxcube.eds.service;

public interface ISmsService {
    int sendSMSCode(String phoneNumber, String captcha, String templateId, String jsonParams);
}
