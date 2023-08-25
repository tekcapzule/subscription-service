package com.tekcapsule.subscription.domain.repository;

import com.tekcapsule.core.domain.CrudRepository;
import com.tekcapsule.subscription.domain.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, String> {
}
