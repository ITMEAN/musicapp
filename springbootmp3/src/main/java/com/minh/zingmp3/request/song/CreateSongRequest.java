package com.minh.zingmp3.request.song;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSongRequest {
    private String name;
    private MultipartFile img;
    private MultipartFile mp3;
    private LocalDate releaseDate;
    private long idAlbum;
    private List<Long> idArtists;
}
