package com.emazon.api_shopping_cart.infraestructure.output.repository;

import com.emazon.api_shopping_cart.infraestructure.output.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ICartRepository extends JpaRepository<CartEntity, Integer> {

    @Transactional
    @Query("SELECT c FROM CartEntity c WHERE c.idArticle = :idArticle AND c.email = :email")
    Optional<CartEntity> findCartByUserNameAndArticleId(@Param("idArticle") Integer idArticle
            ,@Param("email") String email);

    @Transactional
    @Query("SELECT c.idArticle FROM CartEntity c WHERE c.email = :userName")
    List<Integer> findCartByUserName(@Param("userName") String userName);

    @Modifying
    @Transactional
    @Query("DELETE FROM CartEntity c WHERE c.idArticle = :idArticle AND c.email = :email")
    void deleteByIdArticle(@Param("idArticle")Integer idArticle, @Param("email")String email);

    @Modifying
    @Transactional
    @Query("UPDATE CartEntity p SET p.updateDate = :updateDate WHERE p.email = :email")
    void updateProductDateByEmail(@Param("email")String email,@Param("updateDate")LocalDateTime updateDate);
}
