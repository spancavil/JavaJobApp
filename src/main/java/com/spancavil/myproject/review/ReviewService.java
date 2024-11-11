package com.spancavil.myproject.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews (Long companyId);
    void addReview (Long companyId, Review review);
    Review getReviewById (Long companyId, Long reviewId);
    boolean updateReviewById (Long companyId, Long reviewId, Review review);
    boolean removeReviewById (Long companyId, Long reviewId);
}
