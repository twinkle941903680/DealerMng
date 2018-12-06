package com.bababadboy.dealermng.controller;

import com.alibaba.fastjson.JSON;
import com.bababadboy.dealermng.entity.ProductSaleInfo;
import com.bababadboy.dealermng.repository.ProductSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Product Sale Info Controller
 *
 * @author TwinkleN1
 */

@RestController
public class ProductSaleController {
    @Autowired
    private ProductSaleRepository productSaleRepository;

    @GetMapping(value="/productSales")
    public Object all()
    {
       // System.out.println("get all");
        List<ProductSaleInfo> psList = productSaleRepository.findAll();
       // System.out.println(JSON.toJSON(psList));
        return psList;
    }

    @GetMapping(value = "/productSales/{id}")
    public Object getSaleInfoById(@PathVariable("id") Long id){
        //System.out.println("get id");
        ProductSaleInfo psi= productSaleRepository.findProductSaleInfoById(id);
       // ProductSaleInfo psi= productSaleRepository.findById(id);
        return psi;
    }

    @RequestMapping(value = "/productSales/{id}",method = RequestMethod.DELETE)
    public void deleteSaleInfo(@PathVariable("id") Long id){
        productSaleRepository.deleteById(id);
    }

    @RequestMapping(value = "/productSales",method = RequestMethod.POST)
    public ResponseEntity<ProductSaleInfo> createSaleInfo(@RequestBody ProductSaleInfo psiBody){
        ProductSaleInfo savePS = productSaleRepository.save(psiBody);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savePS.getId()).toUri();
    return ResponseEntity.created(location).build();
    }


    @PutMapping("/productSales/{id}")
    public ResponseEntity<Object> updateProductSaleInfo(@RequestBody ProductSaleInfo productSaleInfo,@PathVariable Long id)
    {
        ProductSaleInfo psi = productSaleRepository.findProductSaleInfoById(id);
        productSaleRepository.save(productSaleInfo);

        return ResponseEntity.noContent().build();
    }
}
