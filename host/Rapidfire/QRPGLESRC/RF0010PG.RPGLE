**FREE

//*-----------------------------------------------------------------------------------------*
//*                                                                                         *
//* Copyright (c) 2016 Task Force IT-Consulting GmbH, Waltrop (Germany)                     *
//* This software and the accompanying materials are made available                         *
//* under the terms of the GNU General Public License version 2.0 (GPLv2)                   *
//* which accompanies this distribution and is available at                                 *
//* http://www.gnu.org/licenses/gpl-2.0.html                                                *
//*                                                                                         *
//*-----------------------------------------------------------------------------------------*
//*                                                                                         *
//* Maintain Conversions                                                                    *
//*                                                                                         *
//*-----------------------------------------------------------------------------------------*
//*  >>PRE-COMPILER<<                                                                       *
//*    >>CRTCMD<< CRTBNDRPG    PGM(&LI/&OB) +                                               *
//*                              SRCFILE(&SL/&SF) SRCMBR(&SM);                              *
//*    >>IMPORTANT<<                                                                        *
//*      >>PARM<<  DBGVIEW(*ALL);                                                           *
//*      >>PARM<<  OPTION(*EVENTF);                                                         *
//*      >>PARM<<  TGTRLS(&TR);                                                             *
//*    >>END-IMPORTANT<<                                                                    *
//*    >>EXECUTE<<                                                                          *
//*  >>END-PRE-COMPILER<<                                                                   *
//*-----------------------------------------------------------------------------------------*
//*                                                                                         *
//* (01 - 49) Freie Bezugszahlen f�r sonstige Dinge                                         *
//*                                                                                         *
//* 01      --> Modus : 'Erstellen'                                                         *
//* 02      --> Modus : '�ndern'                                                            *
//* 03      --> Modus : 'Kopieren'                                                          *
//* 04      --> Modus : 'L�schen'                                                           *
//* 05      --> Modus : 'Anzeigen'                                                          *
//*                                                                                         *
//*-----------------------------------------------------------------------------------------*
//*                                                                                         *
//* (50 - 59) Reservierte Bezugszahlen f�r Diverses                                         *
//*                                                                                         *
//* 50      --> Schl�sselwort SFLEND in Nachrichtensubdatei aktivieren                      *
//* 51      --> Ergebnis f�r LookUp Operation                                               *
//*                                                                                         *
//*-----------------------------------------------------------------------------------------*
//*                                                                                         *
//* (60 - 79) Reservierte Bezugszahlen f�r Subdateiverarbeitung                             *
//*                                                                                         *
//* 60,64,68,72,76  --> (Subdatei 1 - 5) Inhalt der Subdatei l�schen                        *
//* 61,65,69,73,77  --> (Subdatei 1 - 5) Keine S�tze in der Subdatei vorhanden              *
//* 62,66,70,74,78  --> (Subdatei 1 - 5) Letzter Satz befindet sich bereits in der Subdatei *
//* 63,67,71,75,79  --> (Subdatei 1 - 5) Subdateisatz ist ein ge�nderter Satz               *
//*                                                                                         *
//*-----------------------------------------------------------------------------------------*
//*                                                                                         *
//* (80 - 89) Reservierte Bezugszahlen f�r Fehlermeldungen                                  *
//*                                                                                         *
//* 80      --> Es liegt ein Fehler vor (Spezifikation durch BZ 81 - 89)                    *
//* 81 - 83 --> (Block 1) Fehlermeldungen                                                   *
//* 84 - 86 --> (Block 2) Fehlermeldungen                                                   *
//* 87 - 89 --> (Block 3) Fehlermeldungen                                                   *
//*                                                                                         *
//*-----------------------------------------------------------------------------------------*
//*                                                                                         *
//* (90 - 99) Reservierte Bezugszahlen f�r Cursorpositionierung                             *
//*                                                                                         *
//* 90      --> Es soll positioniert werden (Spezifikation durch 91 - 99)                   *
//* 91      --> Der Cursor soll auf Koordinate Zeile / Spalte positioniert werden           *
//*                                                                                         *
//*-----------------------------------------------------------------------------------------*
//*                                                                                         *
//* �bergabeparameter                                                                       *
//* =================                                                                       *
//*                                                                                         *
//* PIMode     / *CREATE  --> Modus : 'Erstellen'                                           *
//*              *CHANGE  --> Modus : '�ndern'                                              *
//*              *COPY    --> Modus : 'Kopieren'                                            *
//*              *DELETE  --> Modus : 'L�schen'                                             *
//*              *DISPLAY --> Modus : 'Anzeigen'                                            *
//*                                                                                         *
//* PIRtCd     / *F3      --> Der Benutzer hat die Taste F3 gedr�ckt                        *
//*              *F12     --> Der Benutzer hat die Taste F12 gedr�ckt                       *
//*              *ENTER   --> Der Benutzer hat die Taste ENTER gedr�ckt                     *
//*                                                                                         *
//*------------+--------+-------------------------------------------------------------------*
//*   Datum    I Progr. I Beschreibung                                                      *
//*------------+--------+-------------------------------------------------------------------*
//* 01.07.2014 I   FH   I Programm erstellt                                                 *
//*------------+--------+-------------------------------------------------------------------*
//* 13.06.2016 I   TR   I Enabled program for '*DISPLAY' mode.                              *
//*------------+--------+-------------------------------------------------------------------*

