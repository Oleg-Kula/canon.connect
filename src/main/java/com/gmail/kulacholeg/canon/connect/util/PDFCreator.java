package com.gmail.kulacholeg.canon.connect.util;

import com.gmail.kulacholeg.canon.connect.dto.OperationGetDto;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class PDFCreator {

    public static Document createFromList(List<OperationGetDto> operations){
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("table.pdf"));
            document.open();
            PdfPTable table = new PdfPTable(2);
            table.addCell("Department Name");
            table.addCell("All Operations");
            for(OperationGetDto dto : operations){
                table.addCell(dto.getDepartmentName());
                table.addCell(dto.getAllOperations().toString());
            }
            document.add(table);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return document;
    }
}
