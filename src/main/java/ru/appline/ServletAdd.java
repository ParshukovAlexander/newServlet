package ru.appline;

import com.google.gson.*;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/add")
public class ServletAdd extends HttpServlet {

    private AtomicInteger counter =new AtomicInteger(4);

    Model model = Model.getInstance();

//    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html;charset=utf-8");
//        request.setCharacterEncoding("UTF-8");
//        PrintWriter pw =response.getWriter();
//
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        double salary = Double.parseDouble(request.getParameter("salary"));
//
//        User user = new User(name,surname,salary);
//        model.add(user,counter.getAndIncrement());
//
//        pw.print("<html>"+"<h3>Пользователь "+ name+" "+ surname+" с зарплатой= "+ salary+ " добавлен</h3>"+
//        "<a href=\"addUser.html\">Создать нового пользователя </a><br/>"+
//                "<a href=\"index.jsp\">Домой </a>" +
//                "</html>");
//    }

    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        StringBuffer jb = new StringBuffer();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String line;

        try {

            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                if (line.contains("&") && line.contains("=")){
                    line = parserString(line);
                }
                jb.append(line);

            }
        }catch ( Exception e){
            System.out.println("Error");
        }

        JsonObject jobj=gson.fromJson(String.valueOf(jb),JsonObject.class);

        request.setCharacterEncoding("UTF-8");

        String name = jobj.get("name").getAsString();
        String surname = jobj.get("surname").getAsString();
        double salary = Double.parseDouble(jobj.get("salary").getAsString());

        User user = new User(name, surname, salary);
        model.add(user, counter.getAndIncrement());

        response.setContentType("text/html;charset=utf-8");
//
        PrintWriter pw =response.getWriter();

                pw.print("<html>"+"<h3>Пользователь "+ name+" "+ surname+" с зарплатой= "+ salary+ " добавлен</h3>"+
        "<a href=\"addUser.html\">Создать нового пользователя </a><br/>"+
                "<a href=\"index.jsp\">Домой </a>" +
                "</html>");

    }

    private String parserString(String line) {

        StringBuilder builder = new StringBuilder();
        builder.append('{');

        String [] elements = line.split("&");

        for (int i = 0; i < elements.length; i++) {
            String[] splitForElements = elements[i].split("=");
            splitForElements[0]="\""+splitForElements[0]+"\"";
            builder.append(splitForElements[0]);
            builder.append(':');

            if (!splitForElements[0].equals("\"salary\"")) {
                splitForElements[1] = "\"" + splitForElements[1] + "\"";

            }
            builder.append(splitForElements[1]);
           if (i<elements.length-1) builder.append(',');
        }
        builder.append('}');
        return String.valueOf(builder);
    }

}
