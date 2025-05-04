# PROGETTINO DI SISTEMI E RETI

L'utilizzo di questo programma cambia da in base a quale client si desidera utilizzare, quindi seguite le istruzioni del client del quale siete interessati a utilizzare.

## ISTRUZIONI GENERALI

1- scaricare la repository con il comando "git clone <URL_repo>" sul terminale  
2- aprire il progetto con un compilatore  
3- aprire il file del server seguendo il percorso: ProgettinoSER/Progettino/Server/Serverino.java  
4- cliccare sul run del compilatore per avviare il server  

## CLIENT SIMIL TERMINALE 

completato il passaggio generale si può continuare seguendo le istruzioni su come usere il client simil terminale:  
1- aprire il file del del client seguendo il percorso: ProgettinoSER/Progettino/Client/Clientino.java  
2- cliccare sul run del compilatore per avviare il client **(N.B. il server deve essere avviato!!)**  
3- l'utente dovrà scrivete i comandi da inviare al server sulla console del compilatore  
4- ci sarà prima di tutto il bisogno di inserire l'indirizzo IP e la porta al quale ci si deve connettere, se si lasciano i 2 campi vuoti essa metterà di default 127.0.0.1:1050  
5- dopodichè l'utente potra utilizzare il programma inserendo i comandi presenti in questa lista:  
**get_all** — restituisce tutte le righe del file  
**get_row <numero>** — restituisce la riga specificata  
**comune <nome>** — filtra per comune  
**provincia <nome>** — filtra per provincia  
**regione <nome>** — filtra per regione  
**nome <nome>** — filtra per nome antenna  
**anno <anno>** — filtra per anno  
**identificatore <id>** — filtra per identificatore  
**longitudine <valore>** — filtra per longitudine  
**latitudine <valore>** — filtra per latitudine  
**sort_by <campo>** — ordina per il campo scelto  
**help** — mostra l’elenco dei comandi  
**END** — termina la connessione  

## CLIENT GUI (GRAPHICAL USER INTERFACE)

completato il passaggio generale si può continuare seguendo le istruzioni su come usere il client GUI:  
1- aprire il file del del client seguendo il percorso: ProgettinoSER/Progettino/Client/ClientinoGUI.java  
2- cliccare sul run del compilatore per avviare il client **(N.B. il server deve essere avviato!!)**  
3- sia aprirà una finestra con una serie di bottoni iniziali  
4- prima del bottone **CONNETTI** ci sono 2 caselle di testo, il primo per l'IP e il secondo per la porta, di default sono impostati a 127.0.0.1 e 1050  
5- il bottone **DISCONNETTI** è l'equivalente dell'END che si inserisce su terminale, essa termina la connessione  
6- accanto alla scritta **COMANDO** vi è una finestra che al clicco si aprirà nei comandi disponibili all'utente  
7- quando un comando viene scelto, nella finestra di **VALORE** appariranno il valore che si può aggiungere al comande, tali valori sono in ordine alfabetico e sono valori presenti solamente per quel comando  
8- per il comando **get_all** non vi è un valore possibile da aggiungere essendo che restituisce tutte le righe, per comando **get_row** il valore si inserisce tramite testo
