import com.fasterxml.jackson.annotation.JsonProperty;

public class Post {
    private final String copyright;
    private final String data;
    private final String explanation;
    private final String hdurl;
    private final String mediaType;
    private final String serviceVersion;
    private final String title;
    private final String url;

    public Post(@JsonProperty("copyright") String copyright,
                @JsonProperty("date") String data,
                @JsonProperty("explanation") String explanation,
                @JsonProperty("hdurl") String hdurl,
                @JsonProperty("media_type") String mediaType,
                @JsonProperty("service_version") String serviceVersion,
                @JsonProperty("title") String title,
                @JsonProperty("url") String url) {
        this.copyright = copyright;
        this.data = data;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
        this.title = title;
        this.url = url;
    }

    public String getData() {
        return data;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getCopyright() {
        return copyright;
    }

    @Override
    public String toString() {
        return "Post{" +
                "copyright='" + copyright + '\'' +
                ", data='" + data + '\'' +
                ", explanation='" + explanation + '\'' +
                ", hdurl='" + hdurl + '\'' +
                ", media_type='" + mediaType + '\'' +
                ", service_version='" + serviceVersion + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
