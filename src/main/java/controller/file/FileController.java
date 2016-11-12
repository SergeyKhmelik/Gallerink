package controller.file;


import controller.BaseController;
import controller.BaseException;
import controller.RequestError;
import domain.file.FileEntity;
import dto.file._FileEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rest.ResponseWrapper;
import service.file.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Api(value = "/api/file_", description = "File management flow")
@RestController
@RequestMapping(value = "/api/file")
public class FileController extends BaseController {

    public static final String IMAGE_EXTENSIONS = "jpg|png|tiff|gif|jpeg";
    private static final String IMAGE_FOLDER_NAME = "images";
    @Autowired
    private FileService fileService;

    @ApiOperation(
            value = "Upload image file",
            notes = "Upload image file",
            httpMethod = "POST",
            consumes = "multipart/form-data"
    )
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "file",
                            dataType = "file",
                            value = "File to upload",
                            paramType = "form"
                    ),
                    @ApiImplicitParam(
                            name = "Authorization",
                            dataType = "string",
                            required = false,
                            paramType = "header",
                            value = "Authorization header"
                    )
            }
    )
    @RequestMapping(method = RequestMethod.POST)
    public ResponseWrapper uploadImage(@RequestParam("file") MultipartFile file) throws BaseException {
        if (file.isEmpty()) {
            throw new BaseException(RequestError.FILE_REQUIRED);
        }
        if (!FilenameUtils.getExtension(file.getOriginalFilename()).matches(IMAGE_EXTENSIONS)) {
            throw new BaseException(RequestError.FILE_FORMAT_EXCEPTION);
        }

        FileEntity fileEntity = new FileEntity();
        String filename = fileEntity.getUuid() + '.' + FilenameUtils.getExtension(file.getOriginalFilename());
        fileEntity.setFilename(filename);
        fileEntity.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
        try {
            createImagesDirIfNotExist();
            Files.copy(file.getInputStream(), Paths.get(System.getProperty("user.dir") + "/" + IMAGE_FOLDER_NAME).resolve(filename));
            fileService.save(fileEntity);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException(RequestError.IMAGE_UPLOAD_ERROR);
        }

        return ok(new _FileEntity(fileEntity));
    }

    @ApiOperation(
            value = "Get image",
            notes = "Get image file by uuid",
            httpMethod = "GET"
    )
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "uuid",
                            dataType = "string",
                            required = true,
                            paramType = "path",
                            value = "Image uuid"
                    ),
                    @ApiImplicitParam(
                            name = "Authorization",
                            dataType = "string",
                            required = false,
                            paramType = "header",
                            value = "Authorization header"
                    )
            }
    )
    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getFile(@PathVariable String uuid) throws BaseException, IOException {
        FileEntity fileEntity = fileService.get(uuid);
        if(fileEntity == null) {
            throw new BaseException(RequestError.FILE_NOT_FOUND);
        }

        File file = new File(System.getProperty("user.dir") + "/" + IMAGE_FOLDER_NAME + "/" + fileEntity.getFilename());
        InputStream inputStream = new FileInputStream(file);
        return IOUtils.toByteArray(inputStream);
    }

    private void createImagesDirIfNotExist() {
        File imagesDir = new File(IMAGE_FOLDER_NAME);
        if(!imagesDir.exists()) {
            imagesDir.mkdir();
        }
    }

}
