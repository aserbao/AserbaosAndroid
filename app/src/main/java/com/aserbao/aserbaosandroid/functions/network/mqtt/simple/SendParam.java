package com.aserbao.aserbaosandroid.functions.network.mqtt.simple;

public class SendParam {

    /**
     * type : 10
     * message : {"messageType":0,"messageId":"15434804048752157302000000009","fromPersonToken":"5976cbabb34b3835187b368703e1c730958309f1","chatId":"7302_46857","t":"15434804048752157302000000009","message":"攻击力咯给你一种米席子营why","toPerson":{"id":46857,"nickname":"Asdf"},"video":"","photo":"","time":"1543480404","lyric":{"id":"1"}}
     */

    private int type;
    private MessageBean message;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public static class MessageBean {
        /**
         * messageType : 0
         * messageId : 15434804048752157302000000009
         * fromPersonToken : 5976cbabb34b3835187b368703e1c730958309f1
         * chatId : 7302_46857
         * t : 15434804048752157302000000009
         * message : 攻击力咯给你一种米席子营why
         * toPerson : {"id":46857,"nickname":"Asdf"}
         * video :
         * photo :
         * time : 1543480404
         * lyric : {"id":"1"}
         */

        private int messageType;
        private String messageId;
        private String fromPersonToken;
        private String chatId;
        private String t;
        private String message;
        private ToPersonBean toPerson;
        private String video;
        private String photo;
        private String time;
        private LyricBean lyric;

        public int getMessageType() {
            return messageType;
        }

        public void setMessageType(int messageType) {
            this.messageType = messageType;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public String getFromPersonToken() {
            return fromPersonToken;
        }

        public void setFromPersonToken(String fromPersonToken) {
            this.fromPersonToken = fromPersonToken;
        }

        public String getChatId() {
            return chatId;
        }

        public void setChatId(String chatId) {
            this.chatId = chatId;
        }

        public String getT() {
            return t;
        }

        public void setT(String t) {
            this.t = t;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public ToPersonBean getToPerson() {
            return toPerson;
        }

        public void setToPerson(ToPersonBean toPerson) {
            this.toPerson = toPerson;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public LyricBean getLyric() {
            return lyric;
        }

        public void setLyric(LyricBean lyric) {
            this.lyric = lyric;
        }

        public static class ToPersonBean {
            /**
             * id : 46857
             * nickname : Asdf
             */

            private int id;
            private String nickname;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }

        public static class LyricBean {
            /**
             * id : 1
             */

            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
