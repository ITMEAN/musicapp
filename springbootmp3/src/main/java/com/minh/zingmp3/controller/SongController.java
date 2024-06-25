package com.minh.zingmp3.controller;

import com.minh.zingmp3.enums.Country;
import com.minh.zingmp3.model.Album;
import com.minh.zingmp3.model.Song;
import com.minh.zingmp3.request.song.CreateSongRequest;
import com.minh.zingmp3.response.GetAllSongResponse;
import com.minh.zingmp3.services.song.SongService;
import com.minh.zingmp3.services.upload.FileUpload;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/v1/songs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll() throws Exception {
        return new ResponseEntity<>(songService.findAllSong(), HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<?> getSongsPaging(@RequestParam("size") Optional<Integer> size, @RequestParam("page") Optional<Integer> page) throws Exception {
        Page<Song> songPage = songService.findAllSong(page.orElse(1), size.orElse(10), "id", "asc");
        int totalPages = songPage.getTotalPages();
        List<Integer> pageNumbers = new ArrayList<>();
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
        }
        return ResponseEntity.ok(new GetAllSongResponse(songPage, totalPages, pageNumbers));

    }

    @GetMapping
    public ResponseEntity<?> findAllSong() throws Exception {
        return new ResponseEntity<>(songService.findTopSong(), HttpStatus.OK);
    }

    @GetMapping("/{country}")
    public ResponseEntity<?> findSongByArtistCountry(@PathVariable("country") String country) throws Exception {
        return new ResponseEntity<>(songService.getSongByArtistCountry(country), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> addSong(@ModelAttribute CreateSongRequest request) throws Exception {
        return new ResponseEntity<>(songService.addSong(request), HttpStatus.OK);
    }


    @GetMapping("/album/{id}")
    public ResponseEntity<?> findSongByAlbumId(@PathVariable("id") long id) throws Exception {
        return new ResponseEntity<>(songService.getSongsByAlbum_Id(id),HttpStatus.OK);
    }

    @GetMapping("/artist/{id}/new")
    public ResponseEntity<?> getTopSongNew(@PathVariable("id") long id) throws Exception {
        return ResponseEntity.ok(songService.getListSongNewByArtistId(id));
    }

}
