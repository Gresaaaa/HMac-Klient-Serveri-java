# HMAC Client-Server Java Project

## Përshkrimi i Projektit
Ky projekt implementon një komunikim të sigurt mes klientit dhe serverit duke përdorur Hash-based Message Authentication Code (HMAC) për të garantuar integritetin dhe autenticitetin e mesazheve të dërguara.

---

## Requirements

### Dependencies

Projekti përdor biblioteka për hashing dhe HMAC, kryesisht nga Java Cryptography Architecture (JCA), për të gjeneruar dhe verifikuar kodet HMAC bazuar në algoritmin SHA-256.

### Shared Secret Key

- Përdoret një çelës sekret i përbashkët (pre-shared secret key) midis klientit dhe serverit.
- Ky çelës përdoret për të gjeneruar dhe verifikuar HMAC-in e mesazheve.

### Client-Side Message Preparation

- Klienti pranon input nga përdoruesi për mesazhin që do të dërgohet.
- Gjeneron HMAC përmes çelësit sekret dhe SHA-256 mbi mesazhin e inputuar.
- Dërgon mesazhin së bashku me HMAC-in në server.


### Server-Side Message Verification

- Serveri pranon mesazhin dhe HMAC-in nga klienti.
- Verifikon HMAC-in duke përdorur çelësin sekret dhe të njëjtin algoritëm.
- Kthen një përgjigje që konfirmon nëse mesazhi është autentik dhe i paprekur.

### Communication Protocol

- Komunikimi ndodh mbi një protokoll të thjeshtë socket bazuar në rreshta teksti.
- Mesazhi dhe HMAC dërgohen si string të ndarë me një karakter të caktuar (p.sh. `||`).
- Serveri përgjigjet me mesazh sukses/fail sipas rezultatit të verifikimit.
- Protokolli përfshin menaxhim bazik të gabimeve dhe mundësi për ritransmetim në rast deshtimi (mund të zgjerohet).


### User Interface

- Implementohen ndërfaqe konsolle (console UI) për klientin dhe serverin.
- Përdoruesi udhëzohet qartë gjatë dërgimit dhe marrjes së mesazheve.
- Mesazhet e statusit (p.sh. "Mesazhi u dërgua me sukses", "HMAC validuar") shfaqen në mënyrë të thjeshtë për përdoruesin.

### Security Practices

- Çelësi sekret trajtohet me kujdes dhe nuk ekspozohet jashtë aplikacionit.
- Përdoren teknika për parandalimin e sulmeve të njohura si timing attacks në verifikimin e HMAC.
- Përmirësime të mundshme për sigurinë mund të përfshijnë encryption të kanaleve të komunikimit (jo implementuar në këtë version bazë).

### Logging

- Që të dyja, klienti dhe serveri, ruajnë log-et në file (p.sh. `log.txt`).
- Regjistrohen veprime kryesore si:
    - Dërgimi i mesazheve me HMAC.
    - Marrja e mesazheve.
    - Rezultati i verifikimit të HMAC.
- Kjo ndihmon në auditim dhe debug të sistemit.
- Versionet më të vjetra të log-ut ruhen automatikisht në skedarë backup, si p.sh. `log.txt.1`. Skedarët e tillë mund të fshihen nëse nuk janë të nevojshëm më.
- Ruajtja e log-ut ndihmon në monitorimin e sigurisë dhe funksionimit të saktë të komunikimit midis klientit dhe serverit.
---

## Si të Ekzekutoni

1.Klononi repository-n:

git clone https://github.com/Gresaaa/HMac-Klient-Serveri-java.git
cd HMac-Klient-Serveri-java

2️.Ndërtoni projektin me Maven:

mvn clean install

3️.Startoni Serverin:

java -cp target/HMac-Klient-Serveri-java-1.0-SNAPSHOT.jar hmac.Server

4️.Startoni Klientin:

java -cp target/HMac-Klient-Serveri-java-1.0-SNAPSHOT.jar hmac.Klient

##Siguria

Integriteti i Mesazhit: Mesazhi dhe HMAC dërgohen së bashku te serveri. Serveri verifikon nëse HMAC përputhet me mesazhin e dërguar duke përdorur të njëjtin çelës sekret dhe algoritëm SHA-256.

Autenticiteti: Vetëm klientët që kanë çelësin sekret të saktë mund të gjenerojnë një HMAC valid, gjë që garanton se mesazhi vjen nga një burim i besuar.

Validimi i Formatit: Serveri kontrollon që mesazhi i pranuar ka formatin e saktë: message||hmac||timestamp. Nëse formati është i gabuar, serveri refuzon mesazhin.

Logim i Sigurt: Të gjitha mesazhet dhe verifikimet regjistrohen në log, duke lejuar auditim dhe gjurmim të ngjarjeve të dyshimta.

