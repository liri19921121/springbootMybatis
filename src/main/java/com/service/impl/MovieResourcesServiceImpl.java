package com.service.impl;

import com.mapper.MovieResourcesMapper;
import com.pojo.MovieResources;
import com.service.MovieResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieResourcesServiceImpl implements MovieResourcesService {

    @Autowired
    private MovieResourcesMapper movieResourcesMapper;

    @Override
    public void insert(String name,String url) {
        MovieResources movieResources = new MovieResources();
        movieResources.setName(name);
        movieResources.setMp4Url(url);
        movieResourcesMapper.insert(movieResources);
    }
}
