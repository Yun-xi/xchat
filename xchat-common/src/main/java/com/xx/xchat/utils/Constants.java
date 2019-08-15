package com.xx.xchat.utils;

/**
 * 常量
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-07-30 16:03
 */
public class Constants {

    public class RetCode {
        public final static int SUCCESS = 0;
        public final static int FAIL = -1;
    }

    public class FailCode {

    }

    public class Shiro {
        /**  加密算法 */
        public final static String hashAlgorithmName = "SHA-256";
        /**  循环次数 */
        public final static int hashIterations = 16;
        /**  存放权限的前缀 */
        public final static String CACHE_KEY = "shiro:cache:";
        /** 存放用户的前缀 */
        public final static String SESSION_KEY = "shiro:session:";
        /** 存放用户key的有效期 */
        public final static int EXPIRE = 1800;

        /**
         * 存放前缀的key，会将User的name拿出来和上面的CACHE_KEY拼起来
         * 如：shiro:cache:com.xx.xchat.shiro.ShiroRealm.authorizationCache:admin
         * 这个就作为一个完整的key。
         * principalIdFieldName默认值为id，会被反射调用获取到相应的值
         */
        public final static String principalIdFieldName = "name";

    }
}
