package com.emazon.api_shopping_cart.infraestructure.output.entity;

import com.emazon.api_shopping_cart.infraestructure.util.ConstantsOutput;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "cart")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = ConstantsOutput.EMAIL,nullable = false)
    private String email;
    @Column(name = ConstantsOutput.ID_ARTICLE,nullable = false)
    private Integer idArticle;
    @Column(name = ConstantsOutput.QUANTITY,nullable = false)
    private Integer quantity;
    @Column(name = ConstantsOutput.CREATE_DATE,nullable = false)
    private LocalDateTime createDate;
    @Column(name = ConstantsOutput.UPDATE_DATE,nullable = false)
    private LocalDateTime updateDate;
}
