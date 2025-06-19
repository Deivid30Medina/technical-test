package com.zemsania.technical_test.repository;

import com.zemsania.technical_test.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LoginLogRepository extends JpaRepository<LoginLog, UUID> {
}
