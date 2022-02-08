package com.jorfermo.license.repository;

import java.util.List;

import com.jorfermo.license.model.License;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends CrudRepository<License, String> {

   public List<License> findByOrganizationId(String organizationId);

   public License findByOrganizationIdAndLicenseId(String organizationId, String licenseId);

}