package com.emazon.api_shopping_cart.infraestructure.output.repository;

import com.emazon.api_shopping_cart.infraestructure.output.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICartRepository extends JpaRepository<CartEntity, Integer> {

    @Query("SELECT c FROM CartEntity c WHERE c.idArticle = :idArticle AND c.email = :email")
    Optional<CartEntity> findCartByUserNameAndArticleId(@Param("idArticle") Integer idArticle
            ,@Param("email") String email);

    @Query("SELECT c.idArticle FROM CartEntity c WHERE c.email = :userName")
    List<Integer> findCartByUserName(@Param("userName") String userName);


}
