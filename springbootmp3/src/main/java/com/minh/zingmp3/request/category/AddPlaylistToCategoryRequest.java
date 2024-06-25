package com.minh.zingmp3.request.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AddPlaylistToCategoryRequest {
    private long categoryId;
    private long playlistId;
}
