package com.ayushi.traininfo.respository;

import com.ayushi.traininfo.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train, String> {
}
