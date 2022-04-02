package com.etiya.rentACar.dataAccess.abstracts;
/*Dataaccess:Bu katman veri tabanından veriye erişmek için yazılacak olan kodların bulunduğu katmandır. */
import com.etiya.rentACar.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandDao extends JpaRepository<Brand, Integer> {
    Boolean existsBrandByNameIgnoreCase(String name);
}
