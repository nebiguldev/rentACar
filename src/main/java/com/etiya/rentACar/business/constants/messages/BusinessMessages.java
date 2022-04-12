package com.etiya.rentACar.business.constants.messages;

import java.net.PortUnreachableException;

public class BusinessMessages {

    public static class MaintenanceMessages {
        public static final String CAR_UNDERMAINTENANCE = "Bu araç bakımda";
    }

    public static  class RentalMessages {
        public static final String CAR_RENTED = "Bu araç kirada";
        public static final String CAR_NOT_AVAILABLE = " Araç müsait değil";
        public static final String RENTAL_DELETED = "Kiralama kaydı silinmiştir.";
        public static final String RENTAL_UPDATE = "Kiralama kaydı güncellenmiştir";
        public static final String RENTAL_ADD  ="Kiralama başarıyla eklenmiştir";
        public static final String RENTAL_RETURNED = "Kiralanan araç başarıyla teslim alınmıştır!";
        public static final String RENTAL_NOT_EXIST = "Böyle bir id ile daha önce bir  kiralanma işlemi yoktur. Dolayısıyla teslim alınma söz konusu olamaz!";
    }

    public static class CityMessages {
        public static final String CITY_ADD = "Şehir  başarıyla eklendi.";
        public static final String CITY_DELETE="Şehir silindi.";
        public static final String CITY_UPDATE="Şehir  güncellendi.";
    }

    public static class Car {
        public static final String CAR_ADD="Araç başarıyla eklendi..";
        public static final String CAR_REMOVE="Araç Silindi..";
        public static final String CAR_UPDATE="Araç güncellemesi yapıldı..";
        public static final String CAR_STATE_UPDATED="Durum güncellemesi";
    }

    public static class ColorMessages{

        public static final String COLOR_ADD="Renk başarıyla eklendi.";
        public static final String COLOR_IS_ALREADY_EXISTS="Bu renk mevcut.";
    }
    public static class BrandMessages{
        public static final String BRAND_ADD="Marka eklendi.";
        public static final String BRAND_IS_ALREADY_EXISTS="Bu marka mevcut.";
        public static final String BRAND_DELETE="Marka silindi.";
        public static final String BRAND_UPDATE="Araç markası güncellendi.";
    }
    public static class AdditionalServiceMessage{
        public static final String ADDITIONAL_SERVICES_ADD="Ek hizmet eklendi";
        public static final String ADDITIONAL_SERVICES_DELETED="Ek hizmet silindi";
        public static final String ADDITIONAL_SERVICES_UPDATED="Ek hizmetler güncellendi";

    }
    public static class BillMessages{
        public static final String BILL_ADDED="Fatura başarıyla eklendi";
        public static final String BILL_DELETED="Fatura başarıyla silindi";
        public static final String BILL_UPDATED="Fatura başarıyla güncellendi.";
    }
    public static class PaymentMessages{
        public static final String PAYMENT_ADD = "Ödeme başarıyla eklendi";
        public static final String PAYMENT_UPDATE = "Ödeme başarıyla güncellendi";
        public static final String PAYMENT_DELETE = "Ödeme başarıyla silindi.";
        public static final String PAYMENT_LIST = "Ödemeler listelendi.";

    }






}
