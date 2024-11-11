package com.spancavil.myproject.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    //Este método genera la implementación automáticamente en tiempo de ejecución.
    //Lo que hará es encontrar las reviews que tengan el company Id correspondiente.
    //SQL = select * from review where company_id = ?
    List<Review> findByCompanyId(Long companyId);
    List<Review> findByCompanyIdAndId(Long companyId, Long reviewId);
}
