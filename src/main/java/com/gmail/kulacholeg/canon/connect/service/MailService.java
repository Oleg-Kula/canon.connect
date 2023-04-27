package com.gmail.kulacholeg.canon.connect.service;

import com.gmail.kulacholeg.canon.connect.dto.OperationGetDto;
import com.gmail.kulacholeg.canon.connect.util.PDFCreator;
import com.itextpdf.text.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class MailService {

    private final GetOperationsService operationsService;

    @Autowired
    public MailService(GetOperationsService operationsService) {
        this.operationsService = operationsService;
    }

    @Scheduled(fixedRate = 999999999)
    public void sendStatistic(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date start = new Date(calendar.getTimeInMillis());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date end = new Date(calendar.getTimeInMillis());

        List<OperationGetDto> operations = operationsService.getOperationsBetweenDates(start.toString(), end.toString());

        Document pdf = PDFCreator.createFromList(operations);



    }
}
