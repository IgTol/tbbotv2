package by.itoverone.tbbotv2.dao;

import by.itoverone.tbbotv2.entity.Dbup;
import by.itoverone.tbbotv2.repository.AdRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {
    private final AdRepository adRepository;

    public DBService(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    public void saveAd(Dbup dbup) {
        adRepository.save(dbup);
    }

    public List<Dbup> readAll() {
        return adRepository.findAll();
    }

    public String getStatusById(String id) {
        return adRepository.getStatusById(id);
    }

    public void updateBrand(String id, String brand) {
        adRepository.updateAdBrand(id, brand);
    }

    public void updateModel(String id, String model) {
        adRepository.updateAdModel(id, model);
    }

    public void updateYear(String id, String year) {
        adRepository.updateAdYear(id, year);
    }

    public void updateMileage(String id, String mileage) {
        adRepository.updateAdMileage(id, mileage);
    }

    public void updatePrice(String id, String price) {
        adRepository.updateAdPrice(id, price);
    }

    public void updateDescription(String id, String description) {
        adRepository.updateAdDescription(id, description);
    }

    public void updateTelephone(String id, String telephone) {
        adRepository.updateAdTelephone(id, telephone);
    }

    public String updateStatus(String id, String status) {
        adRepository.updateAdStatus(id, status);
        return "";
    }

    public void deleteAd(String id) {
        adRepository.deleteById(id);
    }

    public List<Dbup> getByStatus(String status) {
        return adRepository.getByStatus(status);
    }

    public List<Dbup> findByChatId(String chatId) {
        return adRepository.getByChatId(chatId);
    }

}
