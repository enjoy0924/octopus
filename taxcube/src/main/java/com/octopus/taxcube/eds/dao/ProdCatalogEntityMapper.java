package com.octopus.taxcube.eds.dao;

import com.octopus.taxcube.eds.entity.ProdCatalogEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdCatalogEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table prod_catalog
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table prod_catalog
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    int insert(ProdCatalogEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table prod_catalog
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    int insertSelective(ProdCatalogEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table prod_catalog
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    ProdCatalogEntity selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table prod_catalog
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    int updateByPrimaryKeySelective(ProdCatalogEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table prod_catalog
     *
     * @mbg.generated Fri Jan 11 13:34:16 CST 2019
     */
    int updateByPrimaryKey(ProdCatalogEntity record);

    List<ProdCatalogEntity> selectAll();
}