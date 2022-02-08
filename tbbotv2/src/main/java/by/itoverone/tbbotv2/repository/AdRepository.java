package by.itoverone.tbbotv2.repository;


import by.itoverone.tbbotv2.entity.Dbup;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends CrudRepository<Dbup, String> {
    @Override
    List<Dbup> findAll();

    @Modifying
    @Query("update Dbup set brand =:brand where id =:id")
    void updateAdBrand(String id, String brand);

    @Modifying
    @Query("update Dbup set model =:model where id =:id")
    void updateAdModel(String id, String model);

    @Modifying
    @Query("update Dbup set year =:year where id =:id")
    void updateAdYear(String id, String year);

    @Modifying
    @Query("update Dbup set mileage =:mileage where id =:id")
    void updateAdMileage(String id, String mileage);

    @Modifying
    @Query("update Dbup set price =:price where id =:id")
    void updateAdPrice(String id, String price);

    @Modifying
    @Query("update Dbup set photo =:photo where id =:id")
    void updateAdPhoto(String id, byte[] photo);

    @Modifying
    @Query("update Dbup set description =:description where id =:id")
    void updateAdDescription(String id, String description);

    @Modifying
    @Query("update Dbup set telephone =:telephone where id =:id")
    void updateAdTelephone(String id, String telephone);

    @Modifying
    @Query("update Dbup set status =:status where id =:id")
    void updateAdStatus(String id, String status);

    @Query("select status from Dbup where id =:id")
    String getStatusById(String id);

    @Query("from Dbup where status =:status")
    List<Dbup> getByStatus(String status);

    @Query("from Dbup where chatId =:chatId")
    List<Dbup> getByChatId(String chatId);

}
