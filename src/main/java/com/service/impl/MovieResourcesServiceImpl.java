package com.service.impl;

import com.common.constant.MovieResourceType;
import com.mapper.*;
import com.pojo.*;
import com.service.MovieResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class MovieResourcesServiceImpl implements MovieResourcesService {

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

    @Override
    public void insert(String name,String url,String column,String fullColumn,String type) {
        try {
            name = new String(name.getBytes("UTF-8"),"UTF-8");
        }catch (Exception e){
            System.out.println("name转换异常");
        }

        switch(column){
            case MovieResourceType.ASIANHM:
                Example example = new Example(AsianhmResource.class);
                example.createCriteria().andEqualTo("url",url);
                int count = asianhmResourceMapper.selectCountByExample(example);
                if (count <= 0){
                    AsianhmResource asianhmResource = new AsianhmResource();
                    asianhmResource.setName(name);
                    asianhmResource.setUrl(url);
                    asianhmResource.setFullColumn(fullColumn);
                    asianhmResource.setType(type);
                    asianhmResourceMapper.insert(asianhmResource);
                }
                break;
            case MovieResourceType.ASIANWM:
                Example example2 = new Example(AsianwmResource.class);
                example2.createCriteria().andEqualTo("url",url);
                int count2 = asianwmResourceMapper.selectCountByExample(example2);
                if (count2 <= 0){
                    AsianwmResource asianwmResource = new AsianwmResource();
                    asianwmResource.setName(name);
                    asianwmResource.setUrl(url);
                    asianwmResource.setFullColumn(fullColumn);
                    asianwmResource.setType(type);
                    asianwmResourceMapper.insert(asianwmResource);
                }
                break;
            case MovieResourceType.CARTOON:
                Example example3 = new Example(CartoonResource.class);
                example3.createCriteria().andEqualTo("url",url);
                int count3 = cartoonResourceMapper.selectCountByExample(example3);
                if (count3 <= 0){
                    CartoonResource cartoonResource = new CartoonResource();
                    cartoonResource.setName(name);
                    cartoonResource.setUrl(url);
                    cartoonResource.setFullColumn(fullColumn);
                    cartoonResource.setType(type);
                    cartoonResourceMapper.insert(cartoonResource);
                }
                break;
            case MovieResourceType.DOMESTIC:
                Example example4 = new Example(DomesticResource.class);
                example4.createCriteria().andEqualTo("url",url);
                int count4 = domesticResourceMapper.selectCountByExample(example4);
                if (count4 <= 0){
                    DomesticResource domesticResource = new DomesticResource();
                    domesticResource.setName(name);
                    domesticResource.setUrl(url);
                    domesticResource.setFullColumn(fullColumn);
                    domesticResource.setType(type);
                    domesticResourceMapper.insert(domesticResource);
                }
                break;
            case MovieResourceType.EUROPE:
                Example example5 = new Example(EuropeResource.class);
                example5.createCriteria().andEqualTo("url",url);
                int count5 = europeResourceMapper.selectCountByExample(example5);
                if (count5 <= 0){
                    EuropeResource europeResource = new EuropeResource();
                    europeResource.setName(name);
                    europeResource.setUrl(url);
                    europeResource.setFullColumn(fullColumn);
                    europeResource.setType(type);
                    europeResourceMapper.insert(europeResource);
                }
                break;
            case MovieResourceType.STAR:
                Example example6 = new Example(StarResource.class);
                example6.createCriteria().andEqualTo("url",url);
                int count6 = starResourceMapper.selectCountByExample(example6);
                if (count6 <= 0){
                    StarResource starResource = new StarResource();
                    starResource.setName(name);
                    starResource.setUrl(url);
                    starResource.setFullColumn(fullColumn);
                    starResource.setType(type);
                    starResourceMapper.insert(starResource);
                }
                break;
             default:
                 Example example7 = new Example(OtherResource.class);
                 example7.createCriteria().andEqualTo("url",url);
                 int count7 = otherResourceMapper.selectCountByExample(example7);
                 if (count7 <= 0){
                     OtherResource otherResource = new OtherResource();
                     otherResource.setName(name);
                     otherResource.setUrl(url);
                     otherResource.setFullColumn(fullColumn);
                     otherResource.setType(type);
                     otherResourceMapper.insert(otherResource);
                 }
                break;
        }

    }
}
