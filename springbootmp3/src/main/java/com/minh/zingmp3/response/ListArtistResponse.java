package com.minh.zingmp3.response;

import com.minh.zingmp3.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListArtistResponse {
    private int total;
    private List<Artist> artists;
}
