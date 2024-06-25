package com.minh.zingmp3.controller;

import com.minh.zingmp3.request.album.AddAddAlbumRequest;
import com.minh.zingmp3.services.album.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/albums")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @PostMapping
    public ResponseEntity<?> addAlbum(@ModelAttribute AddAddAlbumRequest request) throws Exception {
       return new ResponseEntity<>(albumService.addAlbum(request), HttpStatus.OK);
    }

    @GetMapping("/new-album")
    public ResponseEntity<?> getAllNewAlbum() throws Exception {
        return new ResponseEntity<>(albumService.findTopAlbums(5),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAlbumById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(albumService.findAlbumById(id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlbum(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(albumService.deleteAlbum(id),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> findAllAlbum(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) throws Exception {
        int pageResult = page.orElse(1);
        int sizeResult = size.orElse(10);
        return new ResponseEntity<>(albumService.findAllAlbums(sizeResult, pageResult),HttpStatus.OK);
    }
    @GetMapping("/artist/{id}")
    public ResponseEntity<?> getListAlbumByArtistId(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(albumService.getListAlbumByArtistId(id),HttpStatus.OK);
    }
    @GetMapping("/artist/{id}/new")
    public ResponseEntity<?> getTopAlbumNew(@PathVariable("id") long id) throws Exception {
        return new ResponseEntity<>(albumService.getAlbumNewestByArtistId(id),HttpStatus.OK);
    }

}
