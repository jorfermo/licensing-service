package com.jorfermo.license.controller;

import java.util.Locale;

import com.jorfermo.license.model.License;
import com.jorfermo.license.service.LicenseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/organization/{organizationId}/license")
public class LicenseController {
   @Autowired
   private LicenseService licenseService;

   @RequestMapping("/{licenseId}")
   public ResponseEntity<License> getLicense(
         @PathVariable("organizationId") String organizationId,
         @PathVariable("licenseId") String licenseId) {
      License license = licenseService.getLicense(organizationId, licenseId);
      return ResponseEntity.ok(license);
   }

   @PutMapping
   public ResponseEntity<String> updateLicense(
         @PathVariable("organizationId") String organizationId,
         @RequestBody License request) {
      return ResponseEntity.ok(licenseService.updateLicense(request, organizationId));
   }

   @PostMapping
   public ResponseEntity<String> createLicense(
         @PathVariable("organizationId") String organizationId,
         @RequestBody License request,
         @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
      return ResponseEntity.ok(licenseService.createLicense(request, organizationId, locale));
   }

   @DeleteMapping("/{licenseId}")
   public ResponseEntity<String> deleteLicense(
         @PathVariable("organizationId") String organizationId,
         @PathVariable("licenseId") String licenseId) {
      return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId));
   }
}