// Control

CTL-OPT DatFmt(*ISO) TimFmt(*ISO) DecEdit('0,');
CTL-OPT DftActGrp(*NO) ActGrp('RAPIDFIRE') BNDDIR('RAPIDFIRE');
CTL-OPT Copyright('(C) Copyright Task Force IT-Consulting GmbH');
CTL-OPT ExtBinInt(*YES);

//*-----------------------------------------------------------------------------------------*

DCL-F RF0010DF Workstn(*EXT) USAGE(*INPUT:*OUTPUT)
      InfDS(FIDS_RF0010DF);

//*-----------------------------------------------------------------------------------------*

// Prozedurenprototyp f�r Prozedur 'RF0010PG'

/Copy QCPYSRC,RF0010PG

// Prozedurenschnittstelle f�r Prozedur 'RF0010PG'

DCL-PI RF0010PG;
  PIMode CHAR(10) Const;                                                 // --> Modus
  PIJOB CHAR(10) Const;                                                  // --> Job
  PIPOS PACKED(6) Const;                                                 // --> Position
  PIFTC CHAR(10);                                                        // <-> Zu konvertier
  PIRtCd CHAR(10);                                                       // <-- R�ckkehrcode
END-PI;

//*-----------------------------------------------------------------------------------------*

// Prozedurenprototyp f�r Prozedur 'RtvFktKey'

/Copy QCPYSRC,RTVFKTKEY

// Prozedurenprototyp f�r Prozedur 'RmvErrMsg'

/Copy QCPYSRC,RMVERRMSG

// Prozedurenprototyp f�r Prozedur 'SndErrMsg'

/Copy QCPYSRC,SNDERRMSG

// Prozedurenprototyp f�r Prozedur 'QUHDSPH'

/Copy QCPYSRC,QUHDSPH

// Datenstruktur f�r Format 'ERRC0100' f�r Fehlercode

/Copy QCPYSRC,ERRC0100

// Prozedurenprototyp f�r Prozedur 'MNTCNV'

/Copy QCPYSRC,MNTCNV

// Dateiinformationsdatenstruktur f�r RF0010DF

DCL-DS FIDS_RF0010DF;
  AIDCde CHAR(1) POS(369);
END-DS;

// Hilfe-Id.

DCL-DS *N;
  ITHlpId CHAR(52) Dim(100);                                             // Hilfe-Id.
  ITHIPG CHAR(10) Overlay(ITHlpId : 1);                                  // Panelgroup
  ITHILP CHAR(10) Overlay(ITHlpId : 11);                                 // Bibliothek Panelg
  ITHIMO CHAR(32) Overlay(ITHlpId : 21);                                 // Modul
END-DS;

// Sonstige Felddeklarationen

