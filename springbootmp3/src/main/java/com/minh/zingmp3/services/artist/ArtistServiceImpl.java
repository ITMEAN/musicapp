package com.minh.zingmp3.services.artist;

import com.minh.zingmp3.enums.Country;
import com.minh.zingmp3.exception.DataNotFoundException;
import com.minh.zingmp3.exception.FileUploadException;
import com.minh.zingmp3.model.Album;
import com.minh.zingmp3.model.Artist;
import com.minh.zingmp3.repositories.ArtistRepository;
import com.minh.zingmp3.request.artist.AddArtistRequest;
import com.minh.zingmp3.response.ListArtistResponse;
import com.minh.zingmp3.services.upload.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService{
    @Autowired
    private final ArtistRepository artistRepository;
    @Autowired
    private final FileUpload fileUpload;
    @Override
    public Artist addArtist(AddArtistRequest request) throws Exception {
        String avatar = null;
        try{
            avatar = fileUpload.uploadFile(request.getAvatar());
            Artist artist = Artist.builder()
                    .albums(new HashSet<Album>())
                    .fullName(request.getName())
                    .img(avatar)
                    .country(Country.valueOf(request.getCountry()))
                    .build();
            try{
                return  artistRepository.save(artist);
            }catch (Exception e){
                throw new Exception("error while saving artist");
            }
        }catch (Exception e){
            throw new FileUploadException("error uploading file");
        }
    }

    @Override
    public Artist getArtistById(long id) throws Exception {
       return artistRepository.findById(id).orElseThrow(()->new DataNotFoundException("cannot find artist with id:"+id));
    }

    @Override
    public ListArtistResponse findAllArtists(int size, int page) throws Exception {
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Artist> artists = artistRepository.findAll(pageable);
        return ListArtistResponse.builder().total(artists.getTotalPages()).artists(artists.getContent()).build();
    }

    @Override
    public List<Artist> search(String keyword) throws Exception {
        return artistRepository.findTopByFullNameContainingIgnoreCase(keyword,10);
    }

    @Override
    public List<Artist> getTopArtist(int limit) throws Exception {
        try{
            return artistRepository.getTopArtist(limit);
        }catch (Exception e){
            throw new Exception("error while get top artist"+e.getMessage());
        }
    }
}
