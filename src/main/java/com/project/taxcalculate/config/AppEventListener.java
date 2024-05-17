package com.project.taxcalculate.config;

import com.project.taxcalculate.service.TaxRateMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppEventListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private TaxRateMasterService taxRateMasterService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        taxRateMasterService.insertMasterData();
    }
}
