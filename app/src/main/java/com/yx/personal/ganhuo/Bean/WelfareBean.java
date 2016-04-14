package com.yx.personal.ganhuo.Bean;

import java.util.List;

/**
 * Created by YX on 16/4/12.
 */
public class WelfareBean {

    /**
     * error : false
     * results : [{"_id":"570c5e906776590f62db7b9b","createdAt":"2016-04-12T10:33:52.575Z","desc":"4.12","publishedAt":"2016-04-12T11:47:37.342Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/7a8aed7bjw1f2tpr3im0mj20f00l6q4o.jpg","used":true,"who":"张涵宇"},{"_id":"570b1c5a6776590f62db7b8e","createdAt":"2016-04-11T11:39:06.138Z","desc":"4.11","publishedAt":"2016-04-11T12:37:49.993Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bjw1f2sm0ns82hj20f00l8tb9.jpg","used":true,"who":"张涵宇"},{"_id":"5707051467765950c3190163","createdAt":"2016-04-08T09:10:44.529Z","desc":"4.8","publishedAt":"2016-04-08T12:18:10.29Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bjw1f2p0v9vwr5j20b70gswfi.jpg","used":true,"who":"张涵宇"},{"_id":"5705c962677659132abfddcd","createdAt":"2016-04-07T10:43:46.879Z","desc":"4.7","publishedAt":"2016-04-07T11:43:11.427Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bjw1f2nxxvgz7xj20hs0qognd.jpg","used":true,"who":"张涵宇"},{"_id":"570480c46776591325d463ff","createdAt":"2016-04-06T11:21:40.621Z","desc":"4.6","publishedAt":"2016-04-06T12:06:32.601Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1f2mteyftqqj20jg0siq6g.jpg","used":true,"who":"张涵宇"},{"_id":"570317ed677659634149029a","createdAt":"2016-04-05T09:42:05.911Z","desc":"4.5","publishedAt":"2016-04-05T10:45:46.487Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1f2lkx2lhgfj20f00f0dhm.jpg","used":true,"who":"张涵宇"},{"_id":"56fddfcd67765933d8be9193","createdAt":"2016-04-01T10:41:17.615Z","desc":"4.1","publishedAt":"2016-04-01T11:17:05.676Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bjw1f2h04lir85j20fa0mx784.jpg","used":true,"who":"张涵宇"},{"_id":"56fc8d3a67765933d9b0a9a9","createdAt":"2016-03-31T10:36:42.628Z","desc":"3.31","publishedAt":"2016-03-31T11:44:55.91Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bjw1f2fuecji0lj20f009oab3.jpg","used":true,"who":"张涵宇"},{"_id":"56fb7ca867765933d8be916d","createdAt":"2016-03-30T15:13:44.353Z","desc":"3.29","publishedAt":"2016-03-30T15:17:02.228Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034jw1f2ewruruvij20d70miadg.jpg","used":true,"who":"daimajia"},{"_id":"56f8ac1367765933d8be9154","createdAt":"2016-03-28T11:59:15.439Z","desc":"3.28","publishedAt":"2016-03-29T11:56:01.215Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bjw1f2cfxa9joaj20f00fzwg2.jpg","used":true,"who":"张涵宇"}]
     */

    private boolean error;
    /**
     * _id : 570c5e906776590f62db7b9b
     * createdAt : 2016-04-12T10:33:52.575Z
     * desc : 4.12
     * publishedAt : 2016-04-12T11:47:37.342Z
     * source : chrome
     * type : 福利
     * url : http://ww4.sinaimg.cn/large/7a8aed7bjw1f2tpr3im0mj20f00l6q4o.jpg
     * used : true
     * who : 张涵宇
     */

    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    

    public static class ResultsBean {
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
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

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
