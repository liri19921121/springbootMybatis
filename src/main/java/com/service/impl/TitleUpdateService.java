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
    private ResourceAsianhmMapper asianhmResourceMapper;
    @Autowired
    private ResourceAsianwmMapper asianwmResourceMapper;
    @Autowired
    private ResourceCartoonMapper cartoonResourceMapper;
    @Autowired
    private ResourceDomesticMapper domesticResourceMapper;
    @Autowired
    private ResourceStarMapper starResourceMapper;
    @Autowired
    private ResourceOtherMapper otherResourceMapper;
    @Autowired
    private ResourceEuropeMapper europeResourceMapper;


    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void updateResourceMovieTitle( ResourceMovieTitle t){
        try {
            String type = t.getIndexColumn();
            if (MovieResourceType.DOMESTIC.equals(type)){
                ResourceDomestic dr = new ResourceDomestic();
                dr.setName(t.getTitle()+ MovieResourceType.ZXBF);
                ResourceDomestic d = domesticResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setTitleUrl(t.getTitleUrl());
                    d.setName(t.getTitle());
                    domesticResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.ASIANHM.equals(type)){
                ResourceAsianhm dr = new ResourceAsianhm();
                dr.setName(t.getTitle()+ MovieResourceType.ZXBF);
                ResourceAsianhm d = asianhmResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setTitleUrl(t.getTitleUrl());
                    d.setName(t.getTitle());
                    asianhmResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.ASIANWM.equals(type)){
                ResourceAsianwm dr = new ResourceAsianwm();
                dr.setName(t.getTitle()+ MovieResourceType.ZXBF);
                ResourceAsianwm d = asianwmResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setTitleUrl(t.getTitleUrl());
                    d.setName(t.getTitle());
                    asianwmResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.CARTOON.equals(type)){
                ResourceCartoon dr = new ResourceCartoon();
                dr.setName(t.getTitle()+ MovieResourceType.ZXBF);
                ResourceCartoon d = cartoonResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setTitleUrl(t.getTitleUrl());
                    d.setName(t.getTitle());
                    cartoonResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.EUROPE.equals(type)){
                ResourceEurope dr = new ResourceEurope();
                dr.setName(t.getTitle()+ MovieResourceType.ZXBF);
                ResourceEurope d = europeResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setTitleUrl(t.getTitleUrl());
                    d.setName(t.getTitle());
                    europeResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.STAR.equals(type)){
                ResourceStar dr = new ResourceStar();
                dr.setName(t.getTitle()+ MovieResourceType.ZXBF);
                ResourceStar d = starResourceMapper.selectOne(dr);
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
                ResourceDomestic dr = new ResourceDomestic();
                dr.setName(t.getTitle().substring(0,t.getTitle().length()-4));
                ResourceDomestic d = domesticResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setThunder(t.getThunder());
                    domesticResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.ASIANHM.equals(type)){
                ResourceAsianhm dr = new ResourceAsianhm();
                dr.setName(t.getTitle().substring(0,t.getTitle().length()-4));
                ResourceAsianhm d = asianhmResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setThunder(t.getThunder());
                    asianhmResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.ASIANWM.equals(type)){
                ResourceAsianwm dr = new ResourceAsianwm();
                dr.setName(t.getTitle().substring(0,t.getTitle().length()-4));
                ResourceAsianwm d = asianwmResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setThunder(t.getThunder());
                    asianwmResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.CARTOON.equals(type)){
                ResourceCartoon dr = new ResourceCartoon();
                dr.setName(t.getTitle().substring(0,t.getTitle().length()-4));
                ResourceCartoon d = cartoonResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setThunder(t.getThunder());
                    cartoonResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.EUROPE.equals(type)){
                ResourceEurope dr = new ResourceEurope();
                dr.setName(t.getTitle().substring(0,t.getTitle().length()-4));
                ResourceEurope d = europeResourceMapper.selectOne(dr);
                if (d !=null){
                    d.setThunder(t.getThunder());
                    europeResourceMapper.updateByPrimaryKeySelective(d);
                }
            }else if (MovieResourceType.STAR.equals(type)){
                ResourceStar dr = new ResourceStar();
                dr.setName(t.getTitle().substring(0,t.getTitle().length()-4));
                ResourceStar d = starResourceMapper.selectOne(dr);
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
