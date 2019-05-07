package com.allysa.volleyjsonwithrecyclerview;

class Kontak {

        private String Id;
        private String name;
        private String email;
        private String addr;
        private String noHp;


        public Kontak (String id, String name, String email, String addr, String noHp) {
            this.Id = id;
            this.name= name;
            this.email = email;
            this.addr = addr;
            this.noHp = noHp;

        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            this.Id = Id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }
        public String getNoHp() {
            return noHp;
        }

        public void setNoHp(String noHp) {
            this.noHp = noHp;
        }

}
