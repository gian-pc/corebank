package com.corebank.mscore.repository;

import com.corebank.mscore.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
