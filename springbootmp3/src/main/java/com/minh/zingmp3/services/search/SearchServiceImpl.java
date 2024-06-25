package com.minh.zingmp3.services.search;

import com.minh.zingmp3.response.SearchRespone;
import com.minh.zingmp3.services.album.AlbumService;
import com.minh.zingmp3.services.artist.ArtistService;
import com.minh.zingmp3.services.playlist.PlayListService;
import com.minh.zingmp3.services.song.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    @Autowired
    private final SongService songService;
    @Autowired
    private final AlbumService albumService;
    @Autowired
    private final ArtistService  artistService;
    @Autowired
    private final PlayListService playListService;
    @Override
    public SearchRespone search(String keyword) throws Exception {
        SearchRespone searchRespone = new SearchRespone();
        searchRespone.setSongs(songService.search(keyword));
        searchRespone.setAlbums(albumService.search(keyword));
        searchRespone.setArtists(artistService.search(keyword));
        searchRespone.setPlayLists(playListService.search(keyword));
        return searchRespone;
    }
}
