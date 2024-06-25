package com.minh.zingmp3.response;

import com.minh.zingmp3.model.Album;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListAlbumResponse {
    private int total;
    private List<Album> albums;
}
