package com.blaze.network.save.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JAXBDateFromater extends XmlAdapter<String, Date> {
    private static final String formater="EEE MMM dd hh:mm:ss yyyy";
    @Override
    public Date unmarshal(String v) throws Exception {
        if (v == null) {
            return null;
        }
        DateFormat format = new SimpleDateFormat(formater, Locale.US);
        Date foreignDate = format.parse(v);
        System.out.println("foreignDate = " + foreignDate);

//        format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String localDateString = format.format(foreignDate);
//        Date localDate = format.parse(localDateString);
//        return localDate;
        return foreignDate;
    }
    @Override
    public String marshal(Date v) throws Exception {
        DateFormat format = new SimpleDateFormat(formater,Locale.US);
        return format.format(v);
    }

}