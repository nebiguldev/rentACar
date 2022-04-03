package com.etiya.rentACar.entities;
/*Entity katmanı:Bu katman veri tabanı nesnelerinin tutulduğu katmandır.
 Yani kısaca veri tabanında bulunan tabloların yazılım dilinde
 karşılığının tutulduğu katmandır. Veri tabanında tablo bu katmanda
 class a eşit gelmektedir.*/
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data//getter setter
@AllArgsConstructor//parametreli
@NoArgsConstructor//parametresz
@Entity//sen bir entitysin
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//birer birer artan id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "brand")//bir markanın bir çok arabası olur
    private List<Car> cars;

}
