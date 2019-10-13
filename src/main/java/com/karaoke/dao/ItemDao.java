package com.karaoke.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karaoke.model.Item;

@Repository
public interface ItemDao extends JpaRepository<Item, Long>{

}
