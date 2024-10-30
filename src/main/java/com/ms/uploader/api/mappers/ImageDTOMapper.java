package com.ms.uploader.api.mappers;

import com.ms.uploader.api.dtos.ImageDTO;
import com.ms.uploader.models.ImageModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ImageDTOMapper implements Function<ImageModel, ImageDTO> {

    @Override
    public ImageDTO apply( ImageModel imageModel ) {
        return new ImageDTO( imageModel.getUrl() );
    }

}
