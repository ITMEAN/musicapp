package com.minh.zingmp3.services.playlist;

import com.minh.zingmp3.exception.DataNotFoundException;
import com.minh.zingmp3.model.PlayList;
import com.minh.zingmp3.model.PlaylistSong;
import com.minh.zingmp3.model.Song;
import com.minh.zingmp3.model.User;
import com.minh.zingmp3.repositories.SongRepository;
import com.minh.zingmp3.repositories.UserRepository;
import com.minh.zingmp3.request.AddSongToPlayListRequest;
import com.minh.zingmp3.request.playlist.CreatePlayListRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayListServiceImpl implements PlayListService {
    @Autowired
    private final com.minh.zingmp3.repositories.PlayListRepository playListRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final SongRepository songRepository;

    @Override
    public PlayList createPlayList(CreatePlayListRequest playList) throws DataNotFoundException {
        User user = userRepository.findByEmail(playList.getEmail()).orElseThrow(() -> new DataNotFoundException("cannot find user"));
        PlayList save = PlayList.builder().user(user).name(playList.getName()).date(LocalDate.now()).playlistSongs(new ArrayList<>()).categoryPlaylists(new ArrayList<>()).build();
        return playListRepository.save(save);

    }

    @Override
    public List<PlayList> findAll() throws Exception {
        try {
            return playListRepository.findAll();
        } catch (Exception e) {
            throw new Exception("cannot find playlist:" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public void addSongToPlayList(AddSongToPlayListRequest request) throws Exception {
        PlayList playList = playListRepository.findById(request.getIdPlayList()).orElseThrow(() -> new DataNotFoundException("cannot find playlist with id" + request.getIdPlayList()));
        Song song = songRepository.findById(request.getIdSong()).orElseThrow(() -> new DataNotFoundException("cannot find song with id:" + request.getIdSong()));
        PlaylistSong playlistSong = PlaylistSong.builder().playlist(playList).song(song).build();
        playList.getPlaylistSongs().add(playlistSong);
        try {
            playListRepository.save(playList);
        } catch (Exception e) {
            throw new Exception("error when add song to playlist:" + e.getMessage());
        }
    }

    @Override
    public PlayList findById(Long id) throws DataNotFoundException {
        return playListRepository.findById(id).orElseThrow(() -> new DataNotFoundException("cannot find playlist with id:" + id));
    }

    @Override
    public List<PlayList> findByUserId(Long id) throws Exception {
        try {
            return playListRepository.getPlayListsByUserId(id);
        } catch (Exception e) {
            throw new Exception("error while find playlist by user id" + e.getMessage());
        }
    }


    @Override
    public List<PlayList> search(String keyword) {
        return playListRepository.findTopByPlayListNameContainingIgnoreCase(keyword, 10);
    }

    @Override
    public boolean deletePlayList(long id) {
        PlayList playList = playListRepository.findById(id).orElse(null);
        if (playList == null) return false;
        playListRepository.delete(playList);
        return true;
    }
}
