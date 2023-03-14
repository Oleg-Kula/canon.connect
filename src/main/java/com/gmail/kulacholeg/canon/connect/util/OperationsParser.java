package com.gmail.kulacholeg.canon.connect.util;

import com.gmail.kulacholeg.canon.connect.dto.OperationSaveDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class OperationsParser {

    public static List<OperationSaveDto> parse(String html) {
        Document doc = Jsoup.parse(html);
        doc.getElementsByClass("ItemOperation").remove();
        //Elements fullTable = doc.getElementsByClass("ItemListComponent");
        Elements table = doc.select("tbody");
        table.select("colgroup").remove();
        Elements rows = table.select("tr");

        List<OperationSaveDto> result = new ArrayList<>();
        long departmentId = 0L;

        for (Element row : rows) {
            Elements cols = row.select("td");
            if (cols.size() == 6) {
                departmentId = 7654321L;
            } else if (cols.size() == 7) {
                departmentId = Long.parseLong(cols.get(0).select("a").attr("href")
                        .replaceAll("\\D+", ""));
            }
            OperationSaveDto dto = OperationSaveDto.builder()
                    .copyBW(Integer.parseInt(cols.get(1).text()))
                    .printBW(Integer.parseInt(cols.get(2).text()))
                    .scanBW(Integer.parseInt(cols.get(3).text()))
                    .scanColor(Integer.parseInt(cols.get(4).text()))
                    .date(null)
                    .departmentId(departmentId)
                    .build();
            result.add(dto);
        }
        return result;
    }
}
