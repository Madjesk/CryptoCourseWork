package com.example.chat.repository;

import com.example.chat.entity.CipherData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CipherRepository extends CrudRepository<CipherData, Long> {
}
