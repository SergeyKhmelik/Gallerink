package controller;

import org.springframework.http.HttpStatus;
import rest.MetadataWrapper;
import rest.ResponseWrapper;

public class BaseController {

    public ResponseWrapper ok(Object content) {
        MetadataWrapper metadataWrapper = new MetadataWrapper(HttpStatus.OK);
        return new ResponseWrapper(metadataWrapper, content);
    }

    public ResponseWrapper noContent() {
        return new ResponseWrapper(new MetadataWrapper(HttpStatus.NO_CONTENT), null);
    }

}
