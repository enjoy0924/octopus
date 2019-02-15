package com.octopus.taxcube.eds.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ApplyOfferPlus extends ApplyOffer {
    private Map<Long, String> offers = new HashMap<>();
    private List<String> idcardImages = new ArrayList<>();
    private List<String> certificateImages = new ArrayList<>();
    private List<String> locations;

    public void putOffer(Long id, String name){
        offers.put(id, name);
    }

    public void addIdcardImage(String image) {
        idcardImages.add(image);
    }

    public void addCertificateImage(String image) {
        certificateImages.add(image);
    }
}
