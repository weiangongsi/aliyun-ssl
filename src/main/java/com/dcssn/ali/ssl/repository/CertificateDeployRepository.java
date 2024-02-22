package com.dcssn.ali.ssl.repository;

import com.dcssn.ali.ssl.entity.CertificateDeploy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 证书部署repository
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
@Repository
public interface CertificateDeployRepository extends JpaRepository<CertificateDeploy, String> {

    List<CertificateDeploy> findByCronStatusAndNextDeployDateBeforeOrDeployDateIsNull(String cronStatus, LocalDate nextDeployDate);
}
