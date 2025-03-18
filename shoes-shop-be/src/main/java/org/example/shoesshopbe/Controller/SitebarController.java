package org.example.shoesshopbe.Controller;

import org.example.shoesshopbe.Model.Brands;
import org.example.shoesshopbe.Model.Collections;
import org.example.shoesshopbe.Model.Colors;
import org.example.shoesshopbe.Model.Products;
import org.example.shoesshopbe.Repo.BrandRepo;
import org.example.shoesshopbe.Repo.CollectionRepo;
import org.example.shoesshopbe.Repo.ColorRepo;
import org.example.shoesshopbe.Service.ProductService;
import org.example.shoesshopbe.Service.SitebarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sitebar")
public class SitebarController {
    @Autowired
    SitebarService sitebarService;
    @Autowired
    BrandRepo brandRepo;

    @Autowired
    CollectionRepo collectionRepo;

    @Autowired
    ColorRepo colorRepo;
    @GetMapping("/brand")
    public ResponseEntity<?> findAllBrand() {
        List<Brands> brands = brandRepo.findAll();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/collection")
    public ResponseEntity<?> findAllCollection() {
        List<Collections> collection = collectionRepo.findAll();
        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @GetMapping("/color")
    public ResponseEntity<?> findAllColor() {
        List<Colors> colors = colorRepo.findAll();
        return new ResponseEntity<>(colors, HttpStatus.OK);
    }


}
