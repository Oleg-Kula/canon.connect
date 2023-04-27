package com.gmail.kulacholeg.canon.connect.util;

import com.gmail.kulacholeg.canon.connect.dto.OperationGetDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PDFCreator {

    public static Document createFromList(List<OperationGetDto> operations){
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("table.pdf")).setInitialLeading(16);
            document.open();
            PdfPTable table = new PdfPTable(2);
            FontSelector selector = new FontSelector();
            BaseFont bf = BaseFont.createFont("src/main/resources/fonts/DejaVuSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bf, 12);
            selector.addFont(font);
            PdfPCell cell = new PdfPCell(selector.process("Відділ"));
            table.addCell(cell);
            cell = new PdfPCell(selector.process("Кількість використаних листів"));
            table.addCell(cell);
            for(OperationGetDto dto : operations){
                table.addCell(dto.getDepartmentName());
                table.addCell(dto.getAllOperations().toString());
            }
            document.add(table);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return document;
    }
}
