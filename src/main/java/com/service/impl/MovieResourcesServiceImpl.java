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

    @Override
    public void insert(String name,String url,String column,String fullColumn,String type) {
        try {
            name = new String(name.getBytes("UTF-8"),"UTF-8");
        }catch (Exception e){
            System.out.println("name转换异常");
        }

        switch(column){
            case MovieResourceType.ASIANHM:
                Example example = new Example(ResourceAsianhm.class);
                example.createCriteria().andEqualTo("url",url);
                int count = asianhmResourceMapper.selectCountByExample(example);
                if (count <= 0){
                    ResourceAsianhm asianhmResource = new ResourceAsianhm();
                    asianhmResource.setName(name);
                    asianhmResource.setUrl(url);
                    asianhmResource.setFullColumn(fullColumn);
                    asianhmResource.setType(type);
                    asianhmResourceMapper.insert(asianhmResource);
                    System.out.println("====新增成功====");
                }else{
                    System.out.println("====新增重复====");
                }
                break;
            case MovieResourceType.ASIANWM:
                Example example2 = new Example(ResourceAsianwm.class);
                example2.createCriteria().andEqualTo("url",url);
                int count2 = asianwmResourceMapper.selectCountByExample(example2);
                if (count2 <= 0){
                    ResourceAsianwm asianwmResource = new ResourceAsianwm();
                    asianwmResource.setName(name);
                    asianwmResource.setUrl(url);
                    asianwmResource.setFullColumn(fullColumn);
                    asianwmResource.setType(type);
                    asianwmResourceMapper.insert(asianwmResource);
                    System.out.println("====新增成功====");
                }else{
                    System.out.println("====新增重复====");
                }
                break;
            case MovieResourceType.CARTOON:
                Example example3 = new Example(ResourceCartoon.class);
                example3.createCriteria().andEqualTo("url",url);
                int count3 = cartoonResourceMapper.selectCountByExample(example3);
                if (count3 <= 0){
                    ResourceCartoon cartoonResource = new ResourceCartoon();
                    cartoonResource.setName(name);
                    cartoonResource.setUrl(url);
                    cartoonResource.setFullColumn(fullColumn);
                    cartoonResource.setType(type);
                    cartoonResourceMapper.insert(cartoonResource);
                    System.out.println("====新增成功====");
                }else{
                    System.out.println("====新增重复====");
                }
                break;
            case MovieResourceType.DOMESTIC:
                Example example4 = new Example(ResourceDomestic.class);
                example4.createCriteria().andEqualTo("url",url);
                int count4 = domesticResourceMapper.selectCountByExample(example4);
                if (count4 <= 0){
                    ResourceDomestic domesticResource = new ResourceDomestic();
                    domesticResource.setName(name);
                    domesticResource.setUrl(url);
                    domesticResource.setFullColumn(fullColumn);
                    domesticResource.setType(type);
                    domesticResourceMapper.insert(domesticResource);
                    System.out.println("====新增成功====");
                }else{
                    System.out.println("====新增重复====");
                }
                break;
            case MovieResourceType.EUROPE:
                Example example5 = new Example(ResourceEurope.class);
                example5.createCriteria().andEqualTo("url",url);
                int count5 = europeResourceMapper.selectCountByExample(example5);
                if (count5 <= 0){
                    ResourceEurope europeResource = new ResourceEurope();
                    europeResource.setName(name);
                    europeResource.setUrl(url);
                    europeResource.setFullColumn(fullColumn);
                    europeResource.setType(type);
                    europeResourceMapper.insert(europeResource);
                    System.out.println("====新增成功====");
                }else{
                    System.out.println("====新增重复====");
                }
                break;
            case MovieResourceType.STAR:
                Example example6 = new Example(ResourceStar.class);
                example6.createCriteria().andEqualTo("url",url);
                int count6 = starResourceMapper.selectCountByExample(example6);
                if (count6 <= 0){
                    ResourceStar starResource = new ResourceStar();
                    starResource.setName(name);
                    starResource.setUrl(url);
                    starResource.setFullColumn(fullColumn);
                    starResource.setType(type);
                    starResourceMapper.insert(starResource);
                    System.out.println("====新增成功====");
                }else{
                    System.out.println("====新增重复====");
                }
                break;
             default:
                 Example example7 = new Example(ResourceOther.class);
                 example7.createCriteria().andEqualTo("url",url);
                 int count7 = otherResourceMapper.selectCountByExample(example7);
                 if (count7 <= 0){
                     ResourceOther otherResource = new ResourceOther();
                     otherResource.setName(name);
                     otherResource.setUrl(url);
                     otherResource.setFullColumn(fullColumn);
                     otherResource.setType(type);
                     otherResourceMapper.insert(otherResource);
                     System.out.println("====新增成功====");
                 }else{
                     System.out.println("====新增重复====");
                 }
                break;
        }

    }
}