DCL-S ITLoop IND Inz(*On);                                               // Schleife
DCL-S ITFktKey CHAR(10);                                                 // Funktionstaste
DCL-S ITSts CHAR(10);                                                    // Nachrichten-Id. b
DCL-S ITMsgId CHAR(7);                                                   // Nachrichten-Id.
DCL-S ITMsgDta CHAR(256);                                                // Nachrichtendaten
DCL-S ITMsgF   CHAR(10);                                                 // Nachrichtendatei
DCL-S ITDsp INT(10) Dim(2);                                              // Anzeige Hilfetext
DCL-S ITNbr INT(10);                                                     // Anzahl Hilfetextm
DCL-S ITLftC INT(10) Dim(2);                                             // Linke Ecke
DCL-S ITRghC INT(10) Dim(2);                                             // Rechte Ecke
DCL-S ITCsrL INT(10) Dim(2);                                             // Cursorposition
DCL-S ITHlp CHAR(1);                                                     // Kontextbezogene H
DCL-S ITXFld CHAR(10);                                                   // Feld
DCL-S ITXMsg CHAR(256);                                                  // Nachricht
DCL-S ITSUCC CHAR(1);                                                    // Erfolg

//*-----------------------------------------------------------------------------------------*

// Bezugszahl f�r 'Schl�sselwort SFLEND in Nachrichtensubdatei aktivieren' auf 'Ja' setzen
*IN50 = *On;
// Dateien �ffnen
MNTCNV_openFiles();
// Initialisieren
If MNTCNV_initialize(
  PIMode : // --> Mode
  PIJOB  : // --> Job
  PIPOS  : // --> Position
  PIFTC  : // --> Field to convert
  ITSUCC : // <-- Success
  ITXMsg   // <-- Message
);
  // Job initialisieren
  DFJOB = PIJOB;
  // Position initialisieren
  DFPOS = PIPOS;
  Select;
    // Modus : 'Erstellen'
  When PIMode = MNTCNV_CREATE;
    ExSr SR005;
    If PIRtCd = '*ENTER';
      PIFTC = DFFTC;
    EndIf;
    // Modus : '�ndern'
  When PIMode = MNTCNV_CHANGE;
    ExSr SR010;
    // Modus : 'Kopieren'
  When PIMode = MNTCNV_COPY;
    ExSr SR015;
    If PIRtCd = '*ENTER';
      PIFTC = DFFTC;
    EndIf;
    // Modus : 'L�schen'
  When PIMode = MNTCNV_DELETE;
    ExSr SR020;
    // Modus : 'Anzeigen'
  When PIMode = MNTCNV_DISPLAY;
    ExSr SR025;
  EndSl;
EndIf;
// Dateien schlie�en
MNTCNV_closeFiles();
// Programm nicht hauptspeicherresident verlassen
*INLR = *On;

//*-----------------------------------------------------------------------------------------*
//* Erstellen                                                                               *
//*-----------------------------------------------------------------------------------------*

BegSr SR005;
  // �berschrift f�r Modus 'Erstellen' anzeigen
  *IN(1) = *ON;
  *IN(2) = *OFF;
  *IN(3) = *OFF;
  *IN(4) = *OFF;
  *IN(5) = *OFF;
  // Bildschirm mit Werten f�llen
  ExSr SR030;
  // Bildschirm verarbeiten
  ExSr SR045;
  If PIRtCd = '*ENTER';
    // Verbuchen
    MNTCNV_book();
  EndIf;
EndSr;

//*-----------------------------------------------------------------------------------------*
//* �ndern                                                                                  *
//*-----------------------------------------------------------------------------------------*

BegSr SR010;
  // �berschrift f�r Modus '�ndern' anzeigen
  *IN(1) = *OFF;
  *IN(2) = *ON;
  *IN(3) = *OFF;
  *IN(4) = *OFF;
  *IN(5) = *OFF;
  // Bildschirm mit Werten f�llen
  ExSr SR030;
  // Bildschirm verarbeiten
  ExSr SR045;
  If PIRtCd = '*ENTER';
    // Verbuchen
    MNTCNV_book();
  EndIf;
