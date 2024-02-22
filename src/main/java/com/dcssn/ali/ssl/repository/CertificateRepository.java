package com.dcssn.ali.ssl.repository;

import com.dcssn.ali.ssl.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 证书repository
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
@Repository
public interface CertificateRepository extends JpaRepository<Certificate, String> {

    Optional<Certificate> findFirstByDomainAndStatus(String domain, String status);

    List<Certificate> findByDomain(String domain);

    List<Certificate> findByStatus(String status);

    void deleteByOrderIdNotIn(List<Long> orderIdLIst);
}
