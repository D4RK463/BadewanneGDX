# Netzwerkkommunikation - Ablauf

## Übersicht

Die Netzwerkkommunikation nutzt ein **Client-Server-Modell** mit **bidirektionaler TCP-Verbindung**. Es gibt immer genau einen Server und einen Client.

## 1. Spiel starten

### Server-Seite (Spieler 1)

1. **Server-Start initiieren**
   - `NetworkManager.startServer()` wird aufgerufen
   - `Server`-Instanz wird mit `onPackageReceived`-Callback erstellt
   - Separater Server-Thread wird gestartet

2. **Server wartet auf Verbindung**
   - `Server.run()` öffnet `ServerSocket` auf konfiguriertem Port
   - `serverSocket.accept()` blockiert und wartet auf Client
   - Akzeptanz-Timeout: 2 Sekunden (wiederholt sich in Schleife)

### Client-Seite (Spieler 2)

1. **Verbindungsdaten eingeben**
   - Benutzer gibt IP-Adresse und Port ein
   - IP wird validiert (IPv4-Format, nicht localhost)
   - Port wird validiert (numerisch, nicht leer)

2. **Verbindung herstellen**
   - `NetworkManager.startClient(ip, port)` wird aufgerufen
   - `Client`-Instanz wird mit `onPackageReceived`-Callback erstellt
   - `client.connect()` erstellt TCP-Socket-Verbindung

3. **Streams initialisieren**
   - `ObjectOutputStream` wird erstellt und geflusht (wichtig für Header)
   - `ObjectInputStream` wird erstellt
   - `isConnected = true` wird gesetzt
   - Client-Empfangs-Thread wird gestartet (`startReceiving()`)

### Server-Seite (Fortsetzung)

4. **Verbindung akzeptiert**
   - `serverSocket.accept()` gibt Client-Socket zurück
   - Server erstellt `ObjectInputStream` (zuerst!)
   - Server erstellt `ObjectOutputStream` und flusht
   - Server ruft `startReceiving()` auf (läuft im Server-Thread)

### ✅ Verbindung hergestellt

Beide Seiten haben nun:
- Einen Sende-Stream (`ObjectOutputStream`)
- Einen aktiven Empfangs-Thread
- Callbacks für eingehende Pakete

---

## 2. Spiel spielen

### Paket senden (Client oder Server)

1. **Spiellogik erkennt Aktion**
   ```kotlin
   networkManager.sendClick("x,y")
   ```

2. **Paket erstellen**
   ```kotlin
   val pkg = Package().apply {
       intent = Intent.CLICK
       data = clickData
   }
   ```

3. **Paket versenden**
   - **Client:** `client.sendPackage(pkg)`
   - **Server:** `server.sendPackage(pkg)`
   - Paket wird über `ObjectOutputStream` serialisiert
   - TCP sendet Daten über Netzwerk

### Paket empfangen (Client oder Server)

4. **Empfangs-Thread wartet**
   - Thread läuft in `while (isConnected/running)` Schleife
   - `inputStream.readObject()` blockiert bis Paket ankommt

5. **Paket verarbeiten**
   - Paket wird deserialisiert
   - `onPackageReceived`-Callback wird aufgerufen
   - `NetworkManager.handleIncomingPackage()` verarbeitet Intent
   - Spiellogik wird entsprechend aktualisiert

### Kommunikationsfluss

```
Client-Spieler macht Klick
    ↓
Client.sendPackage()
    ↓
[TCP-Netzwerk]
    ↓
Server empfängt in startReceiving()
    ↓
Server.onPackageReceived Callback
    ↓
NetworkManager.handleIncomingPackage()
    ↓
Spiel auf Server-Seite wird aktualisiert
```

**Wichtig:**
- Beide Empfangs-Threads laufen **dauerhaft parallel**
- Jede Seite kann **jederzeit** senden und empfangen
- Keine Request-Response-Synchronisation nötig
- Senden erfolgt aus Haupt-Thread (thread-safe)

---

## 3. Spiel beenden

### Beenden initiieren

1. **Zurück-Button geklickt**
   - Im `NetworkScreen` wird Back-Button geklickt
   - `networkManager.stopItAll()` wird aufgerufen

### Client beenden

2. **Client-Verbindung schließen**
   ```kotlin
   client.disconnect()
   ```
   - `isConnected = false` wird gesetzt
   - Empfangs-Thread beendet sich beim nächsten Durchlauf
   - `socket.dispose()` schließt TCP-Verbindung

### Server beenden

3. **Server-Verbindung schließen**
   ```kotlin
   server.stop()
   ```
   - `running = false` wird gesetzt
   - Accept-Schleife stoppt (keine neuen Clients mehr)
   - Empfangs-Schleife stoppt
   - `clientSocket?.dispose()` schließt Client-Verbindung
   - `serverSocket.dispose()` schließt Server-Socket

### ✅ Aufräumen abgeschlossen

- Alle Sockets geschlossen
- Alle Threads beendet
- Keine offenen Netzwerk-Ressourcen

---

## Thread-Übersicht

### Server-Seite
- **Server-Thread** (`Thread(server, "Server")`)
  - Wartet auf Client-Verbindung
  - Läuft dann als Empfangs-Loop

### Client-Seite
- **Client-Receive-Thread** (`"Client-Receive"`)
  - Läuft als dauerhafter Empfangs-Loop

### Haupt-Thread (beide Seiten)
- UI-Rendering und Input-Handling
- Senden von Paketen
- Verarbeitung empfangener Pakete (via Callback)
- **Wichtig:** Callbacks sollten `Gdx.app.postRunnable` nutzen für UI-Updates

---

## Wichtige Hinweise

### Stream-Reihenfolge
Auf **beiden** Seiten muss die Reihenfolge eingehalten werden:
1. `ObjectOutputStream` erstellen
2. `flush()` aufrufen
3. `ObjectInputStream` erstellen

Sonst: **Deadlock**

### Thread-Sicherheit
- Empfangs-Callbacks laufen in Netzwerk-Threads
- UI-Updates müssen über `Gdx.app.postRunnable` erfolgen:
  ```kotlin
  onPackageReceived = { pkg ->
      Gdx.app.postRunnable {
          // UI-Updates hier
      }
  }
  ```

### Error-Handling
- Verbindungsabbrüche werden über Exceptions erkannt
- `isConnected`/`running` Flags verhindern weitere Operationen
- Empfangs-Threads beenden sich selbst bei Fehlern

---

## Implementierung

### Klassen-Struktur

```
NetworkManager
├── Client
│   ├── Socket
│   ├── ObjectOutputStream
│   ├── ObjectInputStream
│   └── Empfangs-Thread
└── Server
    ├── ServerSocket
    ├── Client-Socket (nach Accept)
    ├── ObjectOutputStream
    ├── ObjectInputStream
    └── Empfangs-Loop (im Server-Thread)
```

### Dateien

- `NetworkManager.kt` - Zentrale Verwaltung und Paket-Handling
- `Client.kt` - Client-Implementierung mit Empfangs-Thread
- `Server.kt` - Server-Implementierung mit Accept- und Empfangs-Loop
- `Package.kt` - Serialisierbares Datenpaket
- `Intent.kt` - Enum für Paket-Typen
- `NetworkScreen.kt` - UI für Netzwerk-Setup

