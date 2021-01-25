# Inteligentny Parking Samochodowy - serwer
Projekt o którym więcej możesz poczytać na moim blogu.

[Połączenie Springa z Postgresem nie musi być trudne](https://mjnczk.wordpress.com/2021/01/21/polaczenie-springa-z-postgresem-nie-musi-byc-trudne/)

[Operacje na bazie danych z JDBC](https://mjnczk.wordpress.com/2021/01/22/operacje-na-bazie-danych-z-jdbc/)

[Zastosowanie paginacji przy obszernych bazach danych](https://mjnczk.wordpress.com/2021/01/24/zastosowanie-paginacji-przy-obszernych-bazach-danych/)

## Wymagania
* Java
* Maven

## Skrypty

### Uruchamianie serwera (domyślny port 8080)
`mvn spring-boot:run`

### Endpointy

#### Pobieranie klienta o określonym id
```
GET http://localhost:8080/customers/{id}

Response
{
    "id_uzytkownika": 28,
    "imie": "Lancelot",
    "nazwisko": "Fishbourne",
    "pesel": "23084635565",
    "telefon": "771164619"
}
```

#### Pobieranie wszystkich klientów
```
GET http://localhost:8080/customers/

Response
[
    {
        "id_uzytkownika": 22,
        "imie": "Perla",
        "nazwisko": "O'Hannigan",
        "pesel": "46869100152",
        "telefon": "802672636"
    },
    {
        "id_uzytkownika": 23,
        "imie": "Shell",
        "nazwisko": "Jay",
        "pesel": "63456469251",
        "telefon": "368487896"
    },
    {
        "id_uzytkownika": 24,
        "imie": "Gwendolyn",
        "nazwisko": "Clewer",
        "pesel": "20210103639",
        "telefon": "220311824"
    },
    {...}
```

#### Pobieranie określonej liczby klientów - paginacja
```
GET http://localhost:8080/customers/limit/{limit}/page/{page}

Response
[
    {
        "id_uzytkownika": 42,
        "imie": "Flo",
        "nazwisko": "Crosswaite",
        "pesel": "21379890702",
        "telefon": "691582318"
    },
    {
        "id_uzytkownika": 43,
        "imie": "Aura",
        "nazwisko": "Goudman",
        "pesel": "11937361422",
        "telefon": "247491222"
    }
]
```

#### Dodawanie nowego klienta
```
POST http://localhost:8080/customers/

Request
{
    "imie": "Mateusz",
    "nazwisko": "Janczak",
    "pesel": "11223344556",
    "telefon": "222111333"
}

Response
{
    "id_uzytkownika": 367,
    "imie": "Mateusz",
    "nazwisko": "Janczak",
    "pesel": "11223344556",
    "telefon": "222111333"
}
```

#### Liczba wierszy w tabeli klienci
```
GET http://localhost:8080/count

Response
{
    "count": 286
}
```

#### Edytowanie klienta
```
PUT http://localhost:8080/customers/{id}

Request
{
    "imie": "Nicholle",
    "nazwisko": "Benallack",
    "pesel": "77522169240",
    "telefon": "123256789"
}

Response
{
    "id_uzytkownika": 200,
    "imie": "Nicholle",
    "nazwisko": "Benallack",
    "pesel": "77522169240",
    "telefon": "123256789"
}
```

#### Usuwanie klienta o określonym id
```
DELETE http://localhost:8080/customers/{id}

Response
200 - OK
404 - NOT FOUND
```

#### Wygenerowanie unikalnych numerów telefonów wszystkim klientom
```
GET http://localhost:8080/customers/generatePhones

Response
200 - OK
```