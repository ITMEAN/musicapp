package com.minh.zingmp3.services.artist;

import com.minh.zingmp3.model.Artist;
import com.minh.zingmp3.request.artist.AddArtistRequest;
import com.minh.zingmp3.response.ListArtistResponse;

import java.util.List;

public interface ArtistService {
    Artist addArtist(AddArtistRequest request) throws Exception;
    Artist getArtistById(long id) throws Exception;
    ListArtistResponse findAllArtists(int size,int page) throws Exception;
    List<Artist> search (String keyword) throws Exception;
    List<Artist> getTopArtist(int limit) throws Exception;
}
