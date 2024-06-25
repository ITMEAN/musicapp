package com.minh.zingmp3.services.playlist;

import com.minh.zingmp3.exception.DataNotFoundException;
import com.minh.zingmp3.model.PlayList;
import com.minh.zingmp3.request.AddSongToPlayListRequest;

import com.minh.zingmp3.request.playlist.CreatePlayListRequest;

import java.util.List;
import java.util.Optional;

public interface PlayListService {
    PlayList createPlayList(CreatePlayListRequest playList) throws DataNotFoundException;

    List<PlayList> findAll() throws Exception;

    void addSongToPlayList(AddSongToPlayListRequest requestAddSongToPlayList) throws Exception;

    PlayList findById(Long id) throws DataNotFoundException;

    List<PlayList> findByUserId(Long id) throws Exception;

    List<PlayList> search(String keyword);

    public boolean deletePlayList(long id);
}
