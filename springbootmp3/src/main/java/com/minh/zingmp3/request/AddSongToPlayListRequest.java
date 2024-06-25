package com.minh.zingmp3.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddSongToPlayListRequest {
    private long idSong;
    private long idPlayList;
}
