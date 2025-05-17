# HMAC Client-Server Java Project

## Përshkrimi i Projektit
Ky projekt implementon një komunikim të sigurt mes klientit dhe serverit duke përdorur Hash-based Message Authentication Code (HMAC) për të garantuar integritetin dhe autenticitetin e mesazheve të dërguara.

---

## ## Requirements

### Dependencies

Projekti përdor biblioteka për hashing dhe HMAC, kryesisht nga Java Cryptography Architecture (JCA), për të gjeneruar dhe verifikuar kodet HMAC bazuar në algoritmin SHA-256.

### Shared Secret Key

- Përdoret një çelës sekret i përbashkët (pre-shared secret key) midis klientit dhe serverit.
- Ky çelës përdoret për të gjeneruar dhe verifikuar HMAC-in e mesazheve.
