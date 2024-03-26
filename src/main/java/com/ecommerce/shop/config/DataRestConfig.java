package com.ecommerce.shop.config;

import com.ecommerce.shop.entity.Country;
import com.ecommerce.shop.entity.Product;
import com.ecommerce.shop.entity.ProductCategory;
import com.ecommerce.shop.entity.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

    private EntityManager mEntityManager;

    @Autowired
    public DataRestConfig(EntityManager entityManager){
        mEntityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
        HttpMethod[] theUnSupportedActions = {HttpMethod.POST,HttpMethod.PUT, HttpMethod.DELETE};
        disableHttpMethod(Product.class, config, theUnSupportedActions);
        disableHttpMethod(ProductCategory.class, config, theUnSupportedActions);
        disableHttpMethod(State.class, config, theUnSupportedActions);
        disableHttpMethod(Country.class, config, theUnSupportedActions);

        exposeIds(config);
    }

    private void disableHttpMethod(Class theClass,RepositoryRestConfiguration config, HttpMethod[] theUnSupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnSupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnSupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entityTypes = mEntityManager.getMetamodel().getEntities();
        List<Class> entityClasses = new ArrayList<>();
        for (EntityType tempEntityType: entityTypes) {
            entityClasses.add(tempEntityType.getJavaType());
        }
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
