package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {

    Model model = Model.getInstance();

    protected  void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        StringBuffer jb = new StringBuffer();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw =response.getWriter();

        String line;

        try {

            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {

                jb.append(line);

            }
        }catch ( Exception e){
            System.out.println("Error");
        }

        JsonObject jobj=gson.fromJson(String.valueOf(jb),JsonObject.class);

        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(String.valueOf(jobj.get("id")));
        if (id<=model.getFromList().size() && id>0 && model.getFromList().containsKey(id)){
            model.getFromList().remove(id);
            pw.print("Удаление прошло успешно!");
        }else {
            pw.print("Не найденно");
        }


    }
}
