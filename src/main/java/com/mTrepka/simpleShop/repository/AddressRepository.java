package com.mTrepka.simpleShop.repository;

import com.mTrepka.simpleShop.domain.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Adress,Integer> {
}
