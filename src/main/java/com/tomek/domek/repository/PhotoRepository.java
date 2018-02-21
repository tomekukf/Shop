package com.tomek.domek.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tomek.domek.model.Photo;

public interface PhotoRepository  extends JpaRepository<Photo, Long>{
	
	Optional<Photo> findById(Long id);
	Photo findByPhotoKey(String id);

}
