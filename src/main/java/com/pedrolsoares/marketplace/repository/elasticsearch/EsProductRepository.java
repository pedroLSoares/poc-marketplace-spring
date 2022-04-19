package com.pedrolsoares.marketplace.repository.elasticsearch;

import com.pedrolsoares.marketplace.model.ESProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EsProductRepository extends ElasticsearchRepository<ESProduct, String> {

    List<ESProduct> findAllByName(String name);
}
