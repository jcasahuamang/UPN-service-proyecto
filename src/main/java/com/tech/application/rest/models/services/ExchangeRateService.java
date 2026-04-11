package com.tech.application.rest.models.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ExchangeRateService {

    public String getExchangeRate() {
        String url = "https://www.sbs.gob.pe/app/pp/sistip_portal/paginas/publicacion/tipocambiopromedio.aspx";
//        System.out.println(url);
        
        try {
            // Fetch the webpage
            Document document = Jsoup.connect(url).get();

            System.out.println(document.text());
            // Locate the table or element containing the exchange rate
            // Use the browser's developer tools to find the correct selector
//            Element table = document.select("rgMasterTable").first(); // Replace with the actual table ID or class
            Element table = document.select("rgMasterTable").first(); // Replace with the actual table ID or class

            //return table.text();

            if (table != null) {
                String tc = table.select("tbody").select("ctl00_cphContent_rgTipoCambio_ctl00__0").select("APLI_fila2").get(0).text();
                return "Currency: " + tc ;

                /*
                // Extract the desired row or cell
                Elements rows = table.select("tr");
                for (Element row : rows) {
                    Elements cells = row.select("td");
                    if (cells.size() > 1) { // Ensure there are enough columns
                        String currency = cells.get(0).text(); // Example: First column for currency
                        String rate = cells.get(1).text();     // Example: Second column for rate
                        return "Currency: " + currency + ", Rate: " + rate;
                    }
                }
                */
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Exchange rate not found.";
    }
}
