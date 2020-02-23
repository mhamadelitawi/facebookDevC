package com.example.facebook.manager;

public interface Database {


   public Boolean connect()  throws Exception;

   public Boolean closeConnection()  throws Exception;


}
