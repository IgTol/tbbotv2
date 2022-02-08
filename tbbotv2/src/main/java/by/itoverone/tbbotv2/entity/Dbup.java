package by.itoverone.tbbotv2.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table (name = "ads")
@NoArgsConstructor
@Getter
public class Dbup {

    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String id;
    @Setter
    private String chatId;
    @Setter
    private String brand;
    @Setter
    private String model;
    @Setter
    private String mileage;
    @Setter
    private String year;
    @Setter
    private String price;
    @Setter
    private String description;
    @Setter
    private byte[] photo;
    @Setter
    private String telephone;
    @Setter
    private String status;

//    public Dbup() {
//    }
//
//    public Dbup(String id, String chatId, String brand, String model, String mileage,
//                String year, String price, String description, byte[] photo,
//                String telephone, String status) {
//        this.id = id;
//        this.chatId = chatId;
//        this.brand = brand;
//        this.model = model;
//        this.mileage = mileage;
//        this.year = year;
//        this.price = price;
//        this.description = description;
//        this.photo = photo;
//        this.telephone = telephone;
//        this.status = status;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getChatId() {
//        return chatId;
//    }
//
//    public void setChatId(String chatId) {
//        this.chatId = chatId;
//    }
//
//    public String getBrand() {
//        return brand;
//    }
//
//    public void setBrand(String brand) {
//        this.brand = brand;
//    }
//
//    public String getModel() {
//        return model;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }
//
//    public String getMileage() {
//        return mileage;
//    }
//
//    public void setMileage(String mileage) {
//        this.mileage = mileage;
//    }
//
//    public String getYear() {
//        return year;
//    }
//
//    public void setYear(String year) {
//        this.year = year;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public byte[] getPhoto() {
//        return photo;
//    }
//
//    public void setPhoto(byte[] photo) {
//        this.photo = photo;
//    }
//
//    public String getTelephone() {
//        return telephone;
//    }
//
//    public void setTelephone(String telephone) {
//        this.telephone = telephone;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Dbup dbup = (Dbup) o;
//        return Objects.equals(id, dbup.id) && Objects.equals(chatId, dbup.chatId) && Objects.equals(brand, dbup.brand) && Objects.equals(model, dbup.model) && Objects.equals(mileage, dbup.mileage) && Objects.equals(year, dbup.year) && Objects.equals(price, dbup.price) && Objects.equals(description, dbup.description) && Arrays.equals(photo, dbup.photo) && Objects.equals(telephone, dbup.telephone) && Objects.equals(status, dbup.status);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = Objects.hash(id, chatId, brand, model, mileage, year, price, description, telephone, status);
//        result = 31 * result + Arrays.hashCode(photo);
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return "Dbup{" +
//                "id='" + id + '\'' +
//                ", chatId='" + chatId + '\'' +
//                ", brand='" + brand + '\'' +
//                ", model='" + model + '\'' +
//                ", mileage='" + mileage + '\'' +
//                ", year='" + year + '\'' +
//                ", price='" + price + '\'' +
//                ", description='" + description + '\'' +
//                ", photo=" + Arrays.toString(photo) +
//                ", telephone='" + telephone + '\'' +
//                ", status='" + status + '\'' +
//                '}';
//    }
}
