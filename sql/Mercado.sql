-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-05-2023 a las 22:00:06
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mercado`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito`
--

CREATE TABLE `carrito` (
  `dni_usuario` varchar(9) NOT NULL,
  `id_producto` int(9) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fecha_compra` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `carrito`
--

INSERT INTO `carrito` (`dni_usuario`, `id_producto`, `cantidad`, `fecha_compra`) VALUES
('12122128R', 4, 2, '2023-01-25'),
('12122128R', 5, 5, '2023-05-22'),
('78456124Y', 8, 3, '2023-05-22');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(9) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `pais_origen` varchar(100) NOT NULL,
  `fecha_caducidad` date NOT NULL,
  `precio` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `nombre`, `pais_origen`, `fecha_caducidad`, `precio`) VALUES
(1, 'Pera', 'canarias', '2023-03-31', '0.75'),
(2, 'Arroz', 'china', '2023-03-31', '1.60'),
(3, 'Macarrones', 'italia', '2023-03-31', '1.40'),
(4, 'Manzana', 'españa', '2023-03-31', '0.80'),
(5, 'Lentejas', 'España', '2023-06-30', '3.80'),
(6, 'Platano', 'Canarias', '2023-05-31', '2.10'),
(7, 'Canela', 'Sri Lanka', '2023-05-31', '2.50'),
(8, 'Atun', 'España', '2023-05-31', '7.80'),
(9, 'Mandarina', 'España', '2023-05-31', '2.40'),
(10, 'Patatas', 'España', '2023-05-31', '3.40');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `Apellido` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`dni`, `nombre`, `Apellido`) VALUES
('12122128R', 'Jose', 'Ruiz'),
('12321439R', 'Juan Jose', 'Gutierrez'),
('12345667P', 'Isabel', 'Roldan'),
('12345678R', 'Paola', 'Santos'),
('34567892U', 'Antonio', 'Montilla'),
('45678923T', 'Manuel', 'Perez'),
('78456124Y', 'Luis', 'Cortes'),
('87654321T', 'Sergio', 'Cubero');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD PRIMARY KEY (`dni_usuario`,`id_producto`,`fecha_compra`),
  ADD KEY `fk_producto_carrito` (`id_producto`),
  ADD KEY `dni_usuario` (`dni_usuario`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`dni`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD CONSTRAINT `fk_producto_carrito` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_usuario_carrito` FOREIGN KEY (`dni_usuario`) REFERENCES `usuario` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
