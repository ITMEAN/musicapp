package com.minh.zingmp3.services.album;

import com.minh.zingmp3.exception.DataNotFoundException;
import com.minh.zingmp3.exception.FileUploadException;
import com.minh.zingmp3.model.Album;
import com.minh.zingmp3.model.Artist;
import com.minh.zingmp3.repositories.AlbumRepository;
import com.minh.zingmp3.repositories.ArtistRepository;
import com.minh.zingmp3.request.album.AddAddAlbumRequest;
import com.minh.zingmp3.request.album.AddArtistToAlbumRequest;
import com.minh.zingmp3.response.ListAlbumResponse;
import com.minh.zingmp3.services.upload.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private FileUpload fileUpload;
    @Override
    public Album addAlbum(AddAddAlbumRequest request) throws Exception {
        String urlImage = null;
        try{
            urlImage =  fileUpload.uploadFile(request.getImage());
            Artist artist = artistRepository.findById(request.getIdArtist()).orElseThrow(() -> new DataNotFoundException("Cannot find Artist with id"+request.getIdArtist()));
            Album album = Album.builder()
                    .img(urlImage)
                    .name(request.getName())
                    .artist(artist)
                    .build();
            try{
               return albumRepository.save(album);
            }catch (Exception e){
                throw new Exception("error while saving album: "+e.getMessage());
            }
        }catch (Exception e){
            throw new FileUploadException("error uploading image");
        }
    }

    @Override
    public Album addArtistToAlbum(AddArtistToAlbumRequest request) throws Exception {
        Artist artist = artistRepository.findById(request.getIdArtist()).orElseThrow(()-> new DataNotFoundException("Cannot find Artist with id"+request.getIdArtist()));
        Album album = albumRepository.findById(request.getIdAlbum()).orElseThrow(()-> new DataNotFoundException("Cannot find Album with id"+request.getIdAlbum()));
        album.setArtist(artist);
        try{
            return albumRepository.save(album);
        }catch (Exception e){
            throw new Exception("error while saving album: "+e.getMessage());
        }
    }

    @Override
    public Album findAlbumById(long id) throws Exception {
        return albumRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot find Album with id"+id));
    }

    @Override
    public ListAlbumResponse findAllAlbums(int size, int page) throws Exception {
        Pageable request = PageRequest.of(page, size);
        Page<Album> albums = albumRepository.findAll(request);
        return ListAlbumResponse.builder()
                .albums(albums.getContent())
                .total(albums.getTotalPages()).build();
    }

    @Override
    public List<Album> findTopAlbums(int limit) throws Exception {
        try{
            return albumRepository.findTopAllAlbums(limit);
        }catch (Exception e){
            throw new Exception("error finding top albums: "+e.getMessage());
        }
    }

    @Override
    public List<Album> getListAlbumByArtistId(long id) throws Exception {
        try{
            return albumRepository.findAllByArtistId(id);
        }catch (Exception e){
            throw new Exception("error finding albums: "+e.getMessage());
        }
    }

    @Override
    public List<Album> search(String keyword) {
        return albumRepository.findTopByAlbumByName(keyword);

    }

    @Override
    public boolean deleteAlbum(long id) throws Exception {
        if(!albumRepository.existsById(id)){
            throw new DataNotFoundException("Cannot find Album with id"+id);
        }
        try{
            albumRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new Exception("error deleting album: "+e.getMessage());
        }
    }

    @Override
    public Album getAlbumNewestByArtistId(long id) throws Exception {
        try{
            return albumRepository.findTopAlbumByArtistId(id,1);
        }catch (Exception e){
            throw new Exception("error finding albums newest: "+e.getMessage());
        }
    }
}
