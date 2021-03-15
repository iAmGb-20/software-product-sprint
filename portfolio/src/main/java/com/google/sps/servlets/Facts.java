package com.google.sps.servlets;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */
@WebServlet("/facts")
public class Facts extends HttpServlet { 

    ArrayList<String> array = new ArrayList<String>(Arrays.asList("I used to play chess in highschool.", "I play soccer since I am 4 years old.", "I'm always smiling."));

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String json = convertToJsonUsingGson(array);
        response.setContentType("application/json;");
        response.getWriter().println(json);
    }

    public String convertToJsonUsingGson(ArrayList array) {
        Gson gson = new Gson();
        String json = gson.toJson(array);
        return json;
    }
}