package com.minh.zingmp3.services.category;

import com.minh.zingmp3.exception.DataNotFoundException;
import com.minh.zingmp3.model.Category;
import com.minh.zingmp3.model.CategoryPlaylist;
import com.minh.zingmp3.model.PlayList;
import com.minh.zingmp3.repositories.CategoryRepository;
import com.minh.zingmp3.repositories.PlayListRepository;
import com.minh.zingmp3.request.category.AddPlaylistToCategoryRequest;
import com.minh.zingmp3.request.category.CreateCategoryRequest;
import com.minh.zingmp3.response.ListCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final PlayListRepository playListRepository;
    @Override
    public Category createCategory(CreateCategoryRequest request) throws Exception {
        try{
            Category category = Category.builder().name(request.getName()).build();
            return categoryRepository.save(category);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Category getCategoryById(long id) throws Exception {
        return categoryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot find category with id"+ id));
    }

    @Override
    public ListCategoryResponse getAllCategory(int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Category> categories = categoryRepository.findAll(pageable);
        try{
            return ListCategoryResponse.builder().total(categories.getTotalPages()).categories(categories.stream().toList()).build();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean addPlaylistToCategory(AddPlaylistToCategoryRequest request) throws Exception {
       try{
           Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new DataNotFoundException("Cannot find category with id"+ request.getCategoryId()));
           PlayList playList = playListRepository.findById(request.getPlaylistId()).orElseThrow(()-> new DataNotFoundException("Cannot find playlist with id"+ request.getPlaylistId()));
           CategoryPlaylist categoryPlaylist= CategoryPlaylist.builder().category(category).playlist(playList).build();
           category.getCategoryPlaylists().add(categoryPlaylist);
           categoryRepository.save(category);
           return true;
       }catch (Exception e){
           throw new Exception("error when add playlist to category:"+e.getMessage());
       }
    }
}
