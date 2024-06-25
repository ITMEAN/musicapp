package com.minh.zingmp3.services.song;

import com.minh.zingmp3.enums.Country;
import com.minh.zingmp3.exception.DataNotFoundException;
import com.minh.zingmp3.exception.FileUploadException;
import com.minh.zingmp3.model.Album;
import com.minh.zingmp3.model.Artist;
import com.minh.zingmp3.model.Song;
import com.minh.zingmp3.model.SongArtist;
import com.minh.zingmp3.repositories.AlbumRepository;
import com.minh.zingmp3.repositories.ArtistRepository;
import com.minh.zingmp3.repositories.SongArtistRepository;
import com.minh.zingmp3.repositories.SongRepository;
import com.minh.zingmp3.request.song.CreateSongRequest;
import com.minh.zingmp3.services.upload.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private SongArtistRepository songArtistRepository;
    @Autowired
    private FileUpload fileUpload;


    @Override
    public Song addSong(CreateSongRequest request) throws Exception {
        try {
            Album album = albumRepository.findById(request.getIdAlbum()).orElseThrow(() -> new DataNotFoundException("cannot find album with id:" + request.getIdAlbum()));
            String img = fileUpload.uploadFile(request.getImg());
            String mp3 = fileUpload.uploadFile(request.getMp3());
            Song song = Song.builder()
                    .img(img)
                    .mp3(mp3)
                    .name(request.getName())
                    .album(album)
                    .songArtists(new ArrayList<>())
                    .playlistSongs(new ArrayList<>())
                    .releaseDate(request.getReleaseDate())
                    .build();
            request.getIdArtists().forEach(id -> {
                try {
                    Artist artist = artistRepository.findById(id).orElseThrow(() -> new DataNotFoundException("cannot find artist with id:"+id));
                    SongArtist songArtist = SongArtist.builder().artist(artist).song(song).build();
                    song.getSongArtists().add(songArtist);
                } catch (DataNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            return songRepository.save(song);
        } catch (Exception e) {
            throw new FileUploadException("error upload file" + e.getMessage());
        }

    }

    @Override
    public List<Song> search(String keyword) throws Exception {
        return songRepository.findTopSongByNameContainingIgnoreCase(keyword, 10);
    }

    @Override
    public List<Song> getListSongNewByArtistId(long artistId) throws Exception {
        return songArtistRepository.findTopSongsByArtist_Id(artistId);
    }

    @Override
    public List<Song> getSongsByAlbum_Id(long id) throws Exception {
        try{
            return songRepository.getSongsByAlbum_Id(id);
        }catch (Exception e){
            throw new Exception("cannot find song:" + e.getMessage());
        }
    }

    @Override
    public List<Song> getSongByArtistCountry(String country) throws Exception {
        Country countryE = Country.valueOf(country);
        try{
            return songRepository.findTopSongsByArtistCountry(countryE,Pageable.ofSize(6));
        }catch (Exception e){
            throw new Exception("cannot find song:" + e.getMessage());
        }
    }


    @Override
    public Page<Song> findAllSong(int pageNo, int pageSize, String sortBy, String sortDirection) throws Exception {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return songRepository.findAll(pageable);
    }

    @Override
    public Song findSongById(Long id) throws Exception {
        return songRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Song Not Found"));
    }

    @Override
    public List<Song> findAllSong() throws Exception {
        return songRepository.findAll();
    }

    @Override
    public List<Song> findTopSong() throws Exception {
        return songRepository.findAll(PageRequest.of(0, 9)).getContent();
    }


}
