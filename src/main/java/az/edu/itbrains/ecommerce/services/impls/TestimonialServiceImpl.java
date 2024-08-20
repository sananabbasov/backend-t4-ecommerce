package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.dtos.testimonial.TestimonialDto;
import az.edu.itbrains.ecommerce.models.Testimonial;
import az.edu.itbrains.ecommerce.repositories.TestimonialRepository;
import az.edu.itbrains.ecommerce.services.TestimonialService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepository testimonialRepository;
    private final ModelMapper modelMapper;

    public TestimonialServiceImpl(TestimonialRepository testimonialRepository, ModelMapper modelMapper) {
        this.testimonialRepository = testimonialRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<TestimonialDto> getTestimonials() {

        List<Testimonial> testimonials = testimonialRepository.findAll();
        List<TestimonialDto> result = testimonials.stream().map(t -> modelMapper.map(t, TestimonialDto.class)).collect(Collectors.toList());
        return result;
    }
}
