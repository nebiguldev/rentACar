package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.OrderedAdditionalService;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedAdditionalServiceDao extends JpaRepository<OrderedAdditionalService,Integer> {

}
