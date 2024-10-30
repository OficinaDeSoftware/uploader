package com.ms.uploader.api.controllers;

import com.ms.uploader.api.dtos.ImageDTO;
import com.ms.uploader.infrastructure.RestErrorMessage;
import com.ms.uploader.services.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping( value = "/image", produces = {"application/json"} )
public class ImageController {

    private final ImageService imageService;

    public ImageController( ImageService imageService ) {
        this.imageService = imageService;
    }

    @Operation( description = "Upload image on firebase storage")

    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Return the URL of storage image",
                content = { @Content( schema = @Schema( implementation = ImageDTO.class ) ) }),
        @ApiResponse(
                responseCode = "404",
                description = "Crendentials firebase not founded",
                content = { @Content( schema = @Schema( implementation = RestErrorMessage.class ) ) }),
        @ApiResponse(
                responseCode = "400",
                description = "File name is empty",
                content = { @Content( schema = @Schema( implementation = RestErrorMessage.class ) ) })
    })

    @RequestBody (
        content = @Content( mediaType = "multipart/form-data",
        schema = @Schema( type = "object", implementation = MultipartFile.class ) )
    )

    @PostMapping
    public ImageDTO upload( @RequestParam("file") MultipartFile multipartFile ) {
        return imageService.upload(multipartFile);
    }
}