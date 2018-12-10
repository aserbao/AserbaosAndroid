package com.aserbao.aserbaosandroid.functions.network.mqtt.simple;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class MQTTManager {
    private static final String TAG = "MQTTManager";
        public static final String SERVER_HOST = "tcp://52.80.116.245:1883";
        private String clientid = "2df8aabfb8b6088953664f413a446bbc";
        private static MQTTManager mqttManager=null;
        private MqttClient client;
        private MqttConnectOptions options;
        private Context mContext;

        private MessageHandlerCallBack callBack;
        private MQTTManager(Context context){
            mContext = context;
            clientid+=MqttClient.generateClientId();
        }

        /**
         * 获取一个MQTTManager单例
         * @param context
         * @return 返回一个MQTTManager的实例对象
         */
        public static MQTTManager getInstance(Context context) {
            Log.d(TAG,"mqttManager="+mqttManager);
            if (mqttManager==null) {
                mqttManager=new MQTTManager(context);
                synchronized (Object.class) {
                    Log.d(TAG,"synchronized mqttManager="+mqttManager);
                    if (mqttManager!=null) {
                        return mqttManager;
                    }
                }
            }else {
                Log.d(TAG,"else mqttManager="+mqttManager);
                return mqttManager;
            }
            return null;
        }
        /**
         * 连接服务器
         */
        public void connect(){
            Log.d(TAG,"开始连接MQtt");
            try {
                // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存    
                client = new MqttClient(SERVER_HOST, "2df8aabfb8b6088953664f413a446bbc", new MemoryPersistence());
                // MQTT的连接设置    
                options = new MqttConnectOptions();
                // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接    
              options.setCleanSession(true);
                // 设置连接的用户名    
                options.setUserName("7302");
                // 设置连接的密码    
                options.setPassword("64ec6f32366ccb80f0dacc804546d62e623e4b72".toCharArray());
                // 设置超时时间 单位为秒    
                options.setConnectionTimeout(30);
                // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制    
                options.setKeepAliveInterval(30);
                // 设置回调    
//              MqttTopic topic = client.getTopic(TOPIC);    
                //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息      
//              options.setWill(topic, "close".getBytes(), 2, true);
                SSLSocketFactory sslSocketFactory = null;
               /* try {
                    sslSocketFactory = sslContextFromStream(mContext.getAssets().open("server.pem")).getSocketFactory();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                options.setSocketFactory(sslSocketFactory);*/
                client.setCallback(new PushCallback());
                client.connect(options);
                Log.d(TAG,"ClientId="+client.getClientId());
            } catch (MqttException e) {
                e.printStackTrace();
                Log.e(TAG, "connect: " + e );
            }
        }

        /**
         * 订阅消息
         * @param topic 订阅消息的主题
         */
        public void subscribeMsg(String topic,int qos){
            if (client!=null) {
                int[] Qos  = {qos};
                String[] topic1 = {topic};
                try {
                    client.subscribe(topic1, Qos);
                    Log.d(TAG,"开始订阅topic="+topic);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 发布消息
         * @param topic 发布消息主题
         * @param msg 消息体
         * @param isRetained 是否为保留消息
         */
        public void publish(String topic,String msg,boolean isRetained,int qos) {

            try {
                if (client!=null) {
                    MqttMessage message = new MqttMessage();
                    message.setQos(qos);
                    message.setRetained(isRetained);
                    message.setPayload(msg.getBytes());
                    client.publish(topic, message);
                    Log.d(TAG,"topic="+topic+"--msg="+msg+"--isRetained"+isRetained);
                }
            } catch (MqttPersistenceException e) {
                e.printStackTrace();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
        int count=0;
        /**
         * 发布和订阅消息的回调
         *
         */
        public class PushCallback implements MqttCallback {

            public void connectionLost(Throwable cause) {
                Log.e(TAG, "connectionLost: " + cause );
                if (count<5) {
                    count++;//5次重连
                    Log.d(TAG,"断开连接，重新连接"+count+"次"+cause);
                    try {
                        client.close();
                        connect();
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }
            }
            /**
             * 发布消息的回调     
             */
            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                //publish后会执行到这里  
                Log.d(TAG,"发布消息成功的回调"+token.isComplete());
            }

            /**
             * 接收消息的回调方法
             */
            @Override
            public void messageArrived(final String topicName, final MqttMessage message)
                    throws Exception {
                //subscribe后得到的消息会执行到这里面    
                Log.d(TAG,"接收消息=="+new String(message.getPayload()));
                if (callBack!=null) {
                    callBack.messageSuccess(topicName,new String(message.getPayload()));
                }
            }

        }
        /**
         *  设置接收消息的回调方法
         * @param callBack
         */
        public void setMessageHandlerCallBack(MessageHandlerCallBack callBack){
            this.callBack = callBack;
        }
        public MessageHandlerCallBack getMessageHandlerCallBack(){
            if (callBack!=null) {
                return callBack;
            }
            return null;
        }
        /**
         * 断开链接
         */
        public void disconnect(){
            if (client!=null&&client.isConnected()) {
                try {
                    client.disconnect();
                    mqttManager=null;
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        }
        /**
         * 释放资源
         */
        public void release(){
            if (mqttManager!=null) {
                mqttManager=null;
            }
        }
        /**
         *  判断服务是否连接
         * @return
         */
        public boolean isConnected(){
            if (client!=null) {
                return client.isConnected();
            }
            return false;
        }

    public SSLContext sslContextFromStream(InputStream inputStream) throws Exception {

        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Certificate certificate = certificateFactory.generateCertificate(inputStream);

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", certificate);

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

        return sslContext;
    }
}