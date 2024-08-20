package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.dtos.slider.SliderBannerDto;
import az.edu.itbrains.ecommerce.models.Slider;
import az.edu.itbrains.ecommerce.repositories.SliderRepository;
import az.edu.itbrains.ecommerce.services.SliderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SliderServiceImpl implements SliderService {

    private final SliderRepository sliderRepository;
    private final ModelMapper modelMapper;

    public SliderServiceImpl(SliderRepository sliderRepository, ModelMapper modelMapper) {
        this.sliderRepository = sliderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SliderBannerDto> getSlider() {
        List<Slider> sliders = sliderRepository.findAll();
        List<SliderBannerDto> result = sliders.stream().map(slider -> modelMapper.map(slider, SliderBannerDto.class)).collect(Collectors.toList());
        return result;
    }
}
