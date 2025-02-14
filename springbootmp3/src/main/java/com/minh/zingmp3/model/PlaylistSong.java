package com.minh.zingmp3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minh.zingmp3.pk.PKSongPlaylist;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(PKSongPlaylist.class)
public class PlaylistSong implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_playlist")
    @JsonIgnore
    private PlayList playlist;
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_song")
    private Song song;

}
