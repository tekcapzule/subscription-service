package com.tekcapzule.subscription.domain.repository;

import com.tekcapzule.core.domain.CrudRepository;
import com.tekcapzule.subscription.domain.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, String> {
}
