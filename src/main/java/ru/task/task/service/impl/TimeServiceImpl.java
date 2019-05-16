package ru.task.task.service.impl;

import org.springframework.stereotype.Service;
import ru.task.task.service.TimeService;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Service("TimeService")
public class TimeServiceImpl implements TimeService {

    private TimeZone timeZone = TimeZone.getTimeZone("UTC");
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset

    @PostConstruct
    private void setTimeZone() {
        dateFormat.setTimeZone(timeZone);
    }

    /**
     * Get current timestamp in ISO 8601 format.
     *
     * @return current timestamp in ISO 8601 format.
     */
    @Override
    public String getTimestamp() {
        return dateFormat.format(new Date());
    }
}
