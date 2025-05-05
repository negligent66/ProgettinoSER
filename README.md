# PROGETTINO DI SISTEMI E RETI

L'utilizzo di questo programma cambia in base a quale client si desidera utilizzare, quindi seguite le istruzioni del client del quale siete interessati a utilizzare.

## ISTRUZIONI GENERALI (necessario per l'utilizzo di entrambi i client)

1- Scaricare la repository con il comando "git clone <URL_repo>" sul terminale.  
2- Aprire il progetto con un IDE.  
3- Aprire il file del server seguendo il percorso: ProgettinoSER/Progettino/Server/Serverino.java.  
4- Eseguire il server.  

## CLIENT TESTUALE

Completato il passaggio generale si può continuare seguendo le istruzioni su come usere il client simil terminale:  
1- Aprire il file del del client seguendo il percorso: ProgettinoSER/Progettino/Client/Clientino.java.  
2- Eseguire il client **(N.B. il server deve essere avviato!!)**.  
3- L'utente dovrà scrivete i comandi da inviare al server sulla console del compilatore.  
4- Ci sarà prima di tutto il bisogno di inserire l'indirizzo IP e la porta al quale ci si deve connettere, se si lasciano i 2 campi vuoti essa metterà di default 127.0.0.1:1050.  
5- Dopodichè l'utente potra utilizzare il programma inserendo i comandi testuali.

## CLIENT GUI (GRAPHICAL USER INTERFACE)

completato il passaggio generale si può continuare seguendo le istruzioni su come usere il client GUI:  
1- Aprire il file del del client seguendo il percorso: ProgettinoSER/Progettino/Client/ClientinoGUI.java.  
2- Eseguire il client **(N.B. il server deve essere avviato!!)**.  
3- Sia aprirà una finestra con una serie di bottoni iniziali.  
4- Prima del bottone **CONNETTI** ci sono 2 caselle di testo, il primo per l'IP e il secondo per la porta, di default sono impostati a 127.0.0.1 e 1050.  
5- Il bottone **DISCONNETTI** è l'equivalente dell'END che si inserisce su terminale, essa termina la connessione.  
6- Accanto alla scritta **COMANDO**  si trova un menu a tendina con tutti i comandi disponibili.  
7- Dopo aver selezionato un comando, nel campo VALORE compariranno i valori possibili da associare (in ordine alfabetico), validi solo per quel comando.  
8- Per il comando **get_all** non è richiesto alcun valore. Per **get_row**, invece, il valore deve essere inserito manualmente come testo.  

### Funzionamento dell'applicazione
L'applicazione è composta da tre componenti principali:  

- Serverino.java (lato server)  
- Clientino.java (client da terminale)  
- ClientinoGUI.java (client con interfaccia grafica)  

**Il server si occupa di:**  

- Caricare un file CSV contenente dati di antenne.  
- Gestire le richieste in ingresso da uno o più client connessi.  
- Processare i comandi testuali ricevuti ed elaborare una risposta.  
- Inviare la risposta al client connesso tramite socket.  

**Il client, a seconda della versione (testuale o grafica), permette all'utente di:** 

- Connettersi al server specificando IP e porta.  
- Inviare comandi testuali (o tramite interfaccia grafica) al server.  
- Ricevere e visualizzare la risposta testuale contenente i dati filtrati o ordinati.

### Scelte progettuali

- E' stato scelto un protocollo di comunicazione semplice costituito da una parola chiave seguito dal valore
- E' stato scelto un socket TCP per la connessione e comunicazione tra server e client  
- L'elaborazione dei dati del file CSV è contenuta nel server

### Protocollo di comunicazione

**get_all**                     — restituisce tutte le righe del file  
**get_row `<numero>`**           — restituisce la riga specificata  
**comune `<nome>`**              — filtra per comune  
**provincia `<nome>`**           — filtra per provincia  
**regione `<nome>`**             — filtra per regione  
**nome `<nome>`**                — filtra per nome antenna  
**anno `<anno>`**                — filtra per anno  
**identificatore `<id>`**        — filtra per identificatore  
**longitudine `<valore>`**       — filtra per longitudine  
**latitudine `<valore>`**        — filtra per latitudine  
**sort_by `<campo>`**            — ordina per il campo scelto  
**help**                       — mostra l’elenco dei comandi  
**END**                        — termina la connessione
