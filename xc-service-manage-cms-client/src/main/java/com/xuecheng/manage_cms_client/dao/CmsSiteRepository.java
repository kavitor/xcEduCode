package com.xuecheng.manage_cms_client.dao;

import com.xuecheng.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author kavito
 * @date 2019/6/13 20:02
 */
public interface CmsSiteRepository extends MongoRepository<CmsSite,String> {
}