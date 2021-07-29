package ConsomiTounsi.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;





/*@Service
public class PdfServiceImpl implements PdfService {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    OrderRepository orderRepository;

    @Override
    public String toPDF(Long idPayment)
    {
        String URL_file = null;

        Payment payment = paymentRepository.findById(idPayment).get();


        Date d = new Date();
        SimpleDateFormat formater = null;
        formater = new SimpleDateFormat("yyyyMMdd_HHmmss");


        try {
            try {
                try {
                    try {

                        URL_file = "C:\\Users\\mouad\\payment\\" + idPayment + "_" + formater.format(d)
                                + ".pdf";

                        OutputStream file = new FileOutputStream(new File(URL_file));


                        Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.GRAY);
                        Font catFont15B = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.BLACK);
                        Font subFontPara13B = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD, BaseColor.BLACK);
                        Font subFontPara13N = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
                        Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);

                        Document my_pdf_report = new Document();
                        PdfWriter.getInstance(my_pdf_report, file);
                        my_pdf_report.open();

                        // Dateeee
                        DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
                        String dt = df.format(payment.getDate());
                        Paragraph dateStr = new Paragraph("Date: " + dt, smallBold);
                        dateStr.setAlignment(Element.ALIGN_RIGHT);


                        Paragraph description = new Paragraph(payment.getDescription(), subFont);
                        description.setAlignment(Element.ALIGN_LEFT);

                        // espaceee
                        Paragraph espace = new Paragraph(" ", subFont);
                        espace.setAlignment(Element.ALIGN_LEFT);

                        // para1
                        Paragraph para1 = new Paragraph("Your Payment Code is " + payment.getCustomerId(), catFont15B);
                        para1.setAlignment(Element.ALIGN_LEFT);
                        Paragraph para2 = new Paragraph(
                                "Consomi Tounsi Thank you for You Trust ", subFontPara13N);
                        para2.setAlignment(Element.ALIGN_LEFT);

                        // espaceee



                        // titreee
                        Paragraph titre1 = new Paragraph("Your Payment Information", catFont15B);
                        titre1.setAlignment(Element.ALIGN_CENTER);

                        // espaceee

                        espace.setAlignment(Element.ALIGN_LEFT);

                        // table declaration
                        PdfPTable table = new PdfPTable(3);
                        table.setWidthPercentage(90);

                        Order order =orderRepository.findById(1L).get();
                        //Ligne 1

                        Paragraph para4 = new Paragraph("Your EMAIL IS  :\r\n" + payment.getEmail(), subFontPara13N);
                        para4.setAlignment(Element.ALIGN_LEFT);

                        Paragraph para3 = new Paragraph("Your Order weight is :\r\n " +"14g", subFontPara13N);
                        para3.setAlignment(Element.ALIGN_LEFT);

                        //Ligne 3
                        Paragraph para5 = new Paragraph("Price:\r\n" + 71+"Dt", subFontPara13N);
                        para5.setAlignment(Element.ALIGN_LEFT);

                        //break
                        Paragraph separator = new Paragraph("_____________________________________________________________________",
                                catFont15B);
                        separator.setAlignment(Element.ALIGN_CENTER);
                        /*
                        // titreee
                        Paragraph titre2 = new Paragraph("BANK", catFont15B);
                        titre2.setAlignment(Element.ALIGN_CENTER);

                        // espaceee

                        espace.setAlignment(Element.ALIGN_LEFT);
                        //table 2
                        PdfPTable table1 = new PdfPTable(5);
                        table1.setWidthPercentage(90);

                        //ligne1
                        PdfPCell Celbankname = new PdfPCell(new Phrase("Bank :\r\n" + simulation.getBank().getNamebank(), subFontPara13N));
                        Celbankname.setBorder(Rectangle.NO_BORDER);
                        Celbankname.setBackgroundColor(BaseColor.WHITE);
                        table1.addCell(Celbankname);

                        //ligne2
                        PdfPCell Celagentname = new PdfPCell(new Phrase("Agent :\r\n" + simulation.getAgent().getFirstname() + "   " +simulation.getAgent().getLastname(),subFontPara13N));
                        Celagentname.setBorder(Rectangle.NO_BORDER);
                        Celagentname.setBackgroundColor(BaseColor.WHITE);
                        table1.addCell(Celagentname);

                        //ligne3
                        PdfPCell Celbankadd = new PdfPCell(new Phrase("Bank Address :\r\n" + simulation.getBank().getAdressbank() ,subFontPara13N));
                        Celbankadd.setBorder(Rectangle.NO_BORDER);
                        Celbankadd.setBackgroundColor(BaseColor.WHITE);
                        table1.addCell(Celbankadd);
                        //ligne4
                        PdfPCell Celtm = new PdfPCell(new Phrase("Avarage Margin Rate :\r\n" + simulation.getBank().getTaux() ,subFontPara13N));
                        Celtm.setBorder(Rectangle.NO_BORDER);
                        Celtm.setBackgroundColor(BaseColor.WHITE);
                        table1.addCell(Celtm);

                        //ligne5
                        PdfPCell Celmarg = new PdfPCell(new Phrase("Interet Bank Margin:\r\n" + simulation.getBank().getMargeInteretbank() , subFontPara13N));
                        Celmarg.setBorder(Rectangle.NO_BORDER);
                        Celmarg.setBackgroundColor(BaseColor.WHITE);
                        table1.addCell(Celmarg);

                        //break
                        Paragraph separator2 = new Paragraph("_____________________________________________________________________\r\n",catFont15B);

                        espace.setAlignment(Element.ALIGN_LEFT);

                        //title
                        Paragraph titre21 = new Paragraph("ESTIMATION", catFont15B);
                        titre21.setAlignment(Element.ALIGN_CENTER);

                        espace.setAlignment(Element.ALIGN_LEFT);
//table 3
                        PdfPTable table3 = new PdfPTable(4);
                        table3.setWidthPercentage(100);

                        //ligne6
                        PdfPCell Celtaux = new PdfPCell(new Phrase("Rate :\r\n" + simulation.getTaux() , subFontPara13N));
                        Celtaux.setBorder(Rectangle.NO_BORDER);
                        Celtaux.setBackgroundColor(BaseColor.WHITE);
                        table3.addCell(Celtaux);
                        //ligne7
                        PdfPCell Celmtaux = new PdfPCell(new Phrase("Monthly rate :\r\n" + simulation.getMensuel() , subFontPara13N));
                        Celmtaux.setBorder(Rectangle.NO_BORDER);
                        Celmtaux.setBackgroundColor(BaseColor.WHITE);
                        table3.addCell(Celmtaux);
                        //ligne8
                        PdfPCell Celcapacity = new PdfPCell(new Phrase("Repayment capacity :\r\n" + simulation.getCapaciteRembouresement() , subFontPara13N));
                        Celcapacity.setBorder(Rectangle.NO_BORDER);
                        Celcapacity.setBackgroundColor(BaseColor.WHITE);
                        table3.addCell(Celcapacity);
                        //ligne9
                        PdfPCell Celmp= new PdfPCell(new Phrase("Monthly payment :\r\n" + simulation.getCapaciteRembouresement() , subFontPara13N));
                        Celmp.setBorder(Rectangle.NO_BORDER);
                        Celmp.setBackgroundColor(BaseColor.WHITE);
                        table3.addCell(Celmp);

                        espace.setAlignment(Element.ALIGN_LEFT);
                        //lligne10
                        PdfPCell CelBI= new PdfPCell(new Phrase("Bank interest :\r\n" + simulation.getInteret() , subFontPara13N));
                        CelBI.setBorder(Rectangle.NO_BORDER);
                        CelBI.setBackgroundColor(BaseColor.WHITE);
                        table3.addCell(CelBI);
                        //lligne11
                        PdfPCell Celp= new PdfPCell(new Phrase("princper :\r\n" + simulation.getPrincipale() , subFontPara13N));
                        Celp.setBorder(Rectangle.NO_BORDER);
                        Celp.setBackgroundColor(BaseColor.WHITE);
                        table3.addCell(Celp);
                        //lligne12
                        PdfPCell CelRA= new PdfPCell(new Phrase("Refund Amount :\r\n" + simulation.getMontantRemb() , subFontPara13N));
                        CelRA.setBorder(Rectangle.NO_BORDER);
                        CelRA.setBackgroundColor(BaseColor.WHITE);
                        table3.addCell(CelRA);
                        //lligne13
                        PdfPCell CelBIT= new PdfPCell(new Phrase("Bank Total interest :\r\n" + simulation.getInteretall() , subFontPara13N));
                        CelBIT.setBorder(Rectangle.NO_BORDER);
                        CelBIT.setBackgroundColor(BaseColor.WHITE);
                        table3.addCell(CelBIT);

                         */

                     /*   Paragraph titrefin = new Paragraph("THANK YOU FOR\r\nUSING OUR SERVICE.", subFont);
                        titrefin.setAlignment(Element.ALIGN_LEFT);


                        // fiin P1
                        Paragraph finPage1 = new Paragraph(" Page 1/1", smallBold);
                        finPage1.setAlignment(Element.ALIGN_RIGHT);



                        my_pdf_report.add(description);
                        my_pdf_report.add(dateStr);

                        my_pdf_report.add(espace);

                        my_pdf_report.add(para1);
                        my_pdf_report.add(para2);
                        my_pdf_report.add(espace);


                        my_pdf_report.add(separator);
                        my_pdf_report.add(espace);
                        my_pdf_report.add(titre1);

                        my_pdf_report.add(espace);
                        my_pdf_report.add(espace);
                        my_pdf_report.add(para3);
                        my_pdf_report.add(espace);
                        my_pdf_report.add(para5);
                        my_pdf_report.add(espace);
                        my_pdf_report.add(para4);


                        /*
                        my_pdf_report.add(separator);
                        my_pdf_report.add(espace);
                        my_pdf_report.add(titre2);


                        my_pdf_report.add(espace);
                        my_pdf_report.add(table1);
                        my_pdf_report.add(espace);


                        my_pdf_report.add(separator);
                        my_pdf_report.add(espace);
                        my_pdf_report.add(titre21);


                        my_pdf_report.add(espace);
                        my_pdf_report.add(table3);

                        my_pdf_report.add(espace);
                        my_pdf_report.add(espace);

                        my_pdf_report.add(espace);
                        my_pdf_report.add(titrefin);
                        my_pdf_report.add(espace);
                        my_pdf_report.add(espace);
                        my_pdf_report.add(espace);
                        my_pdf_report.add(espace);
                        my_pdf_report.add(finPage1);

                         */

                  /*      my_pdf_report.close();
                        file.close();

                        Runtime.getRuntime()
                                .exec("rundll32 url.dll,FileProtocolHandlerC:\\Users\\mouad\\payment\\"
                                        + idPayment + "_" + formater.format(d) + ".pdf");
                        //p.waitFor(2, TimeUnit.SECONDS);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } catch (DocumentException e) {
                    e.printStackTrace();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "" + idPayment + "_" + formater.format(d) + ".pdf";
    }*/
/*}*/