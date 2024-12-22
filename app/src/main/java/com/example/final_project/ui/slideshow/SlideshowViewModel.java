package com.example.final_project.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.final_project.domain.Post;

import java.util.ArrayList;
import java.util.List;

public class SlideshowViewModel extends ViewModel {

    private final MutableLiveData<List<Post>> posts;

    public SlideshowViewModel() {
        posts = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<List<Post>> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        List<Post> currentPosts = posts.getValue();
        if (currentPosts != null) {
            currentPosts.add(0, post);
            posts.setValue(currentPosts);
        }
    }
}