EndSr;

//*-----------------------------------------------------------------------------------------*
//* Kopieren                                                                                *
//*-----------------------------------------------------------------------------------------*

BegSr SR015;
  // �berschrift f�r Modus 'Kopieren' anzeigen
  *IN(1) = *OFF;
  *IN(2) = *OFF;
  *IN(3) = *ON;
  *IN(4) = *OFF;
  *IN(5) = *OFF;
  // Bildschirm mit Werten f�llen
  ExSr SR030;
  // Bildschirm verarbeiten
  ExSr SR045;
  If PIRtCd = '*ENTER';
    // Verbuchen
    MNTCNV_book();
  EndIf;
EndSr;

//*-----------------------------------------------------------------------------------------*
//* L�schen                                                                                 *
//*-----------------------------------------------------------------------------------------*

BegSr SR020;
  // �berschrift f�r Modus 'L�schen' anzeigen
  *IN(1) = *OFF;
  *IN(2) = *OFF;
  *IN(3) = *OFF;
  *IN(4) = *ON;
  *IN(5) = *OFF;
  // Bildschirm mit Werten f�llen
  ExSr SR030;
  // Bildschirm verarbeiten
  ExSr SR045;
  If PIRtCd = '*ENTER';
    // Best�tigung f�r L�schung einholen
    ExFmt DSPF020W;
    // Funktionstaste abrufen
    ITFktKey = RtvFktKey(AIDCde);
    // Datensatz l�schen
    If ITFktKey = '*ENTER';
      // Verbuchen
      MNTCNV_book();
    Else;
      // L�schung abgebrochen
      PIRtCd = '*F12';
    EndIf;
  EndIf;
EndSr;

//*-----------------------------------------------------------------------------------------*
//* Anzeigen                                                                                *
//*-----------------------------------------------------------------------------------------*

BegSr SR025;
  // �berschrift f�r Modus 'Anzeigen' anzeigen
  *IN(1) = *OFF;
  *IN(2) = *OFF;
  *IN(3) = *OFF;
  *IN(4) = *OFF;
  *IN(5) = *ON;
  // Bildschirm mit Werten f�llen
  ExSr SR030;
  // Bildschirm verarbeiten
  ExSr SR045;
  If PIRtCd = '*ENTER';
    // Verbuchen
    MNTCNV_book();
  EndIf;
EndSr;

//*-----------------------------------------------------------------------------------------*
//* Bildschirm mit Werten f�llen                                                            *
//*-----------------------------------------------------------------------------------------*

BegSr SR030;
  MNTCNV_getValues(
    DFFTC  : // <-- Field to convert
    DFRFT  : // <-- Rename field in old file to
    DFSTM1 : // <-- Statement
    DFSTM2 : // <-- Statement
    DFSTM3 : // <-- Statement
    DFSTM4 : // <-- Statement
    DFSTM5 : // <-- Statement
    DFSTM6   // <-- Statement
  );
EndSr;

//*-----------------------------------------------------------------------------------------*
//* Bildschirm verarbeiten                                                                  *
//*-----------------------------------------------------------------------------------------*

