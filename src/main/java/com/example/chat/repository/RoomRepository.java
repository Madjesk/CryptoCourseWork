package com.example.chat.repository;

import com.example.chat.entity.RoomData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<RoomData, Long> {
}
