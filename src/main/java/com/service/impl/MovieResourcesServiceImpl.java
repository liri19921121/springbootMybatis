package com.service.impl;

import com.mapper.MovieResourcesMapper;
import com.pojo.MovieResources;
import com.service.MovieResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class MovieResourcesServiceImpl implements MovieResourcesService {

    @Autowired
    private MovieResourcesMapper movieResourcesMapper;

    @Override
    public void insert(String name,String url,String column,String fullColumn) {
        MovieResources movieResources = new MovieResources();
        try {
            name = new String(name.getBytes("UTF-8"),"UTF-8");
        }catch (Exception e){
            System.out.println("name转换异常");
        }
        //查询是否有重复的
        Example example = new Example(MovieResources.class);
        example.createCriteria().andEqualTo("mp4Url",url);
       int count = movieResourcesMapper.selectCountByExample(example);
       if (count <= 0){
           movieResources.setName(name);
           movieResources.setMp4Url(url);
           movieResources.setPlaceColumn(column);
           movieResources.setFullColumn(fullColumn);
           movieResourcesMapper.insert(movieResources);
       }
    }
}
