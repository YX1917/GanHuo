package com.yx.personal.ganhuo.Bean;

import java.util.List;

/**
 * Created by YX on 16/5/24.
 */
public class AndroidInfoBean {

    /**
     * error : false
     * results : [{"_id":"5742f4236776593b2d517258","createdAt":"2016-05-23T20:14:27.784Z","desc":"Android代码规范利器： Checkstyle","publishedAt":"2016-05-24T11:56:12.924Z","source":"chrome","type":"Android","url":"http://droidyue.com/blog/2016/05/22/use-checkstyle-for-better-code-style/","used":true,"who":"蒋朋"},{"_id":"5742e337677659348b03a9d7","createdAt":"2016-05-23T19:02:15.49Z","desc":"基于Gank.IO提供的API的第三方客户端","publishedAt":"2016-05-24T11:56:12.924Z","source":"chrome","type":"Android","url":"https://github.com/burgessjp/GanHuoIO","used":true,"who":"onlylemi"},{"_id":"5742dcce67765934881e6dd2","createdAt":"2016-05-23T18:34:54.324Z","desc":"RxJava封装的ViewStub。使用方式与RxBinding相似。","publishedAt":"2016-05-24T11:56:12.924Z","source":"web","type":"Android","url":"https://github.com/SmartDengg/RxViewStub","used":true,"who":"小鄧子"},{"_id":"5742dc82677659349111d5c7","createdAt":"2016-05-23T18:33:38.612Z","desc":"用RxJava下的Producer封装的点击事件监听，并添加了一定范围内的防抖动效果。","publishedAt":"2016-05-24T11:56:12.924Z","source":"web","type":"Android","url":"https://github.com/SmartDengg/RxDebounceClick","used":true,"who":"小鄧子"},{"_id":"5742d15b677659348e29e28a","createdAt":"2016-05-23T17:46:03.582Z","desc":"基于Gank.IO提供的API的第三方客户端，可以对信息进行在线收藏[项目开源]","publishedAt":"2016-05-24T11:56:12.924Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/3f137269a942","used":true,"who":"solid"},{"_id":"5742c50b677659348e29e287","createdAt":"2016-05-23T16:53:31.470Z","desc":"Android开发艺术探索学习笔记\u2014\u2014IPC机制","publishedAt":"2016-05-24T11:56:12.924Z","source":"chrome","type":"Android","url":"http://johntsai.xyz/2016/05/23/Android%E5%BC%80%E5%8F%91%E8%89%BA%E6%9C%AF%E6%8E%A2%E7%B4%A2%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0%E2%80%94%E2%80%94IPC%E6%9C%BA%E5%88%B6%EF%BC%88%E4%B8%80%EF%BC%89/","used":true,"who":"Creep"},{"_id":"574265556776590a18d29296","createdAt":"2016-05-23T10:05:09.526Z","desc":"Android 下 RxJava 的 OAuth 扩展","publishedAt":"2016-05-23T10:54:25.890Z","source":"chrome","type":"Android","url":"https://github.com/FuckBoilerplate/RxSocialConnect-Android","used":true,"who":"代码家"},{"_id":"5742622b6776590a1553b0c7","createdAt":"2016-05-23T09:51:39.695Z","desc":"动态加载一个 Service 到应用中，同样采用的是和 Activity 一样的伪装欺骗系统识别的方案。 ","publishedAt":"2016-05-23T10:54:25.890Z","source":"web","type":"Android","url":"http://kymjs.com/code/2016/05/22/01","used":true,"who":"张涛"},{"_id":"57425b236776590a0b0fe26a","createdAt":"2016-05-23T09:21:39.774Z","desc":"是时候学习Android分屏开发了","publishedAt":"2016-05-23T10:54:25.890Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/e6a908d7d5b1","used":true,"who":"于连林"},{"_id":"57425a776776590a18d29294","createdAt":"2016-05-23T09:18:47.13Z","desc":"RxJava化的OnActivityResult\u2014RxActivityResult","publishedAt":"2016-05-23T10:54:25.890Z","source":"chrome","type":"Android","url":"https://github.com/VictorAlbertos/RxActivityResult","used":true,"who":"有时放纵"}]
     */

    private boolean error;
    /**
     * _id : 5742f4236776593b2d517258
     * createdAt : 2016-05-23T20:14:27.784Z
     * desc : Android代码规范利器： Checkstyle
     * publishedAt : 2016-05-24T11:56:12.924Z
     * source : chrome
     * type : Android
     * url : http://droidyue.com/blog/2016/05/22/use-checkstyle-for-better-code-style/
     * used : true
     * who : 蒋朋
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
