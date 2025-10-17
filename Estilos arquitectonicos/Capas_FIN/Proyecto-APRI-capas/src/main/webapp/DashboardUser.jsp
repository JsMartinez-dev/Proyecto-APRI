<%-- 
    Document   : DashboardUser
    Created on : 3 oct 2025, 9:54:45 a.m.
    Author     : ACER-A315-59
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Articulo"%>
<%@page import="modelo.Libro"%>
<%@page import="modelo.MaterialEducativo"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard Usuarios</title>
        <link rel="stylesheet" href="css/styleUserDash_1.css"/>
    </head>

    <body>
        
        <%
            //Validamos que el usuario llego correctamente
            HttpSession sesion = request.getSession(false);
            if(sesion ==null || sesion.getAttribute("usuario") ==null){
                response.sendRedirect("InicioSesionUsuario.jsp");
                return;
            }
            
            Usuario user_login = (Usuario) sesion.getAttribute("usuario");
            
            List<MaterialEducativo> listaMat = (List) request.getAttribute("listaMar");
            
            int cantLibros=0,cantArticulos=0,cantCursos=0;
            
            for (MaterialEducativo mat : listaMat){
                    if(mat.getTipo().equals("libro")){
                        cantLibros++;
                    }else if(mat.getTipo().equals("articulo")){
                        cantArticulos++;     
                    }else{
                        cantCursos++; 
            }
                }

        %>
        
        <div class="container">
  
            <aside class="sidebar">
                <div class="logo">
                        <img src="img/Logo2.png" width="190" height="150" alt="Logo pagina" />
                </div>
                <nav>
                    <a href="UsuarioControll?accion=dashboardUser"  class="menu-item active" >Dashboard</a>
                    <div class="menu-item ">Mi aprendizaje</div>
                    <div class="menu-item">Cursos populares</div>
                    <div class="menu-item">Material educativo disponible</div>
                    <div class="menu-item">Mi perfil</div>
                    <div class="menu-item">Opciones</div>
                    <a href="CerrarSesion"  class="menu-item" >Cerrar sesión </a>
  
                </nav>
            </aside>

            <main class="main-content">
                <header class="header">
                    <div class="welcome-text">
                        <h1>Bienvenido, <%=user_login.getPrimer_nombre()%></h1>
                        <p>A tu lugar para compartir conocimiento</p>
                    </div>
                    <div class="header-right">
                        <form action="ElegirMaterial.jsp">
                        <button class="add-resource-btn">+ Agregar Recurso Educativo</button>
                        </form>
                    </div>
                </header>

       
                <div class="stats-grid">
                    <div class="stat-card">
                        <div class="stat-icon red">📖</div>
                        <div class="stat-info">
                            <h3>Tus libros</h3>
                            <p><%=cantLibros%></p>
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-icon blue">📝</div>
                        <div class="stat-info">
                            <h3>Tus artículos</h3>
                            <p><%=cantArticulos%></p>
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-icon yellow">🎓</div>
                        <div class="stat-info">
                            <h3>Tus cursos</h3>
                            <p><%=cantCursos%></p>
                        </div>
                    </div>

                </div>

            </main>
        </div>
    </body>
</html>
