package com.minh.zingmp3.repositories;

import com.minh.zingmp3.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findAllByArtistId(long id);
    @Query("select a from Album a where a.name like %?1% ")
    List<Album> findTopByAlbumByName(String name);
    @Query(value = "SELECT a FROM Album a order by a.releaseDate desc " )
    List<Album> findTopAllAlbums(@Param("limit") int limit);
    @Query("select a from Album a where a.artist.id = ?1 order by a.releaseDate desc")
    Album findTopAlbumByArtistId( long id , @Param("limit") int limit);


}