package com.bestinsurance.api.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sample REST Controller that declares a service that returns the value of the property api.version
 */
@RestController
@RequestMapping("/status")
public class ApplicationStatusController {
    
    private String apiVersion;

    @GetMapping
    public Status getStatus() {
        return new Status("v1", "running");
    }

    class Status {
        private String apiVersion;
        private String status;

        public Status(String apiVersion, String status) {
            this.apiVersion = apiVersion;
            this.status = status;
        }

        public String getApiVersion() {
            return apiVersion;
        }

        public String getStatus() {
            return status;
        }
    }
}