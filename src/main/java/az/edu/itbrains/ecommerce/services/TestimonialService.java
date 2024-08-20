package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.testimonial.TestimonialDto;

import java.util.List;

public interface TestimonialService {
    List<TestimonialDto> getTestimonials();
}
