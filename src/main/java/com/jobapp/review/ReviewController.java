package com.jobapp.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        List<Review> reviews = reviewService.getAllReviews(companyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId,
                                            @RequestBody Review review){
        boolean isReviewSaved = reviewService.addReview(companyId, review);
        if(isReviewSaved){
            return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review not Saved", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId,
                                            @PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(companyId, reviewId), HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review){
        boolean isReviewUpdated = reviewService.updateReview(companyId, reviewId, review);
        if(isReviewUpdated){
            return new ResponseEntity<>("Review updated Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review Not Updated", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId){
        boolean isReviewDeleted = reviewService.deleteReview(companyId, reviewId);
        if(isReviewDeleted){
            return new ResponseEntity<>("Review deleted Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review Not Deleted", HttpStatus.NOT_FOUND);
        }
    }

}
