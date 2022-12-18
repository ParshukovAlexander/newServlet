package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.appline.logic.Model;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/search")
public class ServletList extends HttpServlet {

    Model model =Model.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter pw =response.getWriter();

        int id = Integer.parseInt(String.valueOf(request.getReader().readLine()).replace("id=",""));

        if (id==0) {
            pw.print(gson.toJson(model.getFromList()));

            pw.println("</ul>" +
                    "<a href=\"index.jsp\">Домой </a>" + "</html>");

        } else if(id >0) {
            if (id > model.getFromList().size()) {
                pw.println("<html>" + "Такого пользователя нет" +
                        "<ul>" + "<a href=\"index.jsp\">Домой </a>" + "</html>");
            } else {
                pw.print(gson.toJson(model.getFromList().get(id)));
//                pw.println("<html>" + "<h3>Запрошенный пользователь:</h3>" +
//                        "<ul>" +
//                        "<li>Имя: " + model.getFromList().get(id).getName() + "</li>" +
//                        "<li>Фамилия: " + model.getFromList().get(id).getSurname() + "</li>" +
//                        "<li>Зарплата: " + model.getFromList().get(id).getSalary() + "</li>" +
//                        "<ul>" +
//                        "<ul>" + "<a href=\"index.jsp\">Домой </a>" + "</html>");
            }
        }else {
            pw.println("<html>" + "id должен быть больше нуля!" +
                    "<ul>" + "<a href=\"index.jsp\">Домой </a>" + "</html>");
        }
    }
}
