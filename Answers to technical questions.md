1. How long did you spend on the coding test? What would you add to your solution if you had more time? If you didn't spend much time on the coding test, use this as an opportunity to explain what you would add.

Alrededor de 3 horas. La solución cumple con los requisitos, así que no creo que añadiera nada. Tal vez una opción para que el usuario pueda escoger cómo se ordenan las máquinas mostradas. A parte de la funcionalidad, realizar un diseño más modular que permita realizar pruebas sobre una mayor parte de la aplicación.

1. What was the most useful feature added to the latest version of your chosen language? Please include a snippet of code that shows how you've used it.

El lenguaje utilizado es Java 11. Aunque no es la versión más reciente, una de las funcionalidades más útiles de esta versión es la estandarización del cliente HTTP, que permite realizar peticiones HTTP y gestionar las respuestas sin necesidad de librerías externas.

```java
HttpRequest httpRequest = HttpRequest.newBuilder()
    .uri(new URI(uri))
    .header("header", "value")
    .GET()
    .build();

HttpClient httpClient = HttpClient.newBuilder()
    .connectTimeout(Duration.ofSeconds(60))
    .followRedirects(Redirect.NORMAL)
    .build();

HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
```

1. How would you track down a performance issue in production? Have you ever had to do this?

Nunca he tenido que monitorizar aspectos de *performance* en producción.

1. How would you improve the Lantek API that you just used?

Añadiendo otras opciones en la petición que permitan seleccionar con más control las máquinas que se recogen. Por ejemplo, mostrar solo las máquinas del manufactor escogido. O incluir datos adicionales en las máquinas como la fecha de lanzamiento o el material con el que trabajan.

Thanks for your time. We look forward to hearing from you! If you have any questions or feedback about this coding exercise, please contact your HR representative.