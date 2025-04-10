package ru.tbank.fdsspring.repository;

import org.springframework.data.repository.query.Param;
import ru.tbank.fdsspring.entity.Currency;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    @Query("SELECT c FROM Currency c WHERE c.isDeleted = false")
    List<Currency> findAllActive();

    @Modifying
    @Query("UPDATE Currency c SET c.isDeleted = true WHERE c.id = :id")
    void softDeleteById(@Param("id") Long id);

    @Query("SELECT c FROM Currency c WHERE c.id = :id AND c.isDeleted = false")
    Optional<Currency> findActiveById(@Param("id") Long id);
}