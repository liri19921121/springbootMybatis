package com.service.impl;

import com.common.constant.MovieResourceType;
import com.mapper.*;
import com.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TitleUpdateService {
    @Autowired
    private AsianhmResourceMapper asianhmResourceMapper;
    @Autowired
    private AsianwmResourceMapper asianwmResourceMapper;
    @Autowired
    private CartoonResourceMapper cartoonResourceMapper;
    @Autowired
    private DomesticResourceMapper domesticResourceMapper;
    @Autowired
    private StarResourceMapper starResourceMapper;
    @Autowired
    private OtherResourceMapper otherResourceMapper;
    @Autowired
    private EuropeResourceMapper europeResourceMapper;


    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void updateResourceMovieTitle( ResourceMovieTitle t){
        try {
            String type = t.getIndexColumn();
            if (MovieResourceType.DOMESTIC.equals(type)){
                DomesticResource dr = new DomesticResource();
                dr.setName(t.getTitle()+ MovieResourceType.ZXBF);
                DomesticResource d = domesticResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setTitleUrl(t.getTitleUrl());
                    d.setName(t.getTitle());
                    domesticResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.ASIANHM.equals(type)){
                AsianhmResource dr = new AsianhmResource();
                dr.setName(t.getTitle()+ MovieResourceType.ZXBF);
                AsianhmResource d = asianhmResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setTitleUrl(t.getTitleUrl());
                    d.setName(t.getTitle());
                    asianhmResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.ASIANWM.equals(type)){
                AsianwmResource dr = new AsianwmResource();
                dr.setName(t.getTitle()+ MovieResourceType.ZXBF);
                AsianwmResource d = asianwmResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setTitleUrl(t.getTitleUrl());
                    d.setName(t.getTitle());
                    asianwmResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.CARTOON.equals(type)){
                CartoonResource dr = new CartoonResource();
                dr.setName(t.getTitle()+ MovieResourceType.ZXBF);
                CartoonResource d = cartoonResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setTitleUrl(t.getTitleUrl());
                    d.setName(t.getTitle());
                    cartoonResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.EUROPE.equals(type)){
                EuropeResource dr = new EuropeResource();
                dr.setName(t.getTitle()+ MovieResourceType.ZXBF);
                EuropeResource d = europeResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setTitleUrl(t.getTitleUrl());
                    d.setName(t.getTitle());
                    europeResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.STAR.equals(type)){
                StarResource dr = new StarResource();
                dr.setName(t.getTitle()+ MovieResourceType.ZXBF);
                StarResource d = starResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setTitleUrl(t.getTitleUrl());
                    d.setName(t.getTitle());
                    starResourceMapper.updateByPrimaryKeySelective(d);
                }
            }
        }catch (Exception e){
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void updateResourceThunder(ResourceThunder t){
        try {
            String type = t.getIndexColumn();
            if (MovieResourceType.DOMESTIC.equals(type)){
                DomesticResource dr = new DomesticResource();
                dr.setName(t.getTitle().substring(0,t.getTitle().length()-4));
                DomesticResource d = domesticResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setThunder(t.getThunder());
                    domesticResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.ASIANHM.equals(type)){
                AsianhmResource dr = new AsianhmResource();
                dr.setName(t.getTitle().substring(0,t.getTitle().length()-4));
                AsianhmResource d = asianhmResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setThunder(t.getThunder());
                    asianhmResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.ASIANWM.equals(type)){
                AsianwmResource dr = new AsianwmResource();
                dr.setName(t.getTitle().substring(0,t.getTitle().length()-4));
                AsianwmResource d = asianwmResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setThunder(t.getThunder());
                    asianwmResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.CARTOON.equals(type)){
                CartoonResource dr = new CartoonResource();
                dr.setName(t.getTitle().substring(0,t.getTitle().length()-4));
                CartoonResource d = cartoonResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setThunder(t.getThunder());
                    cartoonResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.EUROPE.equals(type)){
                EuropeResource dr = new EuropeResource();
                dr.setName(t.getTitle().substring(0,t.getTitle().length()-4));
                EuropeResource d = europeResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setThunder(t.getThunder());
                    europeResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.STAR.equals(type)){
                StarResource dr = new StarResource();
                dr.setName(t.getTitle().substring(0,t.getTitle().length()-4));
                StarResource d = starResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setThunder(t.getThunder());
                    starResourceMapper.updateByPrimaryKeySelective(d);
                }
            }
        }catch (Exception e){
           System.out.println("失败---------------------");
        }
    }

}
