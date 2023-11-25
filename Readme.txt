Link al video: https://www.youtube.com/watch?v=WpvOjyxG1DI
Link al APK: https://drive.google.com/drive/folders/1SeSEcuOr3cgkDjTku7QjZ2rejuvMfPWm?usp=drive_link

REQUERIMIENTOS FUNCIONALES:
	La aplicación debe contar con una pantalla de bienvenida que muestre su nombre y logo
	La aplicación debe poder gestionar un inventario (agregar, quitar, modificar y eliminar)
	La aplicación debe contar con una pantalla de compra de productos, los cuales deben añadirse al inventario
	La aplicación debe tener todos los textos y colores en un archivo XML
	La aplicación debe tener recordatorios que se eliminen una semana después de agregarlos
	La aplicación debe poder guardar el inventario en una base de datos manejada en firebase y de forma local
	La aplicación debe tener un ícono vectorizado
	La aplicación debe tener una pantalla de acceso la cual debe permitir navegar en la aplicación
	La aplicación debe poder cambiar de idioma entre inglés y español
	La aplicación debe contar con una forma de ordenar los productos (Nombre, stock, categoría, etc)
	La aplicación debe contar con una forma de buscar los productos usando su nombre
	Cada uno de los productos debe contar con un registro, el cuál debe mencionar la fecha en la que se cambia el 	valor del producto y todos los detalles de las compras del usuario.


REQUERIMIENTOS NO FUNCIONALES:
	La aplicación debe ser intuitiva para cualquier tipo de usuario
	La aplicación debe usar una paleta de colores adecuada al tema
	La aplicación debe guardar bien los datos de los usuarios
	La aplicación debe ser fluida
	La aplicación debe ayudar a los usuarios en su día a día
	Los tiempos de carga no deben superar los 5 segundos
	Se debe validar la identidad de los usuarios
	Debe matener informado al usuario de lo que está pasando en cada momento
	La aplicación debe ser capaz de soportar hasta 10.000 usuarios concurrentes

Esta aplicación tiene la función de ayudar a los usuarios a manejar la despensa de sus casas.
La aplicación inicia con una splash screen que muestra el logo de la aplicación y el nombre, luego de esto entra a la pantalla de Log in, en la cuál se le pide al usuario el nombre y clave de su cuenta, en caso de no contar con una se les presenta la opción de crear una, una vez logra ingresar entra a la pantalla de Home, en esta se muestran 2 opciones Inventario y Lista de compra. En la pantalla de inventario se muestran los objetos que tiene actualmente el usuario en su despensa, en la lista de compra puede añadir más productos a su inventario, a la vez que le indica el dinero que va a gastar.
Esta nueva versión hace uso de Room, las cuales son bases de datos que facilita android studio, en estas bases de datos se guardan: los productos que se pueden comprar, los producctos que están en el inventario y el registro de movimientos, este último guarda todos los movimientos de cada uno de los productos. A su vez en esta nueva versión se da la opción de cambiar el idioma entre inglés y español.