<%-- 
    Document   : DashboardUser
    Created on : 3 oct 2025, 9:54:45 a.m.
    Author     : ACER-A315-59
--%>

<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       <link rel="stylesheet" href="css/styleDashboard.css"/>
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

        %>
        
        <div class="container">
  
            <aside class="sidebar">
                <div class="logo">
                        <img src="img/Logo2.png" width="190" height="150" alt="Logo pagina" />
                </div>
                <nav>
                    <div class="menu-item active">Dashboard</div>
                    <div class="menu-item ">Mi aprendizaje</div>
                    <div class="menu-item">Cursos populares</div>
                    <div class="menu-item">Material educativo disponible</div>
                    <div class="menu-item">Mi perfil</div>
                    <div class="menu-item">Opciones</div>
                    <a href="SvCerrarSesion" class="menu-item" >Cerrar sesión </a>
  
                </nav>
            </aside>

            <main class="main-content">
                <header class="header">
                    <div class="welcome-text">
                        <h1>Bienvenido, <%=user_login.getPrimer_nombre()%></h1>
                        <p>A tu lugar para compartir conocimiento</p>
                    </div>
                    <div class="header-right">
                        <button class="add-resource-btn">+ Agregar Recurso Educativo</button>
                    </div>
                </header>

       
                <div class="stats-grid">
                    <div class="stat-card">
                        <div class="stat-icon red">📖</div>
                        <div class="stat-info">
                            <h3>Libros</h3>
                            <p>156</p>
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-icon blue">📝</div>
                        <div class="stat-info">
                            <h3>Artículos</h3>
                            <p>243</p>
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-icon yellow">🎓</div>
                        <div class="stat-info">
                            <h3>Cursos</h3>
                            <p>87</p>
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-icon green">👥</div>
                        <div class="stat-info">
                            <h3>Usuarios Activos</h3>
                            <p>1,245</p>
                        </div>
                    </div>
                </div>

            </main>
        </div>
    </body>
</html>
