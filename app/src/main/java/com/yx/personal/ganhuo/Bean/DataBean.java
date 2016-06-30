package com.yx.personal.ganhuo.Bean;

import java.util.List;

/**
 * Created by YX on 16/6/29.
 */
public class DataBean {


    private String dataType;
    private int count;
    /**
     * type : banner1
     * data : {"dataType":"Banner","title":"","description":"","image":"http://img.wdjimg.com/image/video/0eb3bd4e59cb795dfcce32877cc79cc9_0_0.jpeg","actionUrl":"eyepetizer://webview/?title=%E6%AF%95%E4%B8%9A%E5%AD%A3+%C2%B7+%E4%B8%8D%E8%A6%81%E8%80%BD%E8%AF%AF%E8%87%AA%E5%B7%B1&url=http%3A%2F%2Fwww.wandoujia.com%2Feyepetizer%2Farticle.html%3Fnid%3D899%26shareable%3Dtrue","adTrack":null,"shade":false}
     */

    private List<ItemListBean> itemList;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ItemListBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemListBean> itemList) {
        this.itemList = itemList;
    }

    public static class ItemListBean {
        private String type;
        /**
         * dataType : Banner
         * title :
         * description :
         * image : http://img.wdjimg.com/image/video/0eb3bd4e59cb795dfcce32877cc79cc9_0_0.jpeg
         * actionUrl : eyepetizer://webview/?title=%E6%AF%95%E4%B8%9A%E5%AD%A3+%C2%B7+%E4%B8%8D%E8%A6%81%E8%80%BD%E8%AF%AF%E8%87%AA%E5%B7%B1&url=http%3A%2F%2Fwww.wandoujia.com%2Feyepetizer%2Farticle.html%3Fnid%3D899%26shareable%3Dtrue
         * adTrack : null
         * shade : false
         */

        private DataBodyBean data;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public DataBodyBean getData() {
            return data;
        }

        public void setData(DataBodyBean data) {
            this.data = data;
        }

        public static class DataBodyBean {
            private String dataType;
            private String title;
            private String description;
            private String image;
            private String actionUrl;
            private Object adTrack;
            private boolean shade;

            public String getDataType() {
                return dataType;
            }

            public void setDataType(String dataType) {
                this.dataType = dataType;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getActionUrl() {
                return actionUrl;
            }

            public void setActionUrl(String actionUrl) {
                this.actionUrl = actionUrl;
            }

            public Object getAdTrack() {
                return adTrack;
            }

            public void setAdTrack(Object adTrack) {
                this.adTrack = adTrack;
            }

            public boolean isShade() {
                return shade;
            }

            public void setShade(boolean shade) {
                this.shade = shade;
            }
        }
    }


    private int id;
    private String title;
    private String description;
    /**
     * name : YouTube
     * alias : youtube
     * icon : http://img.wdjimg.com/image/video/fa20228bc5b921e837156923a58713f6_256_256.png
     */

    private ProviderBean provider;
    private String category;
    private Object author;
    /**
     * feed : http://img.wdjimg.com/image/video/3a22ba3dced4e22489108cc14480479b_0_0.jpeg
     * detail : http://img.wdjimg.com/image/video/3a22ba3dced4e22489108cc14480479b_0_0.jpeg
     * blurred : http://img.wdjimg.com/image/video/00570bf43ee7a9aeb4e0f65dcb6742c4_0_0.jpeg
     * sharing : null
     */

    private CoverBean cover;
    private String playUrl;
    private int duration;
    /**
     * raw : http://www.wandoujia.com/eyepetizer/detail.html?vid=7696
     * forWeibo : http://wandou.im/2biz0u
     */

    private WebUrlBean webUrl;
    private long releaseTime;
    /**
     * collectionCount : 267
     * shareCount : 496
     * playCount : 0
     * replyCount : 25
     */

    private ConsumptionBean consumption;
    private Object campaign;
    private Object waterMarks;
    private Object adTrack;
    private String type;
    private int idx;
    private Object shareAdTrack;
    private Object favoriteAdTrack;
    private Object webAdTrack;
    private long date;
    private Object promotion;
    private Object label;
    /**
     * height : 360
     * width : 640
     * name : 流畅
     * type : low
     * url : http://uc-baobab.wdjcdn.com/1465387592837_7696_640x360.mp4
     */

    private List<PlayInfoBean> playInfo;
    /**
     * id : 16
     * name : 广告
     * actionUrl : eyepetizer://tag/16/?title=%E5%B9%BF%E5%91%8A
     * adTrack : null
     */

    private List<TagsBean> tags;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProviderBean getProvider() {
        return provider;
    }

    public void setProvider(ProviderBean provider) {
        this.provider = provider;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Object getAuthor() {
        return author;
    }

    public void setAuthor(Object author) {
        this.author = author;
    }

    public CoverBean getCover() {
        return cover;
    }

    public void setCover(CoverBean cover) {
        this.cover = cover;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public WebUrlBean getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(WebUrlBean webUrl) {
        this.webUrl = webUrl;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public ConsumptionBean getConsumption() {
        return consumption;
    }

    public void setConsumption(ConsumptionBean consumption) {
        this.consumption = consumption;
    }

    public Object getCampaign() {
        return campaign;
    }

    public void setCampaign(Object campaign) {
        this.campaign = campaign;
    }

    public Object getWaterMarks() {
        return waterMarks;
    }

    public void setWaterMarks(Object waterMarks) {
        this.waterMarks = waterMarks;
    }

    public Object getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(Object adTrack) {
        this.adTrack = adTrack;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public Object getShareAdTrack() {
        return shareAdTrack;
    }

    public void setShareAdTrack(Object shareAdTrack) {
        this.shareAdTrack = shareAdTrack;
    }

    public Object getFavoriteAdTrack() {
        return favoriteAdTrack;
    }

    public void setFavoriteAdTrack(Object favoriteAdTrack) {
        this.favoriteAdTrack = favoriteAdTrack;
    }

    public Object getWebAdTrack() {
        return webAdTrack;
    }

    public void setWebAdTrack(Object webAdTrack) {
        this.webAdTrack = webAdTrack;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Object getPromotion() {
        return promotion;
    }

    public void setPromotion(Object promotion) {
        this.promotion = promotion;
    }

    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = label;
    }

    public List<PlayInfoBean> getPlayInfo() {
        return playInfo;
    }

    public void setPlayInfo(List<PlayInfoBean> playInfo) {
        this.playInfo = playInfo;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public static class ProviderBean {
        private String name;
        private String alias;
        private String icon;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public static class CoverBean {
        private String feed;
        private String detail;
        private String blurred;
        private Object sharing;

        public String getFeed() {
            return feed;
        }

        public void setFeed(String feed) {
            this.feed = feed;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getBlurred() {
            return blurred;
        }

        public void setBlurred(String blurred) {
            this.blurred = blurred;
        }

        public Object getSharing() {
            return sharing;
        }

        public void setSharing(Object sharing) {
            this.sharing = sharing;
        }
    }

    public static class WebUrlBean {
        private String raw;
        private String forWeibo;

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public String getForWeibo() {
            return forWeibo;
        }

        public void setForWeibo(String forWeibo) {
            this.forWeibo = forWeibo;
        }
    }

    public static class ConsumptionBean {
        private int collectionCount;
        private int shareCount;
        private int playCount;
        private int replyCount;

        public int getCollectionCount() {
            return collectionCount;
        }

        public void setCollectionCount(int collectionCount) {
            this.collectionCount = collectionCount;
        }

        public int getShareCount() {
            return shareCount;
        }

        public void setShareCount(int shareCount) {
            this.shareCount = shareCount;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }
    }

    public static class PlayInfoBean {
        private int height;
        private int width;
        private String name;
        private String type;
        private String url;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class TagsBean {
        private int id;
        private String name;
        private String actionUrl;
        private Object adTrack;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getActionUrl() {
            return actionUrl;
        }

        public void setActionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
        }

        public Object getAdTrack() {
            return adTrack;
        }

        public void setAdTrack(Object adTrack) {
            this.adTrack = adTrack;
        }
    }


    private String text;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    /**
     * banner1
     * @return
     */
    private String image;
    private String actionUrl;
    private boolean shade;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }



    public boolean isShade() {
        return shade;
    }

    public void setShade(boolean shade) {
        this.shade = shade;
    }


    @Override
    public String toString() {
        return "DataBean{" +
                "dataType='" + dataType + '\'' +
                ", count=" + count +
                ", itemList=" + itemList +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", provider=" + provider +
                ", category='" + category + '\'' +
                ", author=" + author +
                ", cover=" + cover +
                ", playUrl='" + playUrl + '\'' +
                ", duration=" + duration +
                ", webUrl=" + webUrl +
                ", releaseTime=" + releaseTime +
                ", consumption=" + consumption +
                ", campaign=" + campaign +
                ", waterMarks=" + waterMarks +
                ", adTrack=" + adTrack +
                ", type='" + type + '\'' +
                ", idx=" + idx +
                ", shareAdTrack=" + shareAdTrack +
                ", favoriteAdTrack=" + favoriteAdTrack +
                ", webAdTrack=" + webAdTrack +
                ", date=" + date +
                ", promotion=" + promotion +
                ", label=" + label +
                ", playInfo=" + playInfo +
                ", tags=" + tags +
                ", text='" + text + '\'' +
                '}';
    }
}
