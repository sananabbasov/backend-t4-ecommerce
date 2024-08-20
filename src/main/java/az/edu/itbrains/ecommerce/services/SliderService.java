package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.slider.SliderBannerDto;

import java.util.List;

public interface SliderService {

    List<SliderBannerDto> getSlider();
}
