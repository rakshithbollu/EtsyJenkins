package com.example.EtsyProject.EtsyProject.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;


@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteId implements Serializable {
        private String email;
        private Integer productId;

        // default constructor, getters, and setters

        // Override equals and hashCode methods
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FavoriteId)) return false;
            FavoriteId that = (FavoriteId) o;
            return Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getProductId(), that.getProductId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getEmail(), getProductId());
        }

}
