package com.cloud.payment.dao;

import com.cloud.api.common.domain.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int save(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
