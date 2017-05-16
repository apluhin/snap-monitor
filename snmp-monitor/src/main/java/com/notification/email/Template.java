package com.notification.email;

import com.entity.DeviceEntity;

import java.util.List;

public class Template {

    private static String template = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "  <title>Bootstrap Example</title>\n" +
            "  <meta charset=\"utf-8\">\n" +
            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
            "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n" +
            "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "<div class=\"container\">\n" +
            "  <p> Следующие устройства не доступны для мониторинга </p>       \n" +
            "  <table class=\"table table-striped\">\n" +
            "    <thead>\n" +
            "      <tr>\n" +
            "        <th>Устройство</th>\n" +
            "        <th>Адрес</th>\n" +
            "      </tr>\n" +
            "    </thead>\n" +
            "    <tbody>\n" +
            "    {#body} " +
            "    </tbody>\n" +
            "  </table>\n" +
            "</div>\n" +
            "\n" +
            "</body>\n" +
            "</html>";


    private String getGenerated(String body) {
        return template.replace("{#body}", body);
    }


    public String generateMessage(List<DeviceEntity> devices) {
        StringBuilder builder = new StringBuilder();

        for (DeviceEntity device : devices) {
            builder.append("<tr>");
            builder.append("<td>").append(device.getName()).append("</td>").append("<td>").append(device.getAddress()).append("</td>");
            builder.append("</tr>");
        }
        return getGenerated(builder.toString());

    }
}
