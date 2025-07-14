package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.PeopleData;

@Repository
public interface PeoplesDataRepository extends JpaRepository<PeopleData, Long>{

}
