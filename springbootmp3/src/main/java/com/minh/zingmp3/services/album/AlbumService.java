package com.minh.zingmp3.services.album;

import com.minh.zingmp3.model.Album;
import com.minh.zingmp3.request.album.AddAddAlbumRequest;
import com.minh.zingmp3.request.album.AddArtistToAlbumRequest;
import com.minh.zingmp3.response.ListAlbumResponse;

import java.util.List;

public interface AlbumService {
    Album addAlbum(AddAddAlbumRequest request) throws  Exception;
    Album addArtistToAlbum(AddArtistToAlbumRequest request) throws Exception;
    Album findAlbumById(long id) throws Exception;
    ListAlbumResponse findAllAlbums(int size,int page) throws Exception;
    List<Album> findTopAlbums(int limit) throws Exception;
    List<Album> getListAlbumByArtistId(long id) throws Exception;
    List<Album> search (String keyword);
    boolean deleteAlbum(long id) throws Exception;
    Album getAlbumNewestByArtistId(long id) throws Exception;

}
