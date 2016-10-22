package rest;

public class ResponseWrapper{

    private MetadataWrapper metadata;
    private Object content;

    public ResponseWrapper(MetadataWrapper metadata) {
        this.metadata = metadata;
    }

    public ResponseWrapper(MetadataWrapper metadata, Object content) {
        this.metadata = metadata;
        this.content = content;
    }

    public MetadataWrapper getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataWrapper metadata) {
        this.metadata = metadata;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}