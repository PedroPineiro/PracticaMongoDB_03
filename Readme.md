# Practica 3 MongoDB
## Pedro Piñeiro Ordax

### Enunciado

Escribe un programa que poida exportar datos dende unha base de datos postgreSQL a
unha base de datos mongoDB usando arquivos JSON, ten en conta as súas diferencias.
Supoñemos que, pese a lanzar nós o programa secuencialmente, será un servicio usado
por diferentes sistemas que queren compartir datos.
As táboas a crear en postgreSQL son:

    CREATE TABLE xogadores (
    id_xogador SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    apelidos VARCHAR(100),
    posicion VARCHAR(100),
    data_nacemento DATE,
    nacionalidade VARCHAR(100),
    id_equipo INT REFERENCES equipos(id_equipo)
    );
    
    CREATE TABLE equipos (
    id_equipo SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    cidade VARCHAR(100)
    );

Pra manter as relacións en mongoDB, podemos ter referencias ó seu equipo en cada
xogador ou incluir un listado de xogadores en cada equipo
Para isto deberás:

- Escribir un método para inserir datos en postgreSQL.
- Escribir un método para ler os datos de postgreSQL.
- Escribir un método para exporta-los datos a JSON.
- Escribir un método para ler os datos dende o JSON
- Escribir un método para inserir os datos en MongoDB
- Escribir un método para ler os datos de MongoDB

Usa Maven e busca nos seus repositorios as dependencias que precises para a elaboración
do proxecto.

Podes usar hibernate para as seccións que non sexan de MongoDB
Entrega un arquivo comprimido coo código e (fora do comprimido) un pdf cunha explicación
sintetizada do desenvolvemento (cál é a estructura do programa, explicación dos métodos e
o seu funcionamento…) enfocada á asignatura.