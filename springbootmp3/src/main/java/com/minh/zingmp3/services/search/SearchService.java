package com.minh.zingmp3.services.search;

import com.minh.zingmp3.response.SearchRespone;

public interface SearchService {
    SearchRespone search(String keyword) throws Exception;
}
