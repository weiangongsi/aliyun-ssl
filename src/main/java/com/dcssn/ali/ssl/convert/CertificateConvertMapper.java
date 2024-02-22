package com.dcssn.ali.ssl.convert;

import com.dcssn.ali.ssl.entity.Certificate;
import com.dcssn.ali.ssl.entity.CertificateDeploy;
import com.dcssn.ali.ssl.vo.certificate.vo.CertificateVo;
import org.mapstruct.Mapper;

/**
 * <p>
 * 对象转换
 * <p>
 *
 * @author lhy
 * @since 2024-02-21
 */
@Mapper(componentModel = "spring")
public interface CertificateConvertMapper {

    CertificateVo certificateToCertificateVo(Certificate certificate);

}
