package com.gmail.kulacholeg.canon.connect.util;

import com.gmail.kulacholeg.canon.connect.dto.OperationSaveDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class OperationsParser {

    public static List<OperationSaveDto> parse(String html) {
        Document doc = Jsoup.parse(html);
        doc.getElementsByClass("ItemOperation").remove();
        Elements table = doc.getElementsByClass("ItemListComponent");
        table = table.select("tbody");
        table.select("colgroup").remove();
        Elements rows = table.select("tr");

        List<OperationSaveDto> result = new ArrayList<>();
        int departmentCode = 0;
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        long date = zdt.toInstant().toEpochMilli();

        //REFACTOR WITH ITERATOR!!!
        int i = 1;
        for (Element row : rows) {
            Elements cols = row.select("td");
            if (cols.size() == 6) {
                departmentCode = 7654321;
            } else if (cols.size() == 7) {
                departmentCode = Integer.parseInt(cols.get(0).select("a").attr("href")
                        .replaceAll("\\D+", ""));
            }
            if (i++ == rows.size()) departmentCode = -1;

            OperationSaveDto dto = OperationSaveDto.builder()
                    .allOperations(Integer.parseInt(cols.get(1).text()))
                    .copyBW(Integer.parseInt(cols.get(2).text()))
                    .printBW(Integer.parseInt(cols.get(3).text()))
                    .scanBW(Integer.parseInt(cols.get(4).text()))
                    .scanColor(Integer.parseInt(cols.get(5).text()))
                    .date(new Date(date))
                    .departmentCode(departmentCode)
                    .build();
            result.add(dto);
        }
        return result;
    }
}
