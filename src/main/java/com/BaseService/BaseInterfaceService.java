
package com.BaseService;

import java.util.HashMap;
import java.util.List;


/**
 * devservice
 * @author rstyro
 * @version v1.0
 */

public interface BaseInterfaceService<T> {
	
        /**
         * 根据主键删除
         */
     	public int deleteByPrimaryKey(Long id) throws Exception;

	    /**
	     * 插入一条全新的记录
	     */
     	public int insert(T t) throws Exception;

	    /**
	     * 插入一条全新的记录  （带字段选择）
	     */
     	public int insertSelective(T t) throws Exception;

        /**
         * 根据主键查询记录
         * @param configId
         * @return
         */
     	public T selectByPrimaryKey(Long id) throws Exception;

	    /**
	     * 更新记录（带字段）
	     */
     	public int updateByPrimaryKeySelective(T t) throws Exception;

	    /**
	     * 更新记录（全字段）
	     */
     	public int updateByPrimaryKey(T t) throws Exception;
     	
     	
     	/**
     	 * 保存或新增
     	 */
    	public boolean saveOrUpdate(T t, String operateType) throws Exception;
    	
    	/**
    	 * 根据主键取对象
    	 */
    	public T getById(Long id) throws Exception;
    	
    	/**
    	 * 根据 主键 
    	 */
    	public boolean deleteT(String ids) throws Exception;
    	
       	/**
    	 * 根据条件查询 对象
    	 */
	    public T getByCondition(HashMap<String, Object> argmap)throws Exception;

		/**
		 * 从缓存中读取配置，若没有则查数据库
		 */
	    public T getConfigByCache(String configType, String configKey)throws Exception;

	    
	    /**
	     * 根据条件查询 列表集合
	     */
	    public List<T> getByCondition4List(HashMap<String, Object> argmap)throws Exception;
	    /**
	     * 根据条件查询集合数量
	     */
	    public long getByCondition4Count(HashMap<String, Object> argmap) throws Exception;
}
