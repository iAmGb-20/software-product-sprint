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
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.sps.data.Comments;
import java.util.List;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** Servlet responsible for creating new tasks. */
@WebServlet("/upload")
public class StoreStuff extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException 
  {
      
    String userString = getParameter(request, "message", "");    
    long timestamp = System.currentTimeMillis();

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService(); //get the instance of the Datastore class
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Comments"); //creates a keyFactory with a kind called "Task" and the name keyFactory
    FullEntity taskEntity =
        Entity.newBuilder(keyFactory.newKey()) //give a key to the Entity
            .set("comment", userString)//set the associated attributes of the key (values)
            .set("timestamp", timestamp)
            //.set("author", taskEntity.getKey().getNamespace())
            .build();
    datastore.put(taskEntity);//store this entity in datastore

    response.sendRedirect("/index.html");
  }

  //load data from the "contact me" form
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException 
  {
    //create an instance of datastore to use
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

    //create a query instance to use
    Query<Entity> query =
        Query.newEntityQueryBuilder().setKind("Comments").setOrderBy(OrderBy.desc("timestamp")).build();
    QueryResults<Entity> results = datastore.run(query);

    List<Comments> messages = new ArrayList<>();//initializes a list of comments
    while (results.hasNext()) 
    {
      Entity entity = results.next();

      long id = entity.getKey().getId();
      String comment = entity.getString("comment");
      long timestamp = entity.getLong("timestamp");
      Date day = new Date(timestamp);
      Comments c = new Comments(comment, id, day.toString());
      messages.add(c);
    }
    String json = convertToJson(messages);
    response.setContentType("application/json;");
    response.getWriter().println(json);

  }//iterate through the stuff in datastore, extract properties and save them in Comments..
  //add these to list after that
  
  //helper function to convert to Json
  private String convertToJson(List<Comments> sample)
  {
    Gson gson = new Gson();
    String json = gson.toJson(sample);
    return json;
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
