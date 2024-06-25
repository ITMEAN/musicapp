package com.minh.zingmp3.controller;

import com.minh.zingmp3.request.artist.AddArtistRequest;
import com.minh.zingmp3.services.upload.FileUpload;
import com.minh.zingmp3.services.artist.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/artists")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArtistController {
    @Autowired
    private ArtistService artistService;
    @Autowired
    private  FileUpload fileUpload;
    @GetMapping("/top")
    public ResponseEntity<?> getTopArtist() throws Exception {
        return ResponseEntity.ok(artistService.getTopArtist(4));
    }
    @PostMapping
    private ResponseEntity<?> addArtist(@ModelAttribute AddArtistRequest request) throws Exception {
        return new ResponseEntity<>(artistService.addArtist(request), HttpStatus.OK);
    }
    @GetMapping
    private ResponseEntity<?> findAllArtist(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) throws Exception {
        int resultPage = page.orElse(1);
        int resultSize = size.orElse(10);
        return new ResponseEntity<>(artistService.findAllArtists(resultSize,resultPage), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    private ResponseEntity<?> findArtistById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(artistService.getArtistById(id),HttpStatus.OK);
    }

}
