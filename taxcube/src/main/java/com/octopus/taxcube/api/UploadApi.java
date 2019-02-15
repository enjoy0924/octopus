package com.octopus.taxcube.api;

import com.octopus.taxcube.common.ServerResponse;
import com.octopus.taxcube.eds.pojo.UploadVo;
import com.octopus.taxcube.eds.service.IGlobalConfigService;
import com.octopus.taxcube.eds.service.IUploadService;
import com.octopus.taxcube.exception.IllegalException;
import com.octopus.taxcube.exception.NotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@Api(value = "文件上传", tags = "文件上传")
@RequestMapping("/api")
@Slf4j
public class UploadApi {

    @Autowired
    private IUploadService uploadService;

    @Autowired
    private IGlobalConfigService globalConfigService;

    @RequestMapping(value = "/upload/img/{type}",consumes = "multipart/*", headers = "content-type=multipart/form-data", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.POST})
    @ApiOperation(value = "上传图片", notes = "此接口只上传图片，无业务逻辑")
    @ResponseBody
    public ServerResponse<UploadVo> uploadImg(
            @ApiParam(name = "type", value = "图片类型: idcard-身份证  product-产品 certificate-资格证 avatar-头像 ", required = true) @PathVariable("type") String type,
            @ApiParam(value = "要上传的文件", required = true) MultipartFile file, HttpServletRequest request
    ) {
        if (!file.isEmpty() && file.getContentType().startsWith("image")) {
            //定义临时文件夹
            String path;
            StringBuilder url = new StringBuilder(globalConfigService.getHttpPrefix());
            if(type.equals("idcard")){
                path = globalConfigService.getIdcardImgLocalPath();
                url.append(globalConfigService.getIdcardImgSuffixPath());
            }else if(type.equals("product")){
                path = globalConfigService.getProductImgLocalPath();
                url.append(globalConfigService.getProductImgSuffixPath());
            }else if(type.equals("avatar")){
                path = globalConfigService.getAvatarImgLocalPath();
                url.append(globalConfigService.getAvatarImgSuffixPath());
            }else if(type.equals("certificate")){
                path = globalConfigService.getCertificateImgLocalPath();
                url.append(globalConfigService.getCertificateImgSuffixPath());
            } else {
                throw new NotFoundException("不支持的图片类型");
            }

            String targetFileName = uploadService.upload(file, path);
            url.append(File.separator);
            url.append(targetFileName);

            UploadVo uploadVo = new UploadVo(targetFileName, url.toString());

            return ServerResponse.createBySuccess(uploadVo);
        } else {
            throw new IllegalException("请求文件文件格式不对");
        }

    }
}


