package com.minh.zingmp3.services.song;

import com.minh.zingmp3.enums.Country;
import com.minh.zingmp3.model.Song;
import com.minh.zingmp3.request.song.CreateSongRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SongService {
    Page<Song> findAllSong(int pageNo, int pageSize, String sortBy, String sortDirection) throws Exception;
    Song findSongById(Long id) throws Exception;
    List<Song> findAllSong() throws Exception;
    List<Song> findTopSong() throws Exception;
    Song addSong(CreateSongRequest request) throws Exception;
    List<Song> search(String keyword) throws Exception;
    List<Song> getListSongNewByArtistId(long artistId) throws Exception;
    List<Song> getSongsByAlbum_Id(long id) throws Exception;
    List<Song> getSongByArtistCountry(String country) throws Exception;

}
