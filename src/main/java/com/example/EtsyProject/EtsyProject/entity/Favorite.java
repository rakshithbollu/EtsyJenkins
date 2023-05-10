package com.example.EtsyProject.EtsyProject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favorites")
@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(FavoriteId.class)
public class Favorite {

    @Id
    @Column(name="email")
    private String email;

    @Id
    @Column(name="productid")
    private Integer productId;
}
