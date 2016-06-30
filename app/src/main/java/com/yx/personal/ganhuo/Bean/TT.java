package com.yx.personal.ganhuo.Bean;

/**
 * Created by YX on 16/6/29.
 */
public class TT {

    /**
     * dataType : Banner
     * title :
     * description :
     * image : http://img.wdjimg.com/image/video/2c892eeefb675b22636ce6f71d2ec76c_0_0.png
     * actionUrl : eyepetizer://recommend/
     * adTrack : null
     * shade : false
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
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
