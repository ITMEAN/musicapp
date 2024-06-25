package com.minh.zingmp3.request.album;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddAddAlbumRequest {
    private String name;
    private MultipartFile image;
    private long idArtist;
}
