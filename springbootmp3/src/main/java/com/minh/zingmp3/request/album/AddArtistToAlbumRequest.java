package com.minh.zingmp3.request.album;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddArtistToAlbumRequest {
    private long idAlbum;
    private long idArtist;
}
