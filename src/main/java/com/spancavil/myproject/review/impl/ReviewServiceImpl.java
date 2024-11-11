package com.spancavil.myproject.review.impl;

import com.spancavil.myproject.company.Company;
import com.spancavil.myproject.company.CompanyService;
import com.spancavil.myproject.review.Review;
import com.spancavil.myproject.review.ReviewRepository;
import com.spancavil.myproject.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public void addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
        }
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyIdAndId(companyId, reviewId);
        if (!reviews.isEmpty()) {
            return reviews.getFirst();
        }
        return null;
    }

    @Override
    public boolean updateReviewById(Long companyId, Long reviewId, Review updatedReview) {
        Review review = getReviewById(companyId, reviewId);
        if (review != null) {
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setTitle(updatedReview.getTitle());
            reviewRepository.save(updatedReview);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeReviewById(Long companyId, Long reviewId) {
        Review reviewToDelete = getReviewById(companyId, reviewId);
        if (reviewToDelete != null) {
            reviewRepository.delete(reviewToDelete);
            return true;
        } else {
            return false;
        }
    }
}
