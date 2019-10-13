package com.karaoke.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karaoke.model.Vip;

@Repository
public interface VipDao extends JpaRepository<Vip, Long>{

}
