package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//JpaRepository oluğunu belirtiyoruz .
public interface RentalDao extends JpaRepository<Rental, Integer> {//generic kısımda id nin int olduğunu belirtiyoruz.Jpadaki generic yapı bu şekilde.
    //Boolean existsById(int rentalId);
   // List<Rental> getByCarId(int CarId);
}
