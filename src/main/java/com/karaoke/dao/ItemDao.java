package com.karaoke.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.karaoke.model.Item;

@Repository
public interface ItemDao extends JpaRepository<Item, Long>{
	
	Item findItemByName(String name);

	@Query("SELECT i FROM Item i WHERE i.id <> :id AND i.name =:name")
	List<Item> findItemDuplicate(@Param("id") Long id ,@Param("name") String name);
	
}
