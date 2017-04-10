package com.test.repository;


import com.test.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

    @Query("update DeviceEntity entity set entity.online = true where entity.address like ?1")
    @Transactional
    @Modifying
    void updateOnlineStatus(String address);

    @Query("update DeviceEntity entity set entity.online = false where entity.address like ?1")
    @Transactional
    @Modifying
    void updateOfflineStatus(String address);


}
