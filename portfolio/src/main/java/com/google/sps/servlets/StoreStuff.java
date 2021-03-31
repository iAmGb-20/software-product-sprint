// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.jsoup.Jsoup;
//import org.jsoup.safety.Whitelist;

/** Servlet responsible for creating new tasks. */
@WebServlet("/upload")
public class StoreStuff extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException 
  {
    String userString = getParameter(request, "message", "");    
    //String title = Jsoup.clean(request.getParameter("title"), Whitelist.none());
    long timestamp = System.currentTimeMillis();

    
    // Redirect back to the HTML page.
    //response.sendRedirect("/index.html");
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService(); //get the instance of the Datastore class
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Task"); //creates a keyFactory with a kind called "Task" and the name keyFactory
    FullEntity taskEntity =
        Entity.newBuilder(keyFactory.newKey()) //give a key to the Entity
            .set("comment", userString)//set the associated attributes of the key (values)
            .set("timestamp", timestamp)
            //.set("author", taskEntity.getKey().getNamespace())
            .build();
    datastore.put(taskEntity);//store this entity in datastore

    response.sendRedirect("/index.html");
  }

  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) 
    {
      return defaultValue;
    }
    return value;
  }
}
