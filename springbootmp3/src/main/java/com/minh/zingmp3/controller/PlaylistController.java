package com.minh.zingmp3.controller;

import com.minh.zingmp3.exception.DataNotFoundException;
import com.minh.zingmp3.model.PlayList;
import com.minh.zingmp3.request.AddSongToPlayListRequest;
import com.minh.zingmp3.request.playlist.CreatePlayListRequest;
import com.minh.zingmp3.services.playlist.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/playlists")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlaylistController {
    @Autowired
    private PlayListService playlistService;

    @GetMapping
    public ResponseEntity<?> findAll() throws Exception {
        return ResponseEntity.ok(playlistService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreatePlayListRequest playListRequest) throws DataNotFoundException {
        return ResponseEntity.ok(playlistService.createPlayList(playListRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws DataNotFoundException {
        return ResponseEntity.ok(playlistService.findById(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable long id) throws Exception {
        return ResponseEntity.ok(playlistService.findByUserId(id));}

    @PostMapping("/add-song")
    public ResponseEntity<?> addSongToPlayList(@RequestBody AddSongToPlayListRequest requestAddSongToPlayList) throws DataNotFoundException {
        return ResponseEntity.ok("success!!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlayList(@PathVariable long id) {
        return ResponseEntity.ok(playlistService.deletePlayList(id));
    }


}
