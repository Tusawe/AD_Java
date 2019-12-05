<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Gestión de reservas</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="/js/jquery.min.js"></script>
        <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <script src="/js/bootstrap.min.js" ></script>
        <script src="/js/bootstrap3-floating-labels.js" type="text/javascript"></script>
        <link href="/css/bootstrap3-floating-labels.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <!-- Static navbar -->
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Desplegar</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="/">Gestión de Reservas</a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="/logout">Logout</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> Usuarios <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="/privado/userRead.jsp">Listado</a></li>
                                    <li><a href="/privado/userCreate.jsp">Alta</a></li>
                                    <li><a href="/privado/userUpdate.jsp">Modificación</a></li>
                                    <li><a href="/privado/userDelete.jsp">Borrado</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> Instalaciones <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="/privado/instalacionRead.jsp">Listar</a></li>
                                    <li><a href="/privado/instalacionCreate.jsp">Alta</a></li>
                                    <li><a href="/privado/instalacionUpdate.jsp">Modificación</a></li>
                                    <li><a href="/privado/instalacionDelete.jsp">Borrado</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>