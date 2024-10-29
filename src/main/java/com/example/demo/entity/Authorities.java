package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Authorities {

        @Id
        private String username;
        private String authority;

        public Authorities(String username, String authority) {
            this.username = username;
            this.authority = authority;
        }

        public Authorities(){

        }

        @Override
        public String toString() {
            return "Authorities{" +
                    "username='" + username + '\'' +
                    ", authority='" + authority + '\'' +
                    '}';
        }

        public String getAuthority() {
            return authority;
        }

        public void setAuthority(String authority) {
            this.authority = authority;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
}