BegSr SR045;
  // Fehlernachricht entfernen
  ITSts = RmvErrMsg('*SAME');
  // Fehlernachrichten-Id. initialisieren
  ITMsgId = *Blanks;
  ITMsgDta = *Blanks;
  ITMsgF = *Blanks;
  // Fehlerbezugszahlen initialisieren
  *IN(80) = *OFF;
  *IN(81) = *OFF;
  *IN(82) = *OFF;
  *IN(83) = *OFF;
  *IN(84) = *OFF;
  *IN(85) = *OFF;
  *IN(86) = *OFF;
  *IN(87) = *OFF;
  *IN(88) = *OFF;
  *IN(89) = *OFF;
  // Positionierung deaktivieren
  *IN(90) = *OFF;
  *IN(91) = *OFF;
  *IN(92) = *OFF;
  *IN(93) = *OFF;
  *IN(94) = *OFF;
  *IN(95) = *OFF;
  *IN(96) = *OFF;
  *IN(97) = *OFF;
  *IN(98) = *OFF;
  *IN(99) = *OFF;
  DoW ITLoop;
    // Fehlernachricht in die Programmnachrichtenwarteschlange schreiben
    If ITMsgId <> *Blanks;
      ExSr SR055;
    EndIf;
    DoU ITFktKey <> '*F1';
      // Satzformate auf den Bildschirm ausgeben
      DFPGMQ = '*';
      Write DSPF999C;
      ExFmt DSPF010;
      // Funktionstaste abrufen
      ITFktKey = RtvFktKey(AIDCde);
      // Funktionstaste : 'F1=Hilfe'
      If ITFktKey = '*F1';
        ExSr SR060;
      EndIf;
    EndDo;
    // Fehlernachricht entfernen
    ITSts = RmvErrMsg('*SAME');
    // Fehlernachrichten-Id. initialisieren
    ITMsgId = *Blanks;
    ITMsgDta = *Blanks;
    ITMsgF = *Blanks;
    // Fehlerbezugszahlen initialisieren
    *IN(80) = *OFF;
    *IN(81) = *OFF;
    *IN(82) = *OFF;
    *IN(83) = *OFF;
    *IN(84) = *OFF;
    *IN(85) = *OFF;
    *IN(86) = *OFF;
    *IN(87) = *OFF;
    *IN(88) = *OFF;
    *IN(89) = *OFF;
    // Positionierung deaktivieren
    *IN(90) = *OFF;
    *IN(91) = *OFF;
    *IN(92) = *OFF;
    *IN(93) = *OFF;
    *IN(94) = *OFF;
    *IN(95) = *OFF;
    *IN(96) = *OFF;
    *IN(97) = *OFF;
    *IN(98) = *OFF;
    *IN(99) = *OFF;
    // Funktionstaste : 'F3=Verlassen'
    If ITFktKey = '*F3';
      PIRtCd = '*F3';
      Leave;
    EndIf;
    // Funktionstaste : 'F12=Abbrechen'
    If ITFktKey = '*F12';
      PIRtCd = '*F12';
      Leave;
    EndIf;
    // Funktionstaste : 'F4=Bedienerf�hrung'
    If ITFktKey = '*F4';
      ExSr SR065;
      If PIRtCd = '*F3';
        Leave;
      Else;
        Iter;
      EndIf;
    EndIf;
    // Funktionstaste : 'Enter'
    If ITFktKey = '*ENTER';
      // Bildschirmfelder auf Fehler �berpr�fen
      ExSr SR050;
      If ITMsgId <> *Blanks;
        Iter;
      EndIf;
      // R�ckkehrcode setzen
      PIRtCd = '*ENTER';
      Leave;
    EndIf;
  EndDo;
EndSr;

//*-----------------------------------------------------------------------------------------*
//* Bildschirmfelder auf Fehler �berpr�fen                                                  *
//*-----------------------------------------------------------------------------------------*

BegSr SR050;
  // Namensfelder linksb�ndig ausrichten
  If PIMode = MNTCNV_CREATE Or
    PIMode = MNTCNV_COPY Or
    PIMode = MNTCNV_CHANGE;
    DFFTC = %TRIML(DFFTC);
    DFRFT = %TRIML(DFRFT);
  EndIf;
  // *** Fehlerpr�fung nur f�r Modus 'Erstellen', 'Kopieren' und '�ndern' ***
  If PIMode = MNTCNV_CREATE Or
    PIMode = MNTCNV_COPY Or
    PIMode = MNTCNV_CHANGE;
    MNTCNV_setValues(
      DFFTC  : // <-- Field to convert
      DFRFT  : // <-- Rename field in old file to
      DFSTM1 : // <-- Statement
      DFSTM2 : // <-- Statement
      DFSTM3 : // <-- Statement
      DFSTM4 : // <-- Statement
      DFSTM5 : // <-- Statement
      DFSTM6   // <-- Statement
    );
  EndIf;
  // Pr�fen
  If Not MNTCNV_check(
    ITSUCC : // <-- Erfolg
    ITXFld : // <-- Feld
    ITXMsg   // <-- Nachricht
  );
    // Fehler setzen
    ITMsgId = 'CPF9897';
    ITMsgDta = ITXMsg;
    ITMsgF = 'QCPFMSG';
    Select;
    When (ITXFld = 'FTC');
      *IN(87) = *OFF;
      *IN(88) = *OFF;
      *IN(89) = *ON;
    When (ITXFld = 'RFT');
      *IN(87) = *ON;
      *IN(88) = *OFF;
      *IN(89) = *ON;
    When (ITXFld = 'FTCRFT');
      *IN(87) = *OFF;
      *IN(88) = *OFF;
      *IN(89) = *OFF;
    When (ITXFld = 'STM');
      *IN(87) = *OFF;
      *IN(88) = *ON;
      *IN(89) = *ON;
    EndSl;
  EndIf;
