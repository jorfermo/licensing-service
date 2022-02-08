package com.jorfermo.license.service;

import java.util.UUID;

import com.jorfermo.license.config.ServiceConfig;
import com.jorfermo.license.model.License;
import com.jorfermo.license.repository.LicenseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {
   @Autowired
   private MessageSource messages;

   @Autowired
   private LicenseRepository licenseRepository;

   @Autowired
   private ServiceConfig config;

   public License getLicense(String licenseId, String organizationId) {
      License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
      if (license == null) {
         throw new IllegalArgumentException(
               String.format(messages.getMessage(
                     "license.search.error.message", null, null),
                     licenseId, organizationId));
      }
      return license;
   }

   public License createLicense(License license) {
      license.setLicenseId(UUID.randomUUID().toString());
      licenseRepository.save(license);
      return license.withComment(config.getProperty());
   }

   public License updateLicense(License license) {
      licenseRepository.save(license);
      return license.withComment(config.getProperty());
   }

   public String deleteLicense(String licenseId) {
      String responseMessage = null;
      License license = new License();
      license.setLicenseId(licenseId);
      licenseRepository.delete(license);
      responseMessage = String.format(messages.getMessage(
            "license.delete.message", null, null), licenseId);
      return responseMessage;
   }
}
