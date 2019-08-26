package com.xuecheng.manage_cms_client.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author kavito
 * @date 2019/6/13 20:02
 */
public interface CmsPageRepository extends MongoRepository<CmsPage,String> {
}