EndSr;

//*-----------------------------------------------------------------------------------------*
//* Fehlernachricht in die Programmnachrichtenwarteschlange schreiben                       *
//*-----------------------------------------------------------------------------------------*

BegSr SR055;
  // Bezugszahl f�r 'Es liegt ein Fehler vor' auf 'Ja' setzen
  *IN80 = *On;
  // Fehlernachricht in die Programmnachrichtenwarteschlange schreiben
  ITSts = SndErrMsg(
   '*SAME' :                                                             // --> Aufrufstapele
   ITMsgId :                                                             // --> Nachrichten-I
   ITMsgF  :                                                             // --> Nachrichtenda
   '*LIBL' :                                                             // --> Bibliothek Na
   ITMsgDta                                                              // --> Nachrichtenda
  );
EndSr;

//*-----------------------------------------------------------------------------------------*
//* Hilfe                                                                                   *
//*-----------------------------------------------------------------------------------------*

BegSr SR060;
  // Kontextbezogene Hilfe initialisiern
  ITHlp = 'N';
  // Nummer Hilfetextmodul initialisieren
  ITNbr = *Zero;
  // Hilfe f�r Fehlernachrichtensubdatei angefordert
  If DFRCD010 = 'DSPF999S';
    ITNbr = ITNbr + 1;
    ITHIPG(ITNbr) = 'CMOREFGP';
    ITHILP(ITNbr) = '*LIBL';
    ITHIMO(ITNbr) = 'REF/MSGINF';
    ITHlp = 'J';
    ITDsp(1) = ITNbr;
    ITDsp(2) = ITNbr;
  Else;
    // Modul 'GNR'
    ITNbr = ITNbr + 1;
    ITHIPG(ITNbr) = 'CNVS';
    ITHILP(ITNbr) = '*LIBL';
    ITHIMO(ITNbr) = 'GNR';
    // Modul 'JOB'
    ITNbr = ITNbr + 1;
    ITHIPG(ITNbr) = 'JOBS';
    ITHILP(ITNbr) = '*LIBL';
    ITHIMO(ITNbr) = 'JOB';
    If DFFLD010 = 'DFJOB';
      ITHlp = 'J';
      ITDsp(1) = ITNbr;
      ITDsp(2) = ITNbr;
    EndIf;
    // Modul 'POS'
    ITNbr = ITNbr + 1;
    ITHIPG(ITNbr) = 'FILES';
    ITHILP(ITNbr) = '*LIBL';
    ITHIMO(ITNbr) = 'POS';
    If DFFLD010 = 'DFPOS';
      ITHlp = 'J';
      ITDsp(1) = ITNbr;
      ITDsp(2) = ITNbr;
    EndIf;
    // Modul 'FTC'
    ITNbr = ITNbr + 1;
    ITHIPG(ITNbr) = 'CNVS';
    ITHILP(ITNbr) = '*LIBL';
    ITHIMO(ITNbr) = 'FTC';
    If DFFLD010 = 'DFFTC';
      ITHlp = 'J';
      ITDsp(1) = ITNbr;
      ITDsp(2) = ITNbr;
    EndIf;
    // Modul 'RFT'
    ITNbr = ITNbr + 1;
    ITHIPG(ITNbr) = 'CNVS';
    ITHILP(ITNbr) = '*LIBL';
    ITHIMO(ITNbr) = 'RFT';
    If DFFLD010 = 'DFRFT';
      ITHlp = 'J';
      ITDsp(1) = ITNbr;
      ITDsp(2) = ITNbr;
    EndIf;
    // Modul 'STM'
    ITNbr = ITNbr + 1;
    ITHIPG(ITNbr) = 'CNVS';
    ITHILP(ITNbr) = '*LIBL';
    ITHIMO(ITNbr) = 'STM';
    If DFFLD010 = 'DFSTM1' Or
      DFFLD010 = 'DFSTM2' Or
      DFFLD010 = 'DFSTM3' Or
      DFFLD010 = 'DFSTM4' Or
      DFFLD010 = 'DFSTM5' Or
      DFFLD010 = 'DFSTM6';
      ITHlp = 'J';
      ITDsp(1) = ITNbr;
      ITDsp(2) = ITNbr;
    EndIf;
    // Modul 'FKT'
    ITNbr = ITNbr + 1;
    ITHIPG(ITNbr) = 'RF0010GP';
    ITHILP(ITNbr) = '*LIBL';
    Select;
    When PIMode = MNTCNV_CREATE Or
      PIMode = MNTCNV_COPY Or
      PIMode = MNTCNV_CHANGE;
      ITHIMO(ITNbr) = 'FKT/EDT';
    When PIMode = MNTCNV_DISPLAY Or
      PIMode = MNTCNV_DELETE;
      ITHIMO(ITNbr) = 'FKT/DSP';
    EndSl;
    If DFROW010 >= 23 And
      DFROW010 <= 23 And
      DFCOL010 >= 2 And
      DFCOL010 <= 79;
      ITHlp = 'J';
      ITDsp(1) = ITNbr;
      ITDsp(2) = ITNbr;
    EndIf;
  EndIf;
  // Keine kontextbezogene Hilfe ausgew�hlt
  If ITHlp = 'N';
    ITDsp(1) = 1;
    ITDsp(2) = ITNbr;
  EndIf;
  // Hilfetext anzeigen
  ITLftC(1) = 1;
  ITLftC(2) = 1;
  ITRghC(1) = 24;
  ITRghC(2) = 80;
  ITCsrL(1) = DFROW010;
  ITCsrL(2) = DFCOL010;
  Reset ERRC0100;
  QUHDSPH(
   ITHlpId :                                                             // --> Help identifi
   ITNbr :                                                               // --> Number of hel
   ITDsp :                                                               // --> Help type
   *Blanks :                                                             // --> Full display
   '*NONE' :                                                             // --> Qualified sea
   'N' :                                                                 // --> Display type
   ITLftC :                                                              // --> Upper left co
   ITRghC :                                                              // --> Lower right c
   ITCsrL :                                                              // --> Cursor locati
   ERRC0100                                                              // <-> Error code
  );
  // Cursor auf der letzten Position positionieren
  *IN(90) = *ON;
  *IN(91) = *ON;
  *IN(92) = *OFF;
  *IN(93) = *OFF;
  *IN(94) = *OFF;
  *IN(95) = *OFF;
  *IN(96) = *OFF;
  *IN(97) = *OFF;
  *IN(98) = *OFF;
  *IN(99) = *OFF;
EndSr;

//*-----------------------------------------------------------------------------------------*
//* Bedienerf�hrung                                                                         *
//*-----------------------------------------------------------------------------------------*

BegSr SR065;
  Select;
    // No prompt for the requested field available
  Other;
    ITMsgId = 'ERR0007';
    ITMsgDta = *Blanks;
    ITMsgF = 'RF0010MF';
  EndSl;
  // Cursor auf der letzten Position positionieren
  *IN(90) = *ON;
  *IN(91) = *ON;
  *IN(92) = *OFF;
  *IN(93) = *OFF;
  *IN(94) = *OFF;
  *IN(95) = *OFF;
  *IN(96) = *OFF;
  *IN(97) = *OFF;
  *IN(98) = *OFF;
  *IN(99) = *OFF;
EndSr;
