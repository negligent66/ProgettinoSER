package Progettino.Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LettoreCSV {
    List<Datini> records = new ArrayList<>();

    public LettoreCSV(){
        letturaFile();
    }
    

    public void letturaFile(){
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\yi.chen\\Desktop\\ProgettinoSER\\Progettino\\Server\\Mappa-delle-antenne-in-Italia.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Datini dati = new Datini();
                dati.setComune(values[0]);
                dati.setProvincia(values[1]);
                dati.setRegione(values[2]);
                dati.setNome(values[3]);
                dati.setAnno(values[4]);
                dati.setDataOra(values[5]);
                dati.setIdentificatore(values[6]);
                dati.setLongitudine(values[7]);
                dati.setLatitudine(values[8]);
                records.add(dati);
            }
        }catch(IOException e){
            System.out.println("file probabilmente non presente");
            e.printStackTrace();
        }
    }


    public List<Datini> getRecords() {
        return records;
    }
    public List<Datini> ricercaComuni(String comune){
        List<Datini> list = new ArrayList<>();
        for(Datini dati: records){
            if(dati.getComune().equalsIgnoreCase(comune)){
                list.add(dati);
            }
        }
        return list;
    }
    public List<Datini> ricercaProvincia(String provincia){
        List<Datini> list = new ArrayList<>();
        for(Datini dati: records){
            if(dati.getProvincia().equalsIgnoreCase(provincia)){
                list.add(dati);
            }
        }
        return list;
    }
    public List<Datini> ricercaRegione(String regione){
        List<Datini> list = new ArrayList<>();
        for(Datini dati: records){
            if(dati.getRegione().equalsIgnoreCase(regione)){
                list.add(dati);
            }
        }
        return list;
    }
    public List<Datini> ricercaNome(String nome){
        List<Datini> comunini = new ArrayList<>();
        for(Datini dati: records){
            if(dati.getNome().equalsIgnoreCase(nome)){
                comunini.add(dati);
            }
        }
        return comunini;
    }
    public List<Datini> ricercaAnno(String anno){
        List<Datini> list = new ArrayList<>();
        for(Datini dati: records){
            if(dati.getAnno().equalsIgnoreCase(anno)){
                list.add(dati);
            }
        }
        return list;
    }
    public List<Datini> ricercaDataOra(String dataOra, String intervallo1, String intervallo2){
        List<Datini> list = new ArrayList<>();
        for(Datini dati: records){
            if (dataOra != null && !dataOra.isEmpty()){
                if(dati.getDataOra().equalsIgnoreCase(dataOra)){
                    list.add(dati);
                }
            }
            if (intervallo1 != null && !intervallo1.isEmpty() &&intervallo2 != null && !intervallo2.isEmpty()){
                if(dati.getDataOra().compareTo(intervallo1) > 0 && dati.getDataOra().compareTo(intervallo2) < 0){
                    list.add(dati);
                }
            }
        }
        return list;
    }
    public List<Datini> ricercaIdentificatore(String identidicatore){
        List<Datini> list = new ArrayList<>();
        for(Datini dati: records){
            if(dati.getIdentificatore().equalsIgnoreCase(identidicatore)){
                list.add(dati);
            }
        }
        return list;
    }
    public List<Datini> ricercaLongitudine(String longitudine){
        List<Datini> list = new ArrayList<>();
        for(Datini dati: records){
            if(dati.getLongitudine().equalsIgnoreCase(longitudine)){
                list.add(dati);
            }
        }
        return list;
    }
    public List<Datini> ricercaLatitudine(String latitudine){
        List<Datini> list = new ArrayList<>();
        for(Datini dati: records){
            if(dati.getLatitudine().equalsIgnoreCase(latitudine)){
                list.add(dati);
            }
        }
        return list;
    }

}
