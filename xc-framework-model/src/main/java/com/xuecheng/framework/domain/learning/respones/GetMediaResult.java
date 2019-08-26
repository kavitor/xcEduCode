package com.xuecheng.framework.domain.learning.respones;

import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author kavito
 * @date 2019/6/30 13:54
 */
@Data
@ToString
@NoArgsConstructor//feign远程调用需要无参构造
public class GetMediaResult extends ResponseResult {
    //视频播放地址
    String fileUrl;
    public GetMediaResult(ResultCode resultCode,String fileUrl){
        super(resultCode);
        this.fileUrl = fileUrl;
    }
}
