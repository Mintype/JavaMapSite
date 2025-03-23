# Java Web Server with Interactive Map

This is a basic Java web server that serves an interactive map on a webpage. The map uses Leaflet.js to display countries, and when a country is clicked, it turns red. This is a work in progress and is built using Java's built-in `HttpServer` to handle HTTP requests.

## Features
- A map that displays all countries.
- Click on any country to change its color to red.
- The server serves static files (HTML, CSS, JS) and handles requests for them.

## Setup

### Prerequisites
- Java 8 or higher

### Running the Server
1. Clone the repository or download the project files.
2. Open the project in your favorite IDE or use the terminal.
3. Compile and run the `Server.java` class. The server will start on port `8000` by default.
4. Open your browser and go to `http://localhost:8000/` to view the map.

## Credits
This project makes use of code from the following repository, which is under the MIT License:

- [Leaflet.CountrySelect.js](https://github.com/ahalota/Leaflet.CountrySelect) by Anika S Halota (GitHub: [@ahalota](https://github.com/ahalota))

## License
This project is licensed under the MIT License.

## Contributing
Feel free to fork the project, make changes, and submit pull requests!

## License
MIT License. See the [LICENSE](LICENSE) file for details.
