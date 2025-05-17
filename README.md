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
